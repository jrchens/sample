package me.sample.cms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * webapp
 *     WEB-INF
 *       views
 *           login.jsp
 *           index.jsp
 *           reg.jsp
 *       web.xml
 * 
 * 
 * @author ChenSheng
 *
 */

//@Controller
public class RegController {

    @RequestMapping(value = "reg", method = RequestMethod.GET)
    public String login(Model model) {
        return "reg";
    }

    @RequestMapping(value = "reg", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        String redirect = "reg";
        try {
            // save user
            
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                redirect = "redirect:/index";
            }
        } catch (Exception e) {
            if (e instanceof AccountException) {
                model.addAttribute("error_code", "username.not.exists");
            } else if (e instanceof IncorrectCredentialsException) {
                model.addAttribute("error_code", "password.not.equals");
            } else {
                model.addAttribute("error_code", "server.error");
            }
        }
        return redirect;
    }

}
