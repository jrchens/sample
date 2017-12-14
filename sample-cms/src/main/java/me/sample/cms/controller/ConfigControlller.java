package me.sample.cms.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
//@RequestMapping(value = "config")
public class ConfigControlller {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String login(Model model) {
    // model.addAttribute("now", sampleService.getNow());
    return "cms/config/index";
    }
}
