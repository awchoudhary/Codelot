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
@RequestMapping("/tasks")
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

        //the language code for the task
        String languageCode = obj.getString("languageCode");

        //get floors for current building
        CodelotUser profile = CodelotUserService.getCurrentUserProfile();
        Building currentBuilding = getBuildings(languageCode).get(numBuilding);
        List<Floor> floors = currentBuilding.getFloors();

        //get the expected outputs for the current floor
        ArrayList<String> expectedOutputs = floors.get(currentFloor).getExpectedOutputs();

        //save attempt
        floors.get(currentFloor).addAttempt(sourceText);

        //compile the code and return a response object
        CompilerService service = new CompilerService();

        JSONObject response = CompilerService.createResponse(service.execute(sourceText,
                floors.get(currentFloor).getTestCases(), languageCode), expectedOutputs);

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
            if (!currentBuilding.isCompleted()){
                currentBuilding.setCompleted(true);
                if (numBuilding+1 < getBuildings(languageCode).size()) {
                    getBuildings(languageCode).get(numBuilding+1).setLocked(false);
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

    /* controller method to navigate to a task
    */
    @RequestMapping("/task")
    public ModelAndView moveFloors(@RequestParam("languageCode") String languageCode,
                                   @RequestParam("numBuilding") int numBuilding,
                                   @RequestParam("floorNum") int floorNum){

        //if floor number is -1, we are navigating from the map page and should therefore get the current floor for the user
        if(floorNum == -1){
            floorNum = getBuildings(languageCode).get(numBuilding).getCurrentFloor();
        }

        return javaTasks(languageCode, numBuilding, floorNum);
    }


    //helper method that sets all model arguments for the task page
    private ModelAndView javaTasks(String languageCode, int numBuilding, int floorNum) {
        //Load values for user
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();
        Building currentBuilding = getBuildings(languageCode).get(numBuilding);
        List<Floor> floors = currentBuilding.getFloors();
        String warning = "";

        //if the floor is locked, create a warning and navigate to current task
        if (floors.get(floorNum).isLocked()) {
            warning = "NOTE: Floor " + (floorNum + 1) + " is locked. Please pass through all lower floors to access this one.";
            floorNum = currentBuilding.getCurrentFloor();
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
        int progress = (int)((((double) currentBuilding.getCompletedTaskSet().size())/floors.size()) * 100);
        // if all tasks have been completed, mark building as completed
        if (progress >= 100){
            if (!currentBuilding.isCompleted()){
                currentBuilding.setCompleted(true);
                if (numBuilding+1 < getBuildings(languageCode).size()) {
                    getBuildings(languageCode).get(numBuilding+1).setLocked(false);
                }
//                else {
//                    c_user.getJavaCodelot().setCompleted(true);
//                }
            }
        }

        currentBuilding.setCurrentFloor(floorNum); // update current floor
        ObjectifyService.ofy().save().entity(c_user).now(); // save user

        ModelAndView model = new ModelAndView("WEB-INF/pages/TaskPage");
        model.addObject("buildingName", currentBuilding.getName());
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
        model.addObject("languageCode", languageCode);

        return model;
    }

    //return all buildings for the given languageCode
    //the language codes are: Java = 3, Python 3 = 30, JavaScript = 20
    private ArrayList<Building> getBuildings(String languageCode){
        //get logged in user
        CodelotUser c_user = CodelotUserService.getCurrentUserProfile();

        //return buildings for java map
        if(languageCode.equals("3")){
            return c_user.getJavaCodelot().getBuildings();
        }else if(languageCode.equals("30")){ //return buildings for python map
            return c_user.getPythonCodelot().getBuildings();
        }else{ //must be JavaScript map buildings
            return c_user.getJavaScriptCodelot().getBuildings();
        }

    }


}
