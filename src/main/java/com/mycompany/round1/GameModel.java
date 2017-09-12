/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.net.HttpURLConnection;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author lap5508
 */
public class GameModel {

    private String connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard.json";
    private ArrayList<Leader> currLeaders;

    public GameModel() {
        read();
        submitScore(new ArrayList<String>(Arrays.asList("Daniel", "120")));
    }

    public boolean checkScore(int score) {
        if (currLeaders.size() < 10) {
            return true;
        }

        //else, check if it is greater then any of them, if so remove that one and add this one
        int currSmallestLocation = 1;
        int currSmallest = 9999;
        for (int i = 1; i < currLeaders.size(); i++) {
            //System.out.println(currLeaders.get(i));
            if (currLeaders.get(i).getScore() < currSmallest) {
                currSmallestLocation = i;
                currSmallest = currLeaders.get(i).getScore();
                System.out.println(currSmallest);
                System.out.println(currSmallestLocation);
            }
        }
        if (score > currSmallest) {
            currLeaders.remove(currSmallestLocation - 1);
            currLeaders.remove(currSmallestLocation - 1);
            return true;
        }

        return false;
    }
    //this will take in the curr users score / name and return a boolean if they are on the leaderboard

    public void submitScore(ArrayList<String> currNameScore) {
        currLeaders.add(new Leader(currNameScore.get(0), Integer.parseInt(currNameScore.get(1))));
        //write(currLeaders);
    }

    public ArrayList<Leader> getLeaders() {
        return currLeaders;
    }

    private HttpURLConnection connectToDB(String method) throws Exception {

        URL urlConnection = new URL(connectionURL);
        HttpURLConnection con = (HttpURLConnection) urlConnection.openConnection();
        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod(method);

        return con;
    }

    public void write(ArrayList<String> leaderboard) {
        try {
            connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard.json";
            HttpURLConnection connection = connectToDB("POST");
            connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            Gson gson = new Gson();
            String data = gson.toJson(leaderboard);

            String leaders = "{ \"leaders\":" + data + "}";
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

    public void read() {
        String inputLine = "";
        connectionURL = "https://boxmunchers-6c015.firebaseio.com/leaderboard/leaders.json";

        try {
            HttpURLConnection connection = connectToDB("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            inputLine = in.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();

        currLeaders = gson.fromJson(inputLine, ArrayList.class);
    }
}
