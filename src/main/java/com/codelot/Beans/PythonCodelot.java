package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
/*
Extended from class Language, this class will have Python programming concepts and tasks.
This is the object that will be loaded when user clicks "Python" on the language selection page.
Each building in the map will be loaded with each object Building from the list of buildings in this class.
 */
public class PythonCodelot extends Language{
    ArrayList<Building> bldgs;

    /* Initializing PythonCodelot by setting default/prelemenary values */
    public PythonCodelot(){
        // Add buildings by reading from PythonContext.xml
        setBuildings(makeBuildings());
        // Since language is initialized when CodelotUser is made and therefore hasnt been clicked on by user, set started to false
        setStarted(false);
        // Set number of completed buildings to 0 since language has not been started
        setNumCompleted(0);
        /*Set current/last-accessed building to 0 (indicating the first building) since when user first enters the language,
    the first and only building they will have access to is the first one.*/
        setCurrentBuilding(0);
        // Since language has just been initialized and hasnt been started, set completed to false.
        setCompleted(false);
    }

    // Method that adds data from xml/json file to buildings list
    private ArrayList<Building> makeBuildings(){
        ArrayList<Building> bldgs = new ArrayList<Building>();
        makeBuilding1("Basics");
        return bldgs;
    }

    // temp method to populate first building
    private void makeBuilding1(String bname){
        Building bldg1 = new Building();
        bldg1.setName(bname);
        Floor flr1 = new Floor();
    }
}
