package com.codelot.controller;

import com.codelot.Beans.Building;
import com.codelot.Beans.CodelotUser;
import com.codelot.Beans.Floor;
import com.codelot.services.CodelotUserService;
import com.codelot.services.CompilerService;
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

@Controller
@RequestMapping("/task")
public class TasksController {

    @RequestMapping(value = "/execute", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody String execute(@RequestBody String source) throws IOException {
        //parse parameter to get source
        JSONObject obj = new JSONObject(source);

        //source code for attempt
        String sourceText = obj.getString("source");

        //current task floor index
        int currentFloor = Integer.parseInt(obj.getString("currentFloor"));

        //get floors for current building
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();
        Building currentBuilding = profile.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currentBuilding.getFloors();

        //get the expected outputs for the current floor
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
        ObjectifyService.ofy().save().entity(profile).now();
        return response.toString();
    }

    @RequestMapping("/getJavaTasksPage")
    public ModelAndView javaTasks() {
        //Load values for user
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currbldg.getFloors();
        String warning = "";
        Floor currentFloor = floors.get(currbldg.getCurrentFloor());

        //get the latest 5 attempts for the user
        List<String> attempts = new ArrayList<>();
        for (int i = 0; i < currentFloor.getAttempts().size(); i++){
            if(attempts.size() < 5 && !currentFloor.getAttempts().get(i).isEmpty()){
                attempts.add(currentFloor.getAttempts().get(i));
            }
        }

        //progress = number of completed tasks / total tasks * 100
        int prog = (int)((((double) currbldg.getCompletedTaskSet().size())/floors.size()) * 100);

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("floors", floors);
        model.addObject("taskDesc", currentFloor.getTaskDescription());
        model.addObject("warning", warning);
        model.addObject("hints", currentFloor.getHints());
        model.addObject("attempts", attempts);
        model.addObject("lesson", currentFloor.getLesson());
        model.addObject("progress", prog);
        model.addObject("baseCode", currentFloor.getBaseCode());
        model.addObject("currentFloor", currbldg.getCurrentFloor());

        return model;
    }

    @RequestMapping("/getJavaTask")
    public ModelAndView javaTasks(@RequestParam("floorNum") int floorNum) {
        //Load values for user
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(0);
        List<Floor> floors = currbldg.getFloors();
        String warning = "";
        int currentFloorNumber = currbldg.getCurrentFloor();

        // if coming from map page, load current floor/task of user
        if (floorNum == -1){ // means it is redirected from map page
            floorNum = currentFloorNumber;
        }
        //if the floor is locked, create a warning and navigate to current task
        else if (floors.get(floorNum).isLocked()) {
            warning = "NOTE: Floor " + (floorNum + 1) + " is locked. Please pass through all lower floors to access this one.";
            floorNum = currentFloorNumber;
        }

        Floor currentFloor = floors.get(floorNum);

        //get the latest 5 attempts for the user
        List<String> attempts = new ArrayList<>();
        for (int i = 0; i < currentFloor.getAttempts().size(); i++){
            if(attempts.size() < 5 && !currentFloor.getAttempts().get(i).isEmpty()){
                attempts.add(currentFloor.getAttempts().get(i));
            }
        }

        //progress = number of completed tasks / total tasks * 100
        int prog = (int)((((double) currbldg.getCompletedTaskSet().size())/floors.size()) * 100);

        currbldg.setCurrentFloor(floorNum); // update current floor
        ObjectifyService.ofy().save().entity(c_user).now(); // save user

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("floors", floors);
        model.addObject("taskDesc", currentFloor.getTaskDescription());
        model.addObject("warning", warning);
        model.addObject("hints", currentFloor.getHints());
        model.addObject("attempts", attempts);
        model.addObject("lesson", currentFloor.getLesson());
        model.addObject("progress", prog);
        model.addObject("baseCode", floors.get(floorNum).getBaseCode());
        model.addObject("currentFloor", floorNum);

        return model;
    }

}
