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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

            //check to see if user already in database, if not need to create profile
            List<CodelotUser> users = ObjectifyService.ofy()
                    .load()
                    .type(CodelotUser.class)
                    .filter("user_id",user.getUserId())
                    .list();

            //Get all the users
            System.out.println(user.getEmail());
            System.out.println(user.getUserId());
            //If user already registered dont direct them
            if (users.size()>=1) {

                return languageSelection(users.get(0).getId());
            }
            //if user not already registered profile creation page
            else{
                ModelAndView model = new ModelAndView("WEB-INF/pages/profileCreationPage");
                return model;
            }
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
        //Get the current user and check to see if they are already in the system else
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();

        //Get all the users
        System.out.println(user.getEmail());
        System.out.println(user.getUserId());
        //If user already registered dont direct them
        if (users.size()>=1) {

            ModelAndView model = new ModelAndView("WEB-INF/pages/languageSelection");
            return model;
        }
        //if user not already registered profile creation page
        else{
            ModelAndView model = new ModelAndView("WEB-INF/pages/profileCreationPage");
            return model;
        }
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

        return languageSelection(newUser.getId());
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
    public ModelAndView languageSelection(Long id) {
        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(id).now();
        ModelAndView model = new ModelAndView("WEB-INF/pages/MapSelect");
        model.addObject("fullName", c_user.getFullname());
        model.addObject("username", c_user.getUsername());
        model.addObject("avatar", c_user.avatarImage);
        model.addObject("email", c_user.getUser_email());
        return model;

    }
}
