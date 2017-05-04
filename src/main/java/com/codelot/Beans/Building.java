package com.codelot.Beans;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Jane on 4/19/2017.
 */
/*
Building is used to refer to a programming concept.
A building is what will be loaded up as the user walks into a building in the map (i.e. chooses a concept).
It has a list of Floors that are programming tasks.
As each floor/task is completed, the progress level on the building will increase until it is fully completed.
 */
public class Building {
    boolean started; // indicates whether the Building has been started
    boolean locked; // indicates whether it is locked i.e. all previous buildings have been completed
    ArrayList<Floor> floors; // list of floors/tasks within this building
    int numCompleted; // number of floors/tasks completed within this building
    int currentFloor; // current/last-accessed floor
    private String name; // name of the buildings (ex. 'Loops')
    private int index; // index of the Building within the list of Buildings in Language object
    private HashSet<Integer> completedTaskSet; // set of completed tasks
    private boolean completed;

    // initialize Building
    public Building(){
        // when init, no building has been started therefore started = false
        started = false;
        // in the beginning all buildings are locked. When first building is made, it is set to locked = false
        locked = true;
        // initialize all floors in this buildings (no content in floors yet though)
        floors = makeFloors();
        // number of floors completed is 0
        numCompleted = 0;
        // when init, user is on the first floor
        currentFloor = 0;
        // init set of completed tasks to black set
        completedTaskSet = new HashSet<Integer>();
        // intially building is not completed
        completed = false;

    }

    // read from xml file for content and add to Floor
    public ArrayList<Floor> makeFloors(){
        ArrayList<Floor> tasks = new ArrayList<Floor>();
        return tasks;
    }

    public void addFloor(Floor flr){
        floors.add(flr);
    }

    // returned whether Building has been started or not
    public boolean isStarted() {
        return started;
    }

    // used to set started to true or false
    public void setStarted(boolean started) {
        this.started = started;
    }

    // returns list of floors/tasks in this building
    public ArrayList<Floor> getFloors() {
        return floors;
    }

    // allows to set the list of floor sin this building
    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    // returns whether this building is locked or not
    public boolean isLocked() {
        return locked;
    }

    // used to set lcoked ot true or false
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // returns number of completed floors
    public int getNumCompleted() {
        return numCompleted;
    }

    // sets num of completed floors
    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    // returns current/last-accessed floor
    public int getCurrentFloor() {
        return currentFloor;
    }

    // used to set current floor
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    // returns named of Building
    public String getName() {
        return name;
    }

    // used to set name of Building
    public void setName(String name) {
        this.name = name;
    }

    // returns index of this building within list of buildings in Language class
    public int getIndex() {
        return index;
    }

    // used to set index of this buiilding within list of buildings
    public void setIndex(int index) {
        this.index = index;
    }

    // return hash set of completed tasks
    public HashSet<Integer> getCompletedTaskSet() {
        return completedTaskSet;
    }

    // used to set hash set of completed task
    public void setCompletedTaskSet(HashSet<Integer> completedTaskSet) {
        this.completedTaskSet = completedTaskSet;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
