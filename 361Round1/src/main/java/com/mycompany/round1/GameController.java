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
            theGameModel.submitScore(theScoreAndPlayer);
            theNavCntl.showLeaderBoard();
        }
        else{
            theNavCntl.showLeaderBoard();
        }
    }
    
    //gameMode is a 1,2 or 3; 1=MULTIPLES,2=FACTORS,3=PRIMES.
    //boxNumber is the value in the box.
    //gameValue is the value set to compare to the box.
    //correctBox returns int correct as true or false.
    public boolean correctBox(int gameMode, int boxNumber, int gameValue){
        int answerCheck = 0;
        boolean correct = false;
        boolean prime = false;
        
        if(gameMode==1){
            //MULTIPLES
            answerCheck = boxNumber % gameValue;
            if(answerCheck!=0){
                correct = false;
            }
            else{
                correct = true;
            }
        }
        else if(gameMode==2){
            //FACTORS
            answerCheck = gameValue % boxNumber;
            if(answerCheck!=0){
                correct = false;
            }
            else{
                correct = true;
            }
        }
        else{
            //PRIME
            if(boxNumber==0){
                prime = false;
            }
            else if(boxNumber==1){
                prime = true;
            }
            else if(boxNumber==2){
                prime = false;
            }
            else{
                for(int i=boxNumber-1; i>2; i--){
                    int primeCheck = boxNumber % i;
                    if(primeCheck!=0){
                        prime = false;
                    }
                    else {
                        prime = true;
                    }
                }
            }
            
            if(prime==true){
                correct = true;
            }
            else {
                correct = false;
            }
        }
        return correct;
    }
}
