package me.sample.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import me.sample.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.io.BaseEncoding;

import io.jsonwebtoken.Jwts;
import me.sample.base.service.JsonWebTokenService;

// public class JWTInterceptor extends WebRequestHandlerInterceptorAdapter {
public class JsonWebTokenInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenInterceptor.class);

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

//    private CacheManager cacheManager;
//
//    public CacheManager getCacheManager() {
//        return cacheManager;
//    }
//
//    public void setCacheManager(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("JsonWebTokenInterceptor.preHandle handler.getClass: {}", handler.getClass());
        String method = request.getMethod();
        if (!method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("POST")) {
            return super.preHandle(request, response, handler);
        } else {
            String user = request.getHeader("user");
            String passwordSalt = null;
            try {
                String cacheJwtoken = jsonWebTokenService.getJWToken(user);
                String jwtoken = request.getHeader("jwtoken");
                if (cacheJwtoken != null && jwtoken.equals(jwtoken)) {
                    passwordSalt = jdbcTemplate.queryForObject(
                            "select password_salt from sys_user where deleted = 0 and username = ?", String.class, user);

                    Jwts.parser().requireSubject(user)
                            .setSigningKey(BaseEncoding.base64().encode(passwordSalt.getBytes()))
                            .parseClaimsJws(jwtoken);
                } else {
                    logger.info("unvalid jwtoken: {}", jwtoken);
                    throw new ServiceException("jwtoken validation failed");
                }

            } catch (Exception e) {
                logger.error("", e);
//                response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE.toString());
//                response.sendError(HttpStatus.UNAUTHORIZED.value());
//                response.addHeader("Access-Control-Allow-Methods", "*");
//                response.addHeader("Access-Control-Allow-Headers", "*");

                response.addHeader("Access-Control-Allow-Origin", "*");
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON.toString());
                response.setCharacterEncoding("utf-8");
                response.getWriter().write("{'success':'false','message':'Unauthorized','data':''}");
                return false;
            } finally {
                jsonWebTokenService.delJWToken(user);
                jsonWebTokenService.generateJWToken(user, UUID.randomUUID().toString(), passwordSalt);
            }

            return true;

        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
