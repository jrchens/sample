package me.sample.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "menu")
public class MenuControlller {

    @RequestMapping(value = "index.html", method = RequestMethod.GET)
    public String index(@RequestParam(defaultValue = "menu_sys", required = false) String sub) {
        return "index";
    }
}
