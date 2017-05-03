package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
/*
Extended from class Language, this class will have JavaScript programming concepts and tasks.
This is the object that will be loaded when user clicks "JavaScript" on the language selection page.
Each building in the map will be loaded with each object Building from the list of buildings in this class.
 */
public class JavaScriptCodelot extends Language{

    /* Initializing JavaScriptCodelot by setting default/prelemenary values */
    public JavaScriptCodelot(){
        // Add buildings by reading from JavaScriptContext.xml
        setBuildings(makeBuildings("content/java_content.json", "java"));
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
}
