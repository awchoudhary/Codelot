package com.codelot.controller;

import com.codelot.Beans.CodelotUser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            ModelAndView model = new ModelAndView("WEB-INF/pages/languageSelection");
            return model;
        }
    }

    @RequestMapping("/profileCreationPage")
    public ModelAndView profileCreationPage() {
        //redirect to google auth page. On signing up, user is redirected to profile page
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/profileCreation1"));
        return model;
    }

    @RequestMapping("/profileCreation1")
    public ModelAndView profileCreation2(){
        ModelAndView model = new ModelAndView("WEB-INF/pages/profileCreationPage");
        return model;
    }
    @RequestMapping("/profileCreation2")
    public ModelAndView profileCreation2(@RequestParam("fullname") String fullname,
        @RequestParam("age") int age,
        @RequestParam("username") String username,
        @RequestParam("avatar") String avatar) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
            CodelotUser newUser = new CodelotUser(fullname, age, username, avatar);
            newUser.setUser(user.getEmail(), user.getUserId());
            ObjectifyService.ofy().save().entity(newUser).now();

            return languageSelection(username);
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
    public ModelAndView signout () {
            UserService userService = UserServiceFactory.getUserService();
            ModelAndView model = new ModelAndView("redirect:" + userService.createLogoutURL("/"));
            return model;
    }

    @RequestMapping("/languageSelection")
    public ModelAndView languageSelection(String username) {
        ModelAndView model = new ModelAndView("WEB-INF/pages/languageSelection");

        //model.addObject("avatarPath", )
        return model;
    }
}
