package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
public abstract class Language {

    private boolean started;
    private ArrayList<Building> buildings;
    private int numCompleted;
    private int currentBuilding;
    private boolean completed;

    public Language(){

    }

//    public Language (ArrayList<Building> bldgs){
//        this.buildings = bldgs;
//        started = false;
//        numBuildings = bldgs.size();
//        numCompleted = 0;
//    }

    public ArrayList<Building> addBuilding(Building bldg){
        buildings.add(bldg);
        return buildings;
    }

    public double getProgressPercent(){
        return numCompleted/getNumBuildings() * 100;
    }

    public void nextBuilding(){
        numCompleted++;
        if (numCompleted >= getNumBuildings()){
            completed = true;
        }
    }

    public int getNumBuildings(){
        return buildings.size();
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getNumCompleted() {
        return numCompleted;
    }

    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    public int getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(int currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
