/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.net.HttpURLConnection;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lap5508
 */
public class GameModel {
    private String connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard.json";
    private ArrayList<String> currLeaders;
    
    public GameModel () {
        currLeaders = read();
    }
    
    //this will take in the curr users score / name and return a boolean if they are on the leaderboard
    public boolean submitScore (ArrayList<String> currNameScore) {
        
        return false;
    }
    
    private HttpURLConnection connectToDB(String method) throws Exception{
        
        URL urlConnection = new URL(connectionURL);
        HttpURLConnection con = (HttpURLConnection) urlConnection.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod(method);
            
        return con;
    }
    
    public void write (ArrayList<String> leaderboard) {
        try {
            connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard.json";
            HttpURLConnection connection = connectToDB("POST");
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            Gson gson = new Gson();
            String data = gson.toJson(leaderboard);
            
            String leaders = "{ \"leaders\":" + data + "}";
            System.out.println(leaders);
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(leaders);
            osw.flush();
            osw.close();
            
            System.out.print("HTTP code : "
                        + connection.getResponseCode());
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }
    
    public ArrayList<String> read () {
        ArrayList<String> leaders = null;
        String inputLine = "";
        connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard/leaders.json";
        
        try {
            HttpURLConnection connection = connectToDB("GET");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            inputLine = in.readLine();
            
        } catch (Exception e){
            e.printStackTrace();
        }
        
        System.out.println(inputLine);
        Gson gson = new Gson();
        leaders = gson.fromJson(inputLine, ArrayList.class);
        System.out.println(leaders.toString());
        
        return leaders;
    }
}
