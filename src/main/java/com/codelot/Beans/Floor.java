package com.codelot.Beans;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jane on 4/19/2017.
 */
public class Floor {
    private boolean locked;
    private boolean currentlyOn;
    String taskDescription;
    String lesson;
    String skeletonCode;
    private String correctOutput;
    LinkedList<String> attempts = new LinkedList<String>();
    ArrayList<String> hints = new ArrayList<String>();
    int index;
    private String baseCode;
    private ArrayList<String> expectedOutputs = new ArrayList<>();

    public Floor(){
        currentlyOn = false;
        locked = true;
    }

    public void addAttempt(String input){
        attempts.addFirst(input);
    }

    public boolean isCurrentlyOn() {
        return currentlyOn;
    }

    public void setCurrentlyOn(boolean currentlyOn) {
        this.currentlyOn = currentlyOn;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getSkeletonCode() {
        return skeletonCode;
    }

    public void setSkeletonCode(String skeletonCode) {
        this.skeletonCode = skeletonCode;
    }

    public ArrayList<String> getHints() {
        return hints;
    }

    public void setHints(ArrayList<String> hints) {
        this.hints = hints;
    }

    public LinkedList<String> getAttempts() {
        return attempts;
    }

    public void setAttempts(LinkedList<String> attempts) {
        this.attempts = attempts;
    }

    public String getCorrectOutput() {
        return correctOutput;
    }

    public void setCorrectOutput(String correctOutput) {
        this.correctOutput = correctOutput;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getLesson() {
        return lesson;
    }
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getBaseCode() {
        return baseCode;
    }
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    public ArrayList<String> getExpectedOutputs() {
        return expectedOutputs;
    }

    public void setExpectedOutputs(ArrayList<String> expectedOutputs) {
        this.expectedOutputs = expectedOutputs;
    }
}
