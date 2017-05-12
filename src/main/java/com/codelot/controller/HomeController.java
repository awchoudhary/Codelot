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
            model.addObject("display","display:none");
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
    public ModelAndView map(@RequestParam("selectedLang") String lang) {
        ModelAndView model = new ModelAndView("WEB-INF/pages/map");
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();

        // Figure out selected language based on langugage code
        Language currentLang;
        if (lang.equals("20")){ // code for javascript
            currentLang = c_user.getJavaScriptCodelot();
        }
        else if (lang.equals("30")){ // code for python
            currentLang = c_user.getPythonCodelot();
        }
        else{ // code for javascript
            currentLang = c_user.getJavaCodelot();
        }

        if(c_user != null){
            ArrayList<Building> buildings = currentLang.getBuildings();

            int numCompleted = 0;
            // get number of completed buildings
            for(int x=0; x<buildings.size(); x++){
                if (currentLang.getBuildings().get(x).isCompleted() == true){
                    numCompleted += 1;
                }
            }
            int progress = (int)((((double) numCompleted)/buildings.size()) * 100);

            // return correct map - java, js, python
            String langMap;
            if (lang.equals("20")){
                langMap = "../scripts/javascript_map.js";
            }
            else if (lang.equals("30")){
                langMap = "../scripts/python_map.js";
            }
            else { // lang is java
                langMap = "../scripts/java_map.js";
            }

            model.addObject("lang", langMap);
            model.addObject("fullName", c_user.getFullname());
            model.addObject("username", c_user.getUsername());
            model.addObject("avatar", c_user.avatarImage);
            model.addObject("email", c_user.getUser_email());
            model.addObject("age", c_user.getAge());
            model.addObject("progress", progress);
        }

        return model;
    }

    // profilePage() function without any particular note
    @RequestMapping("/profilePage")
    public ModelAndView profile(){
        return profilePage("");
    }

    public ModelAndView profilePage(String note) {
        //Load values for user
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        ModelAndView model = new ModelAndView("WEB-INF/pages/profilePage");
        // Do not show reset div if it a user creation
        // We dont want to give a new user the option of resetting all progress
        String display = "display:none";

        //populate fields if not null
        if(profile != null){
            System.out.println("profile is not null");
            display = ""; // Do show reset div and button if prfile update
            model.addObject("fullName", profile.getFullname());
            model.addObject("username", profile.getUsername());
            model.addObject("avatar", profile.avatarImage);
            model.addObject("email", profile.getUser_email());
            model.addObject("age", profile.getAge());
        }

        model.addObject("display", display);
        model.addObject("note", note);
        return model;
    }

    // Function to determine if string is null or is or starts with a space char
    public boolean isValid (String str){
        if (str.isEmpty() || str.equals("") || str.indexOf(0)==' '){
            return false;
        }
        return true;
    }

    @RequestMapping("/updateProfile")
    public ModelAndView updateProfile(@RequestParam("fullname") String fullname,
                                      @RequestParam("age") int age,
                                      @RequestParam("username") String username,
                                      @RequestParam("avatar") String avatar) {

        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        if (isValid(fullname) && isValid(username) && age>0){
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
        // if any of the strings are invalid, send warning/note
        return profilePage("Note: one or more of the fields are invalid.");
    }

    @RequestMapping("/resetRedirect")
    public ModelAndView resetRedirect (){
        ModelAndView model = new ModelAndView("WEB-INF/pages/resetProgress");
        return model;
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
