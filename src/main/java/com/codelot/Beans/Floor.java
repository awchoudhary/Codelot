package com.codelot.Beans;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jane on 4/19/2017.
 */
/*
This class represents a programming task. This is what will be loaded when a building and/or a floor is clicked.
It has the content of the task: lesson, prompt/task, base/skeleton code, expected output.
 */
public class Floor {
    // indicates whether floor is locked (i.e. all previous floors have been completed)
    private boolean locked;
    // this is the prompt/description of the actual task (i.e. what the user needs to do)
    private String taskDescription;
    // this is the background info the user needs to know in order to complete the task
    private String lesson;
    // list of previous attempts/answers user submitted
    LinkedList<String> attempts = new LinkedList<String>();
    // list of all hints available for this task
    ArrayList<String> hints = new ArrayList<String>();
    // index within the list of floors in building
    int index;
    // base/skeleton code within/around which the user can write their code
    private String baseCode;
    // list of expected outputs for this task
    private ArrayList<String> expectedOutputs = new ArrayList<>();
    private String testCases;

    // initializing locked to true (because all are locked in the beginning)
    public Floor(){
        locked = true;
    }

    // Adds user's answer submission to list of attempts from most to least recent
    public void addAttempt(String input){
        attempts.addFirst(input);
    }

    // returns the task description
    public String getTaskDescription() {
        return taskDescription;
    }

    // sets the task description
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    // returns the list of hints
    public ArrayList<String> getHints() {
        return hints;
    }

    // sets the list of hints to the passed in list
    public void setHints(ArrayList<String> hints) {
        this.hints = hints;
    }

    // returns the list of all previous attempts to list of attempts from most to least recent
    public LinkedList<String> getAttempts() {
        return attempts;
    }

    // sets the list of attempts to passed in list
    public void setAttempts(LinkedList<String> attempts) {
        this.attempts = attempts;
    }

    // returns whether floor is locked or not
    public boolean isLocked() {
        return locked;
    }

    // sets locked to true or false
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    // gets index
    public int getIndex() {
        return index;
    }

    // sets index
    public void setIndex(int index) {
        this.index = index;
    }

    // gets lesson
    public String getLesson() {
        return lesson;
    }

    // sets lesson description
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    // gets the base/skeleton code
    public String getBaseCode() {
        return baseCode;
    }

    // sets the base/skeleton code
    public void setBaseCode(String baseCode) {
        this.baseCode = baseCode;
    }

    // returns the list of all expected/acceptable outputs
    public ArrayList<String> getExpectedOutputs() {
        return expectedOutputs;
    }

    // sets the list of expected outputs
    public void setExpectedOutputs(ArrayList<String> expectedOutputs) {
        this.expectedOutputs = expectedOutputs;
    }

    public String getTestCases() {
        return testCases;
    }

    public void setTestCases(String testCases) {
        this.testCases = testCases;
    }
}
