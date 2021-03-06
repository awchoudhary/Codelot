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

    // Takes in list of buildings, calculates completed/total * 100 to get progress percent
    public int getProgress(ArrayList<Building> buildings){
        int numCompleted = 0;
        // calculate user progress for map
        for(int x=0; x<buildings.size(); x++){
            if (buildings.get(x).isCompleted()){
                numCompleted += 1;
            }
        }
        int progress = (int)((((double) numCompleted)/buildings.size()) * 100);

        return progress;
    }

    @RequestMapping("/languageSelection")
    public ModelAndView languageSelection() {
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();

        //direct to map select page
        ModelAndView model = new ModelAndView("WEB-INF/pages/MapSelect");

        // get progress for java map
        ArrayList<Building> javaBuildings = profile.getJavaCodelot().getBuildings();
        int javaProgress = getProgress(javaBuildings);

        // get progress for python map
        ArrayList<Building> pythonBuildings = profile.getPythonCodelot().getBuildings();
        int pythonProgress = getProgress(pythonBuildings);

        // get progress for JavaScript map
        ArrayList<Building> jsBuildings = profile.getJavaScriptCodelot().getBuildings();
        int jsProgress = getProgress(jsBuildings);

        if(profile != null){
            model.addObject("fullName", profile.getFullname());
            model.addObject("username", profile.getUsername());
            model.addObject("avatar", profile.avatarImage);
            model.addObject("email", profile.getUser_email());
            model.addObject("age", profile.getAge());
            model.addObject("javaProgress", javaProgress);
            model.addObject("pythonProgress", pythonProgress);
            model.addObject("jsProgress", jsProgress);
        }

        return model;
    }

    @RequestMapping("/map")
    public ModelAndView map(@RequestParam("selectedLang") String lang) {
        ModelAndView model = new ModelAndView("WEB-INF/pages/map");
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();

        System.out.println("selectedLang = "+lang);

        if(c_user != null){

            String langMap;
            ArrayList<Building> buildings;

            // get map and buildings for language
            if (lang.equals("20")){
                langMap = "../scripts/javascript_map.js";
                buildings = c_user.getJavaScriptCodelot().getBuildings();
            }
            else if (lang.equals("30")){
                langMap = "../scripts/python_map.js";
                buildings = c_user.getPythonCodelot().getBuildings();
            }
            else { // lang is java
                langMap = "../scripts/java_map.js";
                buildings = c_user.getJavaCodelot().getBuildings();
            }

            int progress = getProgress(buildings);

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
        if (str == null || str.isEmpty() || str.equals("") || str.indexOf(0)==' '){
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
