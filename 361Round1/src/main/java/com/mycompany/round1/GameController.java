/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.util.ArrayList;

/**
 *
 * @author lap5508
 */
public class GameController {
    //private GameView theGameView;
    private LeaderBoardView theLeaderBoardView;
    private NavigationController theNavCntl;
    private GameModel theGameModel;
    private String name;
    private String score;
    
    /*
    public GameController(){
        theGameView = new GameView(this);
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }*/
    
    public void showLeaderBoard(){
        //theLeaderBoardView = new LeaderBoardView(this);
        theLeaderBoardView.setVisible(true);
    }
    
    public void getNewLeader(int theScore){
        theNavCntl = new NavigationController();
        theGameModel = new GameModel();
        name = "";
        boolean placed = theGameModel.checkScore(theScore);
        score = String.valueOf(theScore);     
        ArrayList<String> theScoreAndPlayer = new ArrayList();
        theScoreAndPlayer.add(name);
        theScoreAndPlayer.add(score);
        if(placed){
            theGameModel.submitScore();
            theNavCntl.showLeaderBoard();
        }
        else{
            theNavCntl.showLeaderBoard();
        }
    }
}
