package com.codelot.controller;

import com.codelot.Beans.*;
import com.codelot.services.CodelotUserService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.googlecode.objectify.ObjectifyService;

import java.util.ArrayList;


/**
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("WEB-INF/pages/home");
        return model;
    }

    @RequestMapping(value = "/signin")
    public ModelAndView signin() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        //if there is no logged in user, redirect to login page.
        if (user == null){
            ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/signin"));
            return model;
        }

        //if account does not have a profile, redirect to profile creation page.
        if (CodelotUserService.getCurrentUserProfile() == null){
            ModelAndView model = new ModelAndView("WEB-INF/pages/profilePage");
            return model;
        }
        //redirect to map select otherwise
        else{
            return languageSelection();
        }
    }

    @RequestMapping("/signout")
    public ModelAndView signout () {
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLogoutURL("/"));
        return model;
    }

    @RequestMapping("/languageSelection")
    public ModelAndView languageSelection() {
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        //direct to map select page
        ModelAndView model = new ModelAndView("WEB-INF/pages/mapSelect");

        if(profile != null){
            model.addObject("fullName", profile.getFullname());
            model.addObject("username", profile.getUsername());
            model.addObject("avatar", profile.avatarImage);
            model.addObject("email", profile.getUser_email());
            model.addObject("age", profile.getAge());
        }
        return model;
    }

    @RequestMapping("/map")
    public ModelAndView map() {
        ModelAndView model = new ModelAndView("WEB-INF/pages/map");
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();

        if(c_user != null){
            ArrayList<Building> buildings = c_user.getJavaCodelot().getBuildings();

            int numCompleted = 0;
            // get number of completed buildings
            for(int x=0; x<buildings.size(); x++){
                if (c_user.getJavaCodelot().getBuildings().get(x).isCompleted() == true){
                    numCompleted += 1;
                }
            }
            int progress = (int)((((double) numCompleted)/buildings.size()) * 100);

//            // return correct map - java, js, python
//            String langMap;
//            if (lang.equals("javascript")){
//                langMap = "../scripts/javascript_map.js";
//            }
//            else if (lang.equals("python")){
//                langMap = "../scripts/python_map.js";
//            }
//            else { // lang is java
//                langMap = "../scripts/java_map.js";
//            }
//
//            model.addObject("lang", langMap);
            model.addObject("fullName", c_user.getFullname());
            model.addObject("username", c_user.getUsername());
            model.addObject("avatar", c_user.avatarImage);
            model.addObject("email", c_user.getUser_email());
            model.addObject("age", c_user.getAge());
            model.addObject("progress", progress);
        }

        return model;
    }

    @RequestMapping("/profilePage")
    public ModelAndView profilePage() {
        //Load values for user
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        ModelAndView model = new ModelAndView("WEB-INF/pages/profilePage");

        //populate fields if not null
        if(profile != null){
            model.addObject("fullName", profile.getFullname());
            model.addObject("username", profile.getUsername());
            model.addObject("avatar", profile.avatarImage);
            model.addObject("email", profile.getUser_email());
            model.addObject("age", profile.getAge());
        }

        return model;
    }

    @RequestMapping("/updateProfile")
    public ModelAndView updateProfile(@RequestParam("fullname") String fullname,
                                      @RequestParam("age") int age,
                                      @RequestParam("username") String username,
                                      @RequestParam("avatar") String avatar) {

        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        if(profile == null){
            //create new user and redirect to language select
            CodelotUserService.createUser(fullname, age, username, avatar);
        }
        else{
            //update the existing profile
            CodelotUserService.updateUser(fullname, age, username, avatar);
        }

        return languageSelection();
    }

    @RequestMapping("/reset")
    public ModelAndView resetProgress (){
        System.out.println("------------------- RESET ------------------");
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();
        profile.setJavaCodelot(new JavaCodelot());
        profile.setJavaScriptCodelot(new JavaScriptCodelot());
        profile.setPythonCodelot(new PythonCodelot());
        ObjectifyService.ofy().save().entity(profile).now();

        return languageSelection();
    }
}
