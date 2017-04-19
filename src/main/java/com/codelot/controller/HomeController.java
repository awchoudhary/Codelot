package com.codelot.controller;

import com.codelot.Beans.CodelotUser;
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
import java.util.List;


/**
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView home(){
        //if there is no logged in user, redirect to homepage
        ModelAndView model = new ModelAndView("WEB-INF/pages/home");
        return model;
    }

    @RequestMapping("/profileCreationPage")
    public ModelAndView profileCreationPage() {
        //redirect to google auth page. On signing up, user is redirected to profile page
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/profileCreation1"));
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

        return languageSelection(newUser.getId());
    }

    @RequestMapping(value = "/signin")
    public ModelAndView signin() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null){
            ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/signin1"));
            return model;
        }
        //ModelAndView model = new ModelAndView("redirect:" + userService.createLoginURL("/signin1"));
        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();
        if (user != null && users.size()>=1){
            return languageSelection(users.get(0).getId());
        }
        else if (user != null && users.size()<=0){
            return signin1();
        }
        else{
            return home();
        }
    }

    @RequestMapping(value = "/signin1")
    public ModelAndView signin1(){
        //redirect to google auth page. On signing in, user should be redirected to mapselect
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

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

    @RequestMapping("/signout")
    public ModelAndView signout () {
        UserService userService = UserServiceFactory.getUserService();
        ModelAndView model = new ModelAndView("redirect:" + userService.createLogoutURL("/"));
        return model;
    }

    @RequestMapping("/languageSelection")
    public ModelAndView languageSelection(Long id) {
        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(id).now();
        ModelAndView model = new ModelAndView("WEB-INF/pages/mapSelect");
        model.addObject("fullName", c_user.getFullname());
        model.addObject("username", c_user.getUsername());
        model.addObject("avatar", c_user.avatarImage);
        model.addObject("email", c_user.getUser_email());
        model.addObject("age", c_user.getAge());
        return model;
    }

    @RequestMapping("/map")
    public ModelAndView map() {
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

    @RequestMapping("/changeSettings1")
    public ModelAndView changeSettings1() {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();

        long userId = users.get(0).getId();

        //Load values for user
        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(userId).now();
        ModelAndView model = new ModelAndView("WEB-INF/pages/changeSettings");
        model.addObject("fullName", c_user.getFullname());
        model.addObject("username", c_user.getUsername());
        model.addObject("avatar", c_user.avatarImage);
        model.addObject("email", c_user.getUser_email());
        model.addObject("age", c_user.getAge());

        return model;
    }

    @RequestMapping("/changeSettings2")
    public ModelAndView changeSettings2(@RequestParam("fullname") String fullname,
                                        @RequestParam("age") int age,
                                        @RequestParam("username") String username,
                                        @RequestParam("avatar") String avatar) {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();

        long userId = users.get(0).getId();

        //Load values for user
        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(userId).now();

        // set new values
        c_user.setAge(age);
        c_user.setAvatarImage(avatar);
        c_user.setFullname(fullname);
        c_user.setUsername(username);

        ObjectifyService.ofy().save().entity(c_user).now();

        return languageSelection(c_user.getId());
    }

    @RequestMapping("/TaskPage")
    public String taskPage(){
        return "WEB-INF/pages/TaskPage";
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String execute(@RequestBody String source) throws IOException {
        //parse parameter to get source
        JSONObject obj = new JSONObject(source);
        String sourceText = obj.getString("source");
        CompilerService service = new CompilerService();
        return service.execute(sourceText);
    }
}
