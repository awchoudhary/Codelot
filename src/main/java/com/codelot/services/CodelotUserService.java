package com.codelot.services;

import com.codelot.Beans.CodelotUser;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

import java.util.List;

/**
 * Created by awaeschoudhary on 4/26/17.
 */
public final class CodelotUserService {

    //create a new codelot user and return id for new user
    public static long createUser(String fullname, int age, String username, String avatar){
        //get the logged in user
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        //create new codelot user
        CodelotUser newUser = new CodelotUser(fullname, age, username, avatar);
        newUser.setUser(user.getEmail(), user.getUserId());

        //save user
        ObjectifyService.ofy().save().entity(newUser).now();

        //return the id for the created user
        return newUser.getId();
    }

    //get codelot user for the logged in account
    public static CodelotUser getCurrentUserProfile(){
        //get logged in user account
        User user = UserServiceFactory.getUserService().getCurrentUser();

        //return corresponding codelot user account
        List<CodelotUser> profiles =  ObjectifyService.ofy()
                                .load()
                                .type(CodelotUser.class)
                                .filter("user_id", user.getUserId())
                                .list();

        if(profiles.size() > 0){
            return profiles.get(0);
        }
        else{
            return null;
        }
    }

    public static void updateUser(String fullname, int age, String username, String avatar){
        //get user profile for logged in user
        CodelotUser profile = getCurrentUserProfile();

        //update and save profile
        profile.setAge(age);
        profile.setAvatarImage(avatar);
        profile.setFullname(fullname);
        profile.setUsername(username);

        ObjectifyService.ofy().save().entity(profile).now();
    }


}
