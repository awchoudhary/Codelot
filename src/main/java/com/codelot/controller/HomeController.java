package com.codelot.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {
    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("WEB-INF/pages/home");
        return model;
    }

    @RequestMapping("/profileCreation")
    public ModelAndView profileCreation() {
        ModelAndView model = new ModelAndView("WEB-INF/pages/languageSelection");
        return model;
    }
}
