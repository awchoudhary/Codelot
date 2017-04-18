package com.codelot.services;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by awaeschoudhary on 4/18/17.
 * A class to handle program execution via the Hackerrank API
 */
public class CompilerService {
    public String execute(String source) throws IOException {
        String apiKey = "hackerrank|649055-1352|c087072528ca4bc4eb2d9c8d993fcb8971616e9c";
        URL url = new URL("http://api.hackerrank.com/checker/submission.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "source=" + source + "&lang=3&testcases=[\"1\"]&api_key=" + apiKey;

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
}
