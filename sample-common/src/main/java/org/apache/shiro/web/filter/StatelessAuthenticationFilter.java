package org.apache.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.StatelessToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

public class StatelessAuthenticationFilter extends AccessControlFilter {

    private static final Logger logger = LoggerFactory.getLogger(StatelessAuthenticationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        logger.info("StatelessAuthenticationFilter.isAccessAllowed.request:{},response:{},mappedValue:{}", request,
                response, mappedValue);
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (RequestMethod.OPTIONS.name().equalsIgnoreCase(httpServletRequest.getMethod())) {
            return true;
        }

        boolean bool = false;
        try {

            String[] roles = (String[]) mappedValue;
            Subject subject = getSubject(request, response);
            for (String role : roles
                    ) {
                if (subject.hasRole(role)) {
                    bool = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("StatelessAuthenticationFilter.isAccessAllowed.error",e);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

        return bool;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        try {
//
//            logger.info("StatelessAuthenticationFilter.onAccessDenied.request:{},response:{}", request,
//                    response);
//
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//            String user = httpServletRequest.getHeader("user");
//            String jwtoken = httpServletRequest.getHeader("jwtoken");
//
//            logger.info("user:{},jwtoken:{}", user, jwtoken);
//
//            StatelessToken token = new StatelessToken(user, jwtoken);
//            getSubject(request, response).login(token);
//        } catch (Exception e) {
//            logger.error("StatelessAuthenticationFilter.onAccessDenied.Error", e);
//            HttpServletResponse httpResponse = (HttpServletResponse) response;
//            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            // httpResponse.getWriter().write("login error");
//            return false;
//        }
        return false;
    }

}
