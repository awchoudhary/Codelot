package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
public class JavaScriptCodelot extends Language{

    public JavaScriptCodelot(){
        setBuildings(makeBuildings());
        setStarted(false);
        setNumCompleted(0);
        setCurrentBuilding(0);
        setCompleted(false);
    }

    private ArrayList<Building> makeBuildings(){
        ArrayList<Building> bldgs = new ArrayList<Building>();
        makeBuilding1("Basics");
        return bldgs;
    }

    private void makeBuilding1(String bname){
        Building bldg1 = new Building();
        bldg1.setName(bname);
        Floor flr1 = new Floor();
    }
}
