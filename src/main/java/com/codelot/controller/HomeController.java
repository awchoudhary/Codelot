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
    @RequestMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("WEB-INF/pages/home");
        return model;
    }

    @RequestMapping("/profileCreationPage")
    public ModelAndView profileCreationPage() {
        ModelAndView model = new ModelAndView("WEB-INF/pages/profileCreationPage");
        return model;
    }

    @RequestMapping("/profileCreation")
    public ModelAndView profileCreation(@RequestParam("fullname") String fullname,
                                        @RequestParam("age") int age,
                                        @RequestParam("username") String username,
                                        @RequestParam("avatar") String avatar) {
        CodelotUser newUser = new CodelotUser(fullname, age, username, avatar);
        ObjectifyService.ofy().save().entity(newUser).now();

        return languageSelection(username);
    }

    @RequestMapping("/languageSelection")
    public ModelAndView languageSelection(String username) {
        ModelAndView model = new ModelAndView("WEB-INF/pages/languageSelection");

        //model.addObject("avatarPath", )
        return model;
    }
}
