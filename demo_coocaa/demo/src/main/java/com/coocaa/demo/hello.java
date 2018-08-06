package com.coocaa.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class hello {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello ( ){
        return " hello spring boot!";
    }

    @RequestMapping("/go")
    private ModelAndView go() {
        ModelAndView mav = new ModelAndView("charts");
        return mav;
    }

    @RequestMapping("/test")
    public String test(){
        return "demo";
    }
}
