package me.sample.mvc.support;

import java.net.BindException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;

@ControllerAdvice
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    // BindException // 400 (Bad Request)
    // ConversionNotSupportedException // 500 (Internal Server Error)
    // HttpMediaTypeNotAcceptableException // 406 (Not Acceptable)
    // HttpMediaTypeNotSupportedException // 415 (Unsupported Media Type)
    // HttpMessageNotReadableException // 400 (Bad Request)
    // HttpMessageNotWritableException // 500 (Internal Server Error)
    // HttpRequestMethodNotSupportedException // 405 (Method Not Allowed)
    // MethodArgumentNotValidException // 400 (Bad Request)
    // MissingPathVariableException // 500 (Internal Server Error)
    // MissingServletRequestParameterException // 400 (Bad Request)
    // MissingServletRequestPartException // 400 (Bad Request)
    // NoHandlerFoundException // 404 (Not Found)
    // NoSuchRequestHandlingMethodException // 404 (Not Found)
    // TypeMismatchException // 400 (Bad Request)

    @ExceptionHandler(value = { BindException.class })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> bindException(WebRequest webRequest, @RequestHeader String user,
            BindException e) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{},{},{}", user, e);
        Map<String, Object> res = Maps.newHashMap();
        res.put("success", false);
        res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        res.put("data", "data bind exception");
        res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseBody
    public ResponseEntity<Map<String, Object>> exception(Exception e, HttpServletRequest request,
            HttpServletResponse response) {
        Map<String, Object> res = Maps.newHashMap();
        res.put("success", false);
        res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        res.put("data", "server exception");
        res.put("jwtoken", jsonWebTokenService.getJWToken(request.getHeader("user")));
        return ResponseEntity.ok(res);
    }
    @ExceptionHandler(value = { MultipartException.class })
     @ResponseBody
    public ResponseEntity<Map<String, Object>> /*void*/ multipartException(MultipartException e, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        Map<String, Object> res = Maps.newHashMap();
        
        response.addHeader("Access-Control-Allow-Origin", "*");
        res.put("success", false);
        res.put("message", e.getCause() != null ? e.getCause().getMessage() : e.getMessage());
        res.put("data", "data upload exception");
        res.put("jwtoken", jsonWebTokenService.getJWToken(request.getHeader("user")));
        
        return ResponseEntity.ok(res);
    }

}
