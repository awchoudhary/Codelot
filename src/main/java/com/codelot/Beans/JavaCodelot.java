package com.codelot.Beans;

import java.util.ArrayList;

/**
 * Created by Jane on 4/19/2017.
 */
public class JavaCodelot extends Language{
    ArrayList<Building> bldgs;

    public JavaCodelot(){
        setBuildings(makeBuildings());
        setStarted(false);
        setNumCompleted(0);
        setCurrentBuilding(0);
        setCompleted(false);
    }

    private ArrayList<Building> makeBuildings(){
        bldgs = new ArrayList<Building>();
        bldgs.add(makeBuilding1("Basics"));
        return bldgs;
    }

    private Building makeBuilding1(String bname){
        Building bldg1 = new Building();
        bldg1.setName(bname);

        Floor flr1 = new Floor();
        flr1.setLesson("Lets learn one of the most essential aspects of Java: how to print!\n");
        flr1.setTaskDescription("Type in 'System.out.println();' with the name of your favorite movie " +
                "in between quotes in between the two parentheses. Then click Execute.");
        bldg1.getFloors().add(flr1);
        flr1.setIndex(bldg1.getFloors().indexOf(flr1));
        flr1.setLocked(false);
        ArrayList<String> hints = new ArrayList<String>();
        hints.add("To print 'Hello, world' we type 'System.out.println(\"Hello, world\");");
        flr1.setHints(hints);

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
