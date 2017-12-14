package me.sample.api.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.StatelessToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.google.common.collect.Maps;

import me.sample.base.domain.StatelessUser;
import me.sample.base.service.StatelessUserLoginService;

@Controller
public class StatelessLoginController {

    private static final Logger logger = LoggerFactory.getLogger(StatelessLoginController.class);

    @Autowired
    private StatelessUserLoginService statelessUserLoginService;

    @RequestMapping(value = "login.json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> query(WebRequest webRequest, 
            @RequestParam String username,
            @RequestParam String password) {
        logger.info("{}", webRequest.getDescription(true));
        logger.info("{},{}", username, password);
        Map<String, Object> res = Maps.newHashMap();
        try {
            StatelessUser su = statelessUserLoginService.login(username, password);
            logger.info("StatelessUser: {}",su);
            Map<String, Object> data = Maps.newHashMap();
            data.put("jwtoken", su.getJwtoken());
            data.put("viewname", su.getViewname());

//            Subject subject = SecurityUtils.getSubject();
//            subject.login(new StatelessToken(username,su.getJwtoken()));
//            if(!subject.isAuthenticated()){
//                logger.info("user: {} authenticate faield",su.getUsername());
//            }

            res.put("success", true);
            res.put("message", "");
            res.put("data", data);
        } catch (Exception e) {
            res.put("success", false);
            res.put("message", "");
            res.put("data", e.getMessage());
        }
        // return
        // ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        return ResponseEntity.ok(res);
    }
    
}
