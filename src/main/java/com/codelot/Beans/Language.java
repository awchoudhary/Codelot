package com.codelot.Beans;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */

/*
This class will have a list of buildings (programming concepts) which will have a list of
floors (programming tasks).
This abstract class will serve as a template for all languages Codelot can offer.

Each user has each of the offered languages initialized when created.
As the user progresses through each language, the database gets updated.
 */
public abstract class Language {

    private boolean started; // indicates whether the language has been started (clicked on) by user.
    private ArrayList<Building> buildings; // list of buildings that exist within this language
    private int numCompleted; // number of completed buildings
    private int currentBuilding; // index of the current/last-accessed building
    private boolean completed; // indicates whether the whole language has been completed or not

    public Language(){

    }

    // Adds a building to the existing list of building => building must be initialized with floors (with content within floors)
    public ArrayList<Building> addBuilding(Building bldg){
        buildings.add(bldg);
        return buildings;
    }

    // Returns the percentage of the language completed by the user
    public double getProgressPercent(){
        return numCompleted/getNumBuildings() * 100;
    }

    // When moving on to next building, this function is called to increment numCompleted
    public void nextBuilding(){
        numCompleted++;
        if (numCompleted >= getNumBuildings()){
            completed = true;
        }
    }

    // Method that adds data from xml/json file to buildings list
    protected ArrayList<Building> makeBuildings(String filename, String lang){
        buildings = new ArrayList<Building>();
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader( filename));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray json_buildings = (JSONArray) jsonObject.get(lang);

            int i = 0;
            while (i < json_buildings.size()){
                Building building = new Building();

                // get json building
                JSONObject json_building = (JSONObject) json_buildings.get(i);
                // get name of building from json
                String name = (String) json_building.get("name");
                // set name of building
                building.setName(name);
                // unlock the first building
                if(i == 0){
                    building.setLocked(false);
                }

                // get floors for this building
                JSONArray floors = (JSONArray) json_building.get("floors");
                int n = 0;
                // get floor info and save it
                while (n < floors.size()){
                    Floor floor = new Floor();
                    // unlock first floor
                    if(n == 0){
                        floor.setLocked(false);
                    }

                    JSONObject json_floor = (JSONObject) floors.get(n);
                    // Get and save lesson, description, base code for this floor and save.
                    String lesson = (String) json_floor.get("lesson");
                    floor.setLesson(lesson);
                    String desc = (String) json_floor.get("description");
                    floor.setTaskDescription(desc);
                    String baseCode = (String) json_floor.get("baseCode");
                    floor.setBaseCode(baseCode);

                    // Get hints for this floor and save to list. Then save list.
                    JSONArray hints = (JSONArray) json_floor.get("hints");
                    ArrayList<String> save_hints = new ArrayList<String>();
                    for (Object hint: hints) {
                        save_hints.add((String)hint);
                    }
                    floor.setHints(save_hints);

                    // Get expected outputs for this floor and save to list. Then save list.
                    JSONArray outputs = (JSONArray) json_floor.get("expectedOutputs");
                    ArrayList<String> save_outputs = new ArrayList<String>();
                    for (Object output: outputs) {
                        save_outputs.add((String)output);
                    }
                    floor.setExpectedOutputs(save_outputs);

                    //get test cases if applicable
                    if(json_floor.containsKey("testCases")){
                        floor.setTestCases((String)json_floor.get("testCases"));
                    }

                    building.addFloor(floor);
                    floor.setIndex(building.getFloors().indexOf(floor));
                    n++;
                }

                // Add populated building into list of buildings
                buildings.add(building);
                building.setIndex(buildings.indexOf(building));
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return buildings;
    }

    // Returns the total number of buildings within this language
    public int getNumBuildings(){
        return buildings.size();
    }

    // Get whether lang has started or not
    public boolean isStarted() {
        return started;
    }

    // Set lang has been started
    public void setStarted(boolean started) {
        this.started = started;
    }

    // Get the list of buildings
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    // Set the list of buildings with new list of buildings
    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    // Get the number of completed buildings
    public int getNumCompleted() {
        return numCompleted;
    }

    // Set the number of completed buildings
    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    // Get index of current/last-accessed building
    public int getCurrentBuilding() {
        return currentBuilding;
    }

    // Set the current/most recently accessed building
    public void setCurrentBuilding(int currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    // Returns whether language is completed or not
    public boolean isCompleted() {
        return completed;
    }

    // Set whether language has been completed
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
