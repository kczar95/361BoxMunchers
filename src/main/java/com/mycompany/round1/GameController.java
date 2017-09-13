package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GameController {

    private final GameView theGameView;
    private LeaderBoardView theLeaderBoardView;
    private GameModel theGameModel;
    private String name;
    private String score = "0";
    private Timer gameTime;
    private int i = 0;
    private final int delay = 1000;
    private final int gEnd = 0;
    private boolean playing = false;
    
    Color defaultColor;

    public GameController() {
        theGameView = new GameView(this);
        theGameView.playButton.addActionListener((java.awt.event.ActionEvent evnt) -> 
                {
                  playButtonActionPerformed(evnt);  
                });
        theGameView.leaderboardButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            leaderboardButtonActionPerformed(evt);
        });
    }
    
    private void playButtonActionPerformed(ActionEvent evnt){
        try{
            theGameView.removeCurrentBoard();
        }catch (Exception e){
            
        }
        theGameView.createGameBoardPanel();
        play();
    }
    
    private void leaderboardButtonActionPerformed(ActionEvent evt) {
        showLeaderBoard();
    }
    
    public boolean isPlaying() {
        return playing;
    }

    public void showGameView() {
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }

    public void showLeaderBoard() {
        theLeaderBoardView = new LeaderBoardView(this);
        theLeaderBoardView.setLocationRelativeTo(null);
        theLeaderBoardView.setVisible(true);
    }

    public void hideLeaderBoard() {
        theLeaderBoardView.setVisible(false);
        theLeaderBoardView.dispose();
    }

    public boolean checkLeaders() {
        return false;
    }
    
    public void gameOver() {
        theGameView.scoreCounter.setText("GAME OVER");
        theGameView.scoreCounter.setForeground(Color.RED);
        theGameView.playButton.setEnabled(true);
        theGameView.multipleOf.setEnabled(true);
        theGameView.optionsBox.setEnabled(true);
    }

    //this method will be run when the Play button is clicked on
    public void play() {
        i = 18; //the length of the game, in seconds
        playing = true;
        
        defaultColor = theGameView.playButton.getBackground();
        theGameView.timeRem.setBackground(defaultColor);
        
        score = "0";
        theGameView.scoreCounter.setText(score);
        theGameView.scoreCounter.setForeground(Color.blue);
        theGameView.playButton.setEnabled(false);
        theGameView.multipleOf.setEnabled(false);
        theGameView.optionsBox.setEnabled(false);
        startGameTimer();
    }
    
    //this method will run the timer of the game
    public void startGameTimer(){
        ActionListener secondCounter = new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
               if (i > gEnd){
                    i = i - 1;
                    theGameView.timeRem.setText(Integer.toString(i));
                    if(i < 16){
                    theGameView.timeRem.setBackground(Color.YELLOW);
                }
               } else {
                   gameTime.stop();
                   playing = false;
                   gameOver();   
               }
            }
        };       
        gameTime = new Timer(delay, secondCounter);
        gameTime.start();
    }

    public void getNewLeader(int theScore) {
        theGameModel = new GameModel();
        name = "";
        boolean placed = theGameModel.checkScore(theScore);
        score = String.valueOf(theScore);
        ArrayList<String> theScoreAndPlayer = new ArrayList();
        theScoreAndPlayer.add(name);
        theScoreAndPlayer.add(score);
        if (placed) {
            theGameModel.submitScore(theScoreAndPlayer);
            showLeaderBoard();
        } else {
            showLeaderBoard();
        }
    }
    
    public void incScore() {
        int scoreInt = Integer.parseInt(score) + 1;
        score = Integer.toString(scoreInt);
        updateScore();
        if (Integer.parseInt(score) == 10) {
            boolean placed = checkLeaders();
            showLeaderBoard();
        }
    }

    public void decScore() {
        int scoreInt = Integer.parseInt(score) - 1;
        score = Integer.toString(scoreInt);
        updateScore();
    }

    public void updateScore() {
        theGameView.scoreCounter.setText(score);
    }
}
