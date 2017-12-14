package me.sample.mvc.support;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.google.common.collect.Maps;

import me.sample.base.service.JsonWebTokenService;

@Controller
public class ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorController.class);

    @Autowired
    private JsonWebTokenService jsonWebTokenService;

    @RequestMapping(value = "error.json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(
            WebRequest webRequest, 
            @RequestHeader String user,
            @RequestParam String type) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", user, type);

//        String[] names = webRequest.getAttributeNames(RequestAttributes.SCOPE_REQUEST);
//        for (String name : names) {
//            logger.info("{},{}", name,webRequest.getAttribute(name, RequestAttributes.SCOPE_REQUEST));
//        }
        
        Exception exception = (Exception)webRequest.getAttribute("javax.servlet.error.exception", RequestAttributes.SCOPE_REQUEST);
        
        Map<String, Object> res = Maps.newHashMap();
        res.put("success", false);
        res.put("message", exception.getCause() != null ? exception.getCause().getMessage() : exception.getMessage());
        if ("multipartException".equals(type)) {
            res.put("data", "multipart exception");
        } else {
            res.put("data", "");
        }
        res.put("data", "server exception");
        res.put("jwtoken", jsonWebTokenService.getJWToken(user));
        return ResponseEntity.ok(res);
    }

}
