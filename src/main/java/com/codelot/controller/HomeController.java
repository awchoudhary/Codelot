package com.codelot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String home(){
        return "WEB-INF/pages/home";
    }
}
