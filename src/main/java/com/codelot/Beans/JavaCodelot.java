package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */

/*
Extended from class Language, this class will have Java programming concepts and tasks.
This is the object that will be loaded when user clicks "Java" on the language selection page.
Each building in the map will be loaded with each object Building from the list of buildings in this class.
 */
public class JavaCodelot extends Language{
    ArrayList<Building> bldgs;

    /* Initializing JavaCodelot by setting default/prelemenary values */
    public JavaCodelot(){
        // Add buildings by reading from JavaContext.xml
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
        bldgs = new ArrayList<Building>();
        bldgs.add(makeBuilding1("Basics"));
        return bldgs;
    }

    // temp method to populate Java building #1 with floors 1, 2, and 3.
    private Building makeBuilding1(String bname){
        Building bldg1 = new Building();
        bldg1.setName(bname);

        Floor flr1 = new Floor();
        flr1.setLesson("Lets learn one of the most essential aspects of Java: how to print!\n");
        flr1.setTaskDescription("Type in 'System.out.print();' with the words Hello World! " +
                "in between quotes in between the two parentheses. Then click Execute.");
        bldg1.getFloors().add(flr1);
        flr1.setIndex(bldg1.getFloors().indexOf(flr1));
        flr1.setLocked(false);
        ArrayList<String> hints = new ArrayList<String>();
        hints.add("To print 'Hello, world' we type 'System.out.println(\"Hello, world\");");
        flr1.setHints(hints);
        flr1.setBaseCode("public class Solution{\n" +
                            "\tpublic static void main(String[] args){\n" +
                            "\t\t//Your code goes under here\n" +
                            "\t}\n" +
                            "}");
        ArrayList<String> expectedOutputs = new ArrayList<>();
        expectedOutputs.add("Hello World!");
        flr1.setExpectedOutputs(expectedOutputs);

        Floor flr2 = new Floor();
        flr2.setLesson("Theres also another type of print: System.out.print(). " +
                        "While System.out.println() prints the given input and moves to a new line" +
                        " so that any new input will begin in the next line, " +
                        "System.out.print() prints the input on the same line. Lets try it out! \n");
        flr2.setTaskDescription("Type in 'System.out.print();' with your first name, then 'System.out.println();'" +
                "with your last name, and then 'System.out.println();' with your age.");
        bldg1.getFloors().add(flr2);
        flr2.setIndex(bldg1.getFloors().indexOf(flr2));

        Floor flr3 = new Floor();
        flr3.setLesson("Theres no computation without data types so lets " +
                "explore some of the data types available in Java. Here we learn int. \n");
        flr3.setTaskDescription("Type in 'System.out.println();' with 1 in between the two parentheses." +
                "Then type in 'System.out.println();' with 1+4 in between the two parentheses." +
                "Then click Execute!");
        bldg1.getFloors().add(flr3);
        flr3.setIndex(bldg1.getFloors().indexOf(flr3));

        return bldg1;
    }
}
