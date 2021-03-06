package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;
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
        theGameModel = new GameModel();
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
        int [][] gameData = createGameData();
        theGameView.createGameBoardPanel(gameData);
        play();
    }
    
    private int[][] createGameData() {
        int[][] tempData = new int [9][9];
        for(int i = 0; i < tempData.length; i++){
            for(int j = 0; j < tempData.length; j++){
                if(tempData[i][j] == 0){
                    tempData[i][j] = (int) (Math.random() * 200);
                }
            }
        }
        return tempData;
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

    
    public void gameOver() {
        playing = false;
        theGameView.scoreCounter.setText("GAME OVER");
        theGameView.scoreCounter.setForeground(Color.RED);
        theGameView.playButton.setEnabled(true);
        theGameView.multipleOf.setEnabled(true);
        theGameView.optionsBox.setEnabled(true);
        boolean placed = theGameModel.checkScore(i * 10);
        if(placed){
            String name = JOptionPane.showInputDialog(theGameView, "You win! what is your name?", "WINNER!", 3);
            String score = Integer.toString(i * 10);
            theGameModel.submitScore(new ArrayList<String>(Arrays.asList(name,score)));
        }
        showLeaderBoard();
    }

    //this method will be run when the Play button is clicked on
    public void play() {
        i = 30; //the length of the game, in seconds
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
    public void startGameTimer() {
        ActionListener secondCounter = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (playing) {
                    if (i > gEnd) {
                        i = i - 1;
                        theGameView.timeRem.setText(Integer.toString(i));
                        if (i < 16) {
                            theGameView.timeRem.setBackground(Color.YELLOW);
                        }
                    } else {
                        gameTime.stop();
                        playing = false;
                        gameOver();
                    }
                } else {
                    gameTime.stop();
                }
            }
        };
        gameTime = new Timer(delay, secondCounter);
        gameTime.start();
    }

    public void getNewLeader(int theScore) {
        theGameModel = new GameModel();
        name = "winner";
        score = String.valueOf(theScore);
        ArrayList<String> theScoreAndPlayer = new ArrayList();
        theScoreAndPlayer.add(name);
        theScoreAndPlayer.add(score);
        theGameModel.submitScore(theScoreAndPlayer);
        showLeaderBoard();
  
        
    }
    
    public void incScore() {
        int scoreInt = Integer.parseInt(score) + 1;
        score = Integer.toString(scoreInt);
        updateScore();
        if (Integer.parseInt(score) == 10) {
            gameOver();
        }
    }

    public void decScore() {
        int scoreInt = Integer.parseInt(score) - 1;
        score = Integer.toString(scoreInt);
        updateScore();
    }

    public void updateScore() {
        theGameView.scoreCounter.setText(score);
        theGameView.scoreCounter.setForeground(Color.blue);
    }
}
