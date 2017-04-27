package com.codelot.controller;

import com.codelot.Beans.Building;
import com.codelot.Beans.CodelotUser;
import com.codelot.Beans.Floor;
import com.codelot.services.CodelotUserService;
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
 * Created by awaeschoudhary on 3/31/17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView("WEB-INF/pages/home");
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

    public User getGoogleUser(){
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        return user;
    }

    public CodelotUser getCodelotUser(){
        User user = getGoogleUser();
        //check to see if user already in database, if not need to create profile
        List<CodelotUser> users = ObjectifyService.ofy()
                .load()
                .type(CodelotUser.class)
                .filter("user_id",user.getUserId())
                .list();
        long userid = users.get(0).getId();

        CodelotUser c_user = ObjectifyService.ofy().load().type(CodelotUser.class).id(userid).now();
        return c_user;
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
        CodelotUser c_user = getCodelotUser();
        ArrayList<Building> buildings = c_user.getJavaCodelot().getBuildings();
        int progress = (int)((((double) c_user.getJavaCodelot().getNumCompleted())/buildings.size()) * 100);

        ModelAndView model = new ModelAndView("WEB-INF/pages/map");
        model.addObject("fullName", c_user.getFullname());
        model.addObject("username", c_user.getUsername());
        model.addObject("avatar", c_user.avatarImage);
        model.addObject("email", c_user.getUser_email());
        model.addObject("age", c_user.getAge());
        model.addObject("progress", progress);
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

    @RequestMapping("/TaskPage")
    public String taskPage(){
        return "WEB-INF/pages/TaskPage";
    }

    @RequestMapping(value = "/execute", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String execute(@RequestBody String source) throws IOException {
        //parse parameter to get source
        JSONObject obj = new JSONObject(source);

        String sourceText = obj.getString("source");

        //get the expected outputs
        int currentFloor = Integer.parseInt(obj.getString("currentFloor"));
        CodelotUser c_user = getCodelotUser();
        Building currentBuilding = c_user.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currentBuilding.getFloors();
        ArrayList<String> expectedOutputs = floors.get(currentFloor).getExpectedOutputs();

        //save attempt
        floors.get(currentFloor).addAttempt(sourceText);

        //compile the code and return a response object
        CompilerService service = new CompilerService();

        JSONObject response = CompilerService.createResponse(service.execute(sourceText), expectedOutputs);

        //if successful, add to task set for the building
        if(response.get("outcome").equals("true")){
            currentBuilding.getCompletedTaskSet().add(currentFloor);

            //unlock next floor if current floor is not last floor.
            if(currentFloor < floors.size()-1){
                floors.get(currentFloor + 1).setLocked(false);
            }
        }

        //add progress to response
        int progress = (int)((((double) currentBuilding.getCompletedTaskSet().size())/floors.size()) * 100);
        response.put("progress", progress);

        //save changes
        ObjectifyService.ofy().save().entity(c_user).now();
        return response.toString();
    }

    @RequestMapping("/getJavaTasksPage")
    public ModelAndView javaTasks() {
        //Load values for user
        CodelotUser c_user = getCodelotUser();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currbldg.getFloors();
        String warning = "";
        int currFlr = currbldg.getCurrentFloor();
        String lesson = floors.get(currFlr).getLesson();
        String task = floors.get(currFlr).getTaskDescription();
        ArrayList<String> hints = floors.get(currFlr).getHints();
        List<String> attempts = new ArrayList<>();
        int attSize;
        if (floors.get(currFlr).getAttempts().size() < 5){
            attSize = floors.get(currFlr).getAttempts().size();
        }
        else {
            attSize = 5;
        }
        for (int i = 0; i<attSize; i++){
            if(floors.get(currFlr).getAttempts().get(i).isEmpty() == false){
                attempts.add(floors.get(currFlr).getAttempts().get(i));
            }
        }
        int prog = (int)((((double) currbldg.getCompletedTaskSet().size())/floors.size()) * 100);

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("floors", floors);
        model.addObject("taskDesc", task);
        model.addObject("warning", warning);
        model.addObject("hints", hints);
        model.addObject("attempts", attempts);
        model.addObject("lesson", lesson);
        model.addObject("progress", prog);
        model.addObject("baseCode", floors.get(currFlr).getBaseCode());
        model.addObject("currentFloor", currFlr);

        return model;
    }

    @RequestMapping("/getJavaTask")
    public ModelAndView javaTasks(@RequestParam("floorNum") int floorNum) {
        //Load values for user
        CodelotUser c_user = getCodelotUser();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currbldg.getFloors();
        String warning = "";
        int currFlr = currbldg.getCurrentFloor();

        // if coming from map page, load current floor/task of user
        if (floorNum == -1){ // means it is redirected from map page
            floorNum = currFlr;
        }
        else if (floors.get(floorNum).isLocked() == true) {
            warning = "NOTE: Floor " + (floorNum + 1) + " is locked. Please pass through all lower floors to access this one.";
            floorNum = currFlr;
        }

        String lesson = floors.get(floorNum).getLesson();
        String task = floors.get(floorNum).getTaskDescription();
        ArrayList<String> hints = floors.get(floorNum).getHints();
        List<String> attempts = new ArrayList<>();
        int attSize;
        if (floors.get(floorNum).getAttempts().size() < 5){
            attSize = floors.get(floorNum).getAttempts().size();
        }
        else {
            attSize = 5;
        }
        for (int i = 0; i<attSize; i++){
            if(floors.get(floorNum).getAttempts().get(i).isEmpty() == false){
                attempts.add(floors.get(floorNum).getAttempts().get(i));
            }
        }
        int prog = (int)((((double) currbldg.getCompletedTaskSet().size())/floors.size()) * 100);
        currbldg.setCurrentFloor(floorNum); // update current floor
        ObjectifyService.ofy().save().entity(c_user).now(); // save user

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("floors", floors);
        model.addObject("taskDesc", task);
        model.addObject("warning", warning);
        model.addObject("hints", hints);
        model.addObject("attempts", attempts);
        model.addObject("lesson", lesson);
        model.addObject("progress", prog);
        model.addObject("baseCode", floors.get(floorNum).getBaseCode());
        model.addObject("currentFloor", floorNum);

        return model;
    }
}
