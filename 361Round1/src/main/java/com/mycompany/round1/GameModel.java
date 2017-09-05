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
        currLeaders = new ArrayList<String>();
        currLeaders.add("Chandler");
        currLeaders.add("100");
        currLeaders.add("Ahkil");
        currLeaders.add("100");
        currLeaders.add("Luke");
        currLeaders.add("100");
        currLeaders.add("Justin");
        currLeaders.add("100");
        
        try {
            write(currLeaders);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private HttpURLConnection connectToDB(String method) throws Exception{
        
        URL urlConnection = new URL(connectionURL);
        HttpURLConnection con = (HttpURLConnection) urlConnection.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            con.setRequestMethod(method);
            
        return con;
    }
    
    public void write (ArrayList<String> leaderboard) {
        try {
            HttpURLConnection connection = connectToDB("POST");
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
    
    //this will take in the curr users score / name and return a boolean if they are on the leaderboard
    public boolean submitScore (ArrayList<String> currNameScore) {
        
        return false;
    }
    
    public ArrayList<String> read () {
        ArrayList<String> leaders = null;
        
//        try {
//            HttpURLConnection connection = connectToDB("GET");
//            BufferedReader in = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream()));
//        } catch (Exception ex) {
//            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//
//        try {
//            String inputLine = in.readLine();
//        } catch (IOException ex) {
//            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        return leaders;
    }
    
    
    
}
