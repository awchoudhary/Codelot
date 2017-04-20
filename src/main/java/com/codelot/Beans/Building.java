package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
public class Building {
    boolean started;
    boolean currentlyOn;
    boolean locked;
    ArrayList<Floor> floors;
    int numCompleted;
    int currentFloor;
    private String name;
    private int index;

    public Building(){
        started = false;
        currentlyOn = false;
        locked = true;
        floors = makeFloors();
        numCompleted = 0;
        currentFloor = 0;
    }

    public ArrayList<Floor> makeFloors(){
        ArrayList<Floor> tasks = new ArrayList<Floor>();
        return tasks;
    }

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public ArrayList<Floor> getFloors() {
        return floors;
    }

    public void setFloors(ArrayList<Floor> floors) {
        this.floors = floors;
    }

    public boolean isCurrentlyOn() {

        return currentlyOn;
    }

    public void setCurrentlyOn(boolean currentlyOn) {
        this.currentlyOn = currentlyOn;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getNumCompleted() {
        return numCompleted;
    }

    public void setNumCompleted(int numCompleted) {
        this.numCompleted = numCompleted;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
