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

        //get building number
        int numBuilding = Integer.parseInt(obj.getString("numBuilding"));

        //get floors for current building
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();
        Building currentBuilding = profile.getJavaCodelot().getBuildings().get(numBuilding);
        List<Floor> floors = currentBuilding.getFloors();

        //get the expected outputs for the current floor
        ArrayList<String> expectedOutputs = floors.get(currentFloor).getExpectedOutputs();

        //save attempt
        floors.get(currentFloor).addAttempt(sourceText);

        //compile the code and return a response object
        CompilerService service = new CompilerService();

        JSONObject response = CompilerService.createResponse(service.execute(sourceText,
                floors.get(currentFloor).getTestCases(), "3"), expectedOutputs);

        //if successful, add to task set for the building
        if(response.get("outcome").equals("true")){
            currentBuilding.getCompletedTaskSet().add(currentFloor);

            //unlock next floor if current floor is not last floor.
            if(currentFloor < floors.size()-1){
                floors.get(currentFloor + 1).setLocked(false);
            }
        }

        //add progress to response
        //progress = number of completed tasks / total tasks * 100
        int progress = (int)((((double) currentBuilding.getCompletedTaskSet().size())/floors.size()) * 100);
        // if all tasks have been completed, mark building as completed
        if (progress >= 100){
            if (currentBuilding.isCompleted() == false){
                currentBuilding.setCompleted(true);
                if (numBuilding+1 < profile.getJavaCodelot().getBuildings().size()) {
                    profile.getJavaCodelot().getBuildings().get(numBuilding+1).setLocked(false);
                }
//                else {
//                    c_user.getJavaCodelot().setCompleted(true);
//                }
            }
        }
        response.put("progress", progress);

        //save changes
        ObjectifyService.ofy().save().entity(profile).now();
        return response.toString();
    }

    /* Function used when directing from map to tasks page when there is no floor number passed in
    // Default floor number is set to last-accessed floor
    */
    @RequestMapping("/getJavaTasksPage")
    public ModelAndView tasksContent(@RequestParam("numBuilding") int numBuilding){
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(numBuilding);
        int currentFloorNumber = currbldg.getCurrentFloor();
        return javaTasks(currentFloorNumber, numBuilding);
    }

    /* Function used when moving from one floor to another when floor number IS passed in
    */
    @RequestMapping("/getJavaTask")
    public ModelAndView moveFloors(@RequestParam("floorNum") int floorNum, @RequestParam("numBuilding") int numBuilding){
        return javaTasks(floorNum, numBuilding);
    }

    private ModelAndView javaTasks(int floorNum, int numBuilding) {
        //Load values for user
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();
        Building currbldg = c_user.getJavaCodelot().getBuildings().get(numBuilding);
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
        int progress = (int)((((double) currbldg.getCompletedTaskSet().size())/floors.size()) * 100);
        // if all tasks have been completed, mark building as completed
        if (progress >= 100){
            if (currbldg.isCompleted() == false){
                currbldg.setCompleted(true);
                if (numBuilding+1 < c_user.getJavaCodelot().getBuildings().size()) {
                    c_user.getJavaCodelot().getBuildings().get(numBuilding+1).setLocked(false);
                }
//                else {
//                    c_user.getJavaCodelot().setCompleted(true);
//                }
            }
        }

        currbldg.setCurrentFloor(floorNum); // update current floor
        ObjectifyService.ofy().save().entity(c_user).now(); // save user

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("buildingName", currbldg.getName());
        model.addObject("numBuilding", numBuilding);
        model.addObject("floors", floors);
        model.addObject("taskDesc", currentFloor.getTaskDescription());
        model.addObject("warning", warning);
        model.addObject("hints", currentFloor.getHints());
        model.addObject("attempts", attempts);
        model.addObject("lesson", currentFloor.getLesson());
        model.addObject("progress", progress);
        model.addObject("baseCode", floors.get(floorNum).getBaseCode());
        model.addObject("currentFloor", floorNum);

        return model;
    }

}
