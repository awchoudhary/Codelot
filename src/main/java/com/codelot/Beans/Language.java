package com.codelot.Beans;

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
