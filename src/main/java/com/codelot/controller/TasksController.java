package com.codelot.controller;

import com.codelot.Beans.Building;
import com.codelot.Beans.CodelotUser;
import com.codelot.Beans.Floor;
import com.codelot.services.CompilerService;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jane on 4/19/2017.
 */
public class TasksController {

    @RequestMapping("/getJavaMap")
    public ModelAndView javaMap() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        //check to see if user already in database, if not need to create profile
        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();
        long userid = users.get(0).getId();

        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(userid).now();
        ModelAndView model = new ModelAndView("WEB-INF/pages/map");
        model.addObject("fullName", c_user.getFullname());
        model.addObject("username", c_user.getUsername());
        model.addObject("avatar", c_user.avatarImage);
        model.addObject("email", c_user.getUser_email());
        model.addObject("age", c_user.getAge());
        return model;
    }

    @RequestMapping("/getJavaTasks")
    public ModelAndView javaTasks() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        //check to see if user already in database, if not need to create profile
        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();
        // get id of user
        long userid = users.get(0).getId();
        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(userid).now();

        List<Building> bldgs = c_user.getJavaCodelot().getBuildings();
        for (int i=0; i<bldgs.size(); i++){
            System.out.println(i+" "+bldgs.get(i).getName());
        }

        List<Floor> floors = c_user.getJavaCodelot().getBuildings().get(0).getFloors();
        for (Floor floor : floors) {
            System.out.println(floor.getIndex());
            System.out.println(floor.getTaskDescription());
        }

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("floors", floors);

        return model;
    }

}
