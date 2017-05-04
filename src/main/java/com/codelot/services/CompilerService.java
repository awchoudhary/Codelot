package com.codelot.services;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by awaeschoudhary on 4/18/17.
 * A class to handle program execution via the Hackerrank API
 */
public class CompilerService {
    public String execute(String source, String testCases) throws IOException {
        if(testCases == null || testCases.isEmpty()){
            testCases = "[\"1\"]";
        }

        String apiKey = "hackerrank|649055-1352|c087072528ca4bc4eb2d9c8d993fcb8971616e9c";
        URL url = new URL("http://api.hackerrank.com/checker/submission.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "source=" + URLEncoder.encode(source, "UTF-8") + "&lang=3&testcases=" + testCases + "&api_key=" + apiKey;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        return response.toString();
    }

    //create a JSON response string based on provided JSON string and expected output
    public static JSONObject createResponse(String input, ArrayList<String> expectedOutputs){
        if(input == null){
            return null;
        }

        //the object to return
        JSONObject response = new JSONObject();

        //responses from Hackerrank are wrapped in an object
        JSONObject object = new JSONObject(input);
        JSONObject result = object.getJSONObject("result");

        if(result.has("compilemessage")){
            String message = result.getString("compilemessage");
            if(!message.equals("")){
                result.put("compilemessage", message);
                result.put("outcome", "false");
                return result;
            }
        }

        if(result.has("stdout")){
            JSONArray stdout = result.getJSONArray("stdout");

            response.put("stdout", stdout);
            response.put("expectedOutputs", expectedOutputs);

            //compare stdout with the expected answer
            if(stdout.length() != expectedOutputs.size()){
                response.put("outcome", "false");
            }
            else{
                for(int i = 0; i < stdout.length(); i++){
                    if(!stdout.getString(i).equals(expectedOutputs.get(i))){
                        response.put("outcome", "false");
                        break;
                    }
                }
            }
            if(!response.has("outcome")){
                response.put("outcome", "true");
            }
        }

        return response;
    }

}
