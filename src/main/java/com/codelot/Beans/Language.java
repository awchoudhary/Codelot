package com.codelot.Beans;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

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
        try {

            File fXmlFile = new File("content/java_content.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //optional, but recommended
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList buildingsList = doc.getElementsByTagName("element"); // list of buildings

            System.out.println("----------------------------");

            // iterate through each building in buildings list
            for (int b = 0; b < buildingsList.getLength(); b++) {
                Building newBldg = new Building();

                Node building = buildingsList.item(b); // one building
                System.out.println("\nCurrent Element :" + building.getNodeName()); // = "element"

                if (building.getNodeType() == Node.ELEMENT_NODE) {

                    Element buildingElem = (Element) building; // convert to xml element

                    // get name of building if it is not null
                    if (buildingElem.getElementsByTagName("name").equals(null) == false){
                        System.out.println(buildingElem.getElementsByTagName("name").toString());
                        System.out.println(buildingElem.getElementsByTagName("name").getLength());
                        String name = buildingElem.getElementsByTagName("name")
                                .item(0).getTextContent();
                        System.out.println(name);
                        newBldg.setName(name);
                    }
                    else{
                        System.out.println("name null");
                    }

                    // get child nodes of <floors> => should be element
                    NodeList floors = buildingElem.getElementsByTagName("floors")
                            .item(0).getChildNodes();

                    // iterate through list of floors
                    for (int f=0; f<floors.getLength(); f++){
                        Floor newFloor = new Floor();
                        // should be "element"
                        Node floor = floors.item(f);

                        if (floor.getNodeType() == Node.ELEMENT_NODE) {
                            // floor element with baseCode, lesson, etc.
                            Element floorElem = (Element) floor;
                            // element inside floors

                            String baseCode =
                                    floorElem.getElementsByTagName("baseCode").
                                            item(0).getTextContent();
                            System.out.println(baseCode);
                            newFloor.setBaseCode(baseCode);

                            String description =
                                    floorElem.getElementsByTagName("description").
                                            item(0).getTextContent();
                            System.out.println(description);
                            newFloor.setTaskDescription(description);

                            String lesson =
                                    floorElem.getElementsByTagName("lesson").
                                            item(0).getTextContent();
                            System.out.println(lesson);
                            newFloor.setLesson(lesson);

                            // get expected outputs list
                            NodeList outputs = floorElem.getElementsByTagName("expectedOutputs")
                                    .item(0).getChildNodes();
                            ArrayList<String> outs = new ArrayList<String>();
                            // add each output to list of outputs
                            for (int eo = 0; eo < outputs.getLength(); eo++){
                                Node output = outputs.item(eo);

                                if (output.getNodeType() == Node.ELEMENT_NODE) {
                                    // element tag with each output
                                    Element outputElem = (Element) output;
                                    // add to outputs list
                                    outs.add(outputElem.getTextContent());
                                    System.out.println(outputElem.getTextContent());
                                }
                            }
                            newFloor.setExpectedOutputs(outs);

                            // get hints list
                            NodeList hints = floorElem.getElementsByTagName("hints")
                                    .item(0).getChildNodes();
                            ArrayList<String> newHints = new ArrayList<String>();
                            // add each hint to list of hints
                            for (int h = 0; h < hints.getLength(); h++){
                                Node hint = hints.item(h);

                                if (hint.getNodeType() == Node.ELEMENT_NODE) {
                                    // element tag with each hint
                                    Element hintElem = (Element) hint;
                                    // add to hints list
                                    newHints.add(hintElem.getTextContent());
                                    System.out.println(hintElem.getTextContent());
                                }
                            }
                            newFloor.setHints(newHints);

//                            // get testCases
//                            if (floorElem.getElementsByTagName("testCases").equals(null) == false
//                                    && floorElem.getElementsByTagName("testCases").item(0).equals(null) == false
//                                    && floorElem.getElementsByTagName("testCases")
//                                    .item(0).getChildNodes().equals(null) == false){
//                            if(floorElem){
//                                NodeList testCases = floorElem.getElementsByTagName("testCases")
//                                        .item(0).getChildNodes();
//                                Node test = testCases.item(0);
//                                if (test.getNodeType() == Node.ELEMENT_NODE) {
//                                    // element tag with each hint
//                                    Element testElem = (Element) test;
//                                    // add each test case to list of test cases
//                                    newFloor.setTestCases(testElem.getTextContent());
//                                    System.out.println(testElem.getTextContent());
//                                }
//                            }
                        }

                        newBldg.addFloor(newFloor);
                    }
                }
                buildings.add(newBldg);
            }
        } catch (Exception e) {
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
