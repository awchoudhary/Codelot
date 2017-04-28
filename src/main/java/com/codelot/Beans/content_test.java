package com.codelot.Beans;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

/**
 * Created by Jane on 4/28/2017.
 */
public class content_test {
    public static void main (String args[]){
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader( "content/java_content.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray buildings = (JSONArray) jsonObject.get("java");
            int i = 0;
            while (i < buildings.size()){
                JSONObject building = (JSONObject) buildings.get(i);
                String name = (String) building.get("name");
                System.out.println("Name: "+name);

                JSONArray floors = (JSONArray) building.get("floors");
                int n = 0;
                while (n < floors.size()){
                    JSONObject floor = (JSONObject) floors.get(n);
                    String lesson = (String) floor.get("lesson");
                    System.out.println("Lesson: "+lesson);
                    String desc = (String) floor.get("description");
                    System.out.println("Description: "+desc);
                    String baseCode = (String) floor.get("baseCode");
                    System.out.println("Base code: "+baseCode);

                    JSONArray hints = (JSONArray) floor.get("hints");
                    System.out.println("Hints: ");
                    for (Object hint: hints) {
                        System.out.println((String)hint);
                    }

                    JSONArray outputs = (JSONArray) floor.get("expectedOutputs");
                    System.out.println("Outputs: ");
                    for (Object output: outputs) {
                        System.out.println((String)output);
                    }
                    System.out.println("\n\n");
                    n++;
                }

                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
