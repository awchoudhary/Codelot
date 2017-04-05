package com.codelot.controller;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public ModelAndView home(){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        //if there is no logged in user, redirect to homepage
        if(user == null){
            ModelAndView model = new ModelAndView("WEB-INF/pages/home");
            return model;
        }
        else{
            //redirect to map select page if there is a logged in user
            ModelAndView model = new ModelAndView("WEB-INF/pages/MapSelect");
            return model;
        }
    }

    @RequestMapping(value = "/signup")
    public ModelAndView signup(){
        //redirect to google auth page. On signing up, user is redirected to profile page
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/profile"));
        return model;
    }

    @RequestMapping(value = "/profile")
    public String profile(){
        return "WEB-INF/pages/Profile";
    }

    @RequestMapping(value = "/signin")
    public ModelAndView signin(){
        //redirect to google auth page. On signing in, user should be redirected to mapselect
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/"));
        return model;
    }

    @RequestMapping("/signout")
    public ModelAndView signout (){
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLogoutURL("/"));
        return model;
    }
}
