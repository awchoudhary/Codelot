package com.codelot.Beans;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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
    ArrayList<Building> buildings;

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
        buildings = new ArrayList<Building>();
        JSONParser parser = new JSONParser();

        try {
            final String dir = System.getProperty("user.dir");
            System.out.println("----------------------CURRENT DIRECTORY-------------");
            System.out.println("current dir = " + dir);

            Object obj = parser.parse(new FileReader( "content/java_content.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray json_buildings = (JSONArray) jsonObject.get("java");

            int i = 0;
            while (i < json_buildings.size()){
                Building building = new Building();

                JSONObject json_building = (JSONObject) json_buildings.get(i);
                String name = (String) json_building.get("name");
                building.setName(name);
                if(i == 0){
                    building.setLocked(false);
                }

                JSONArray floors = (JSONArray) json_building.get("floors");
                int n = 0;
                while (n < floors.size()){
                    Floor floor = new Floor();
                    if(n == 0){
                        floor.setLocked(false);
                    }

                    JSONObject json_floor = (JSONObject) floors.get(n);
                    String lesson = (String) json_floor.get("lesson");
                    floor.setLesson(lesson);
                    String desc = (String) json_floor.get("description");
                    floor.setTaskDescription(desc);
                    String baseCode = (String) json_floor.get("baseCode");
                    floor.setBaseCode(baseCode);

                    JSONArray hints = (JSONArray) json_floor.get("hints");
                    ArrayList<String> save_hints = new ArrayList<String>();
                    for (Object hint: hints) {
                        save_hints.add((String)hint);
                    }
                    floor.setHints(save_hints);

                    JSONArray outputs = (JSONArray) json_floor.get("expectedOutputs");
                    ArrayList<String> save_outputs = new ArrayList<String>();
                    for (Object output: outputs) {
                        save_outputs.add((String)output);
                    }
                    floor.setExpectedOutputs(save_outputs);

                    building.addFloor(floor);
                    floor.setIndex(building.getFloors().indexOf(floor));
                    n++;
                }

                buildings.add(building);
                building.setIndex(buildings.indexOf(building));
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return buildings;
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
