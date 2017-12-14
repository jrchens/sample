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

@Controller
public class LoginController {

    // @Autowired
    // private SampleService sampleService;

    @RequestMapping(value = {"login.html"}, method = RequestMethod.GET)
    public String login(Model model) {
        // model.addAttribute("now", sampleService.getNow());
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return "redirect:/index.html";
        }
        return "login";
    }

    @RequestMapping(value = "login.html", method = RequestMethod.POST)
    public String login(String username, String password, Model model) {
        String redirect = "login";
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                redirect = "redirect:/index.html";
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

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public String logout(Model model) {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }

}
