package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author lap5508
 */
public class GameController {

    //private GameView theGameView;
    private final GameView theGameView;
    private LeaderBoardView theLeaderBoardView;
    private GameModel theGameModel;
    private String name;
    private String score;
    private Timer gameTime;
    private int i = 61;
    private int delay = 1000;
    private int gEnd = 0;
    private boolean playing = false;

    public GameController() {
        theGameView = new GameView(this, 9, 9);
    }
    
    public boolean isPlaying() {
        return playing;
    }

    public void showGameView() {
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }

    public void modifyScore(GameBox theBox) {
        if (theBox.isCorrect() && !theBox.wasSelected()) {
            theGameView.incScore();
        } else if (!theBox.wasSelected()) {
            theGameView.decScore();
        }
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
        theGameView.setGameOver();
    }

    //this method will be run when the PLay button is clicked on
    public void play() {
        playing = true;
           startGameTimer();
    }
    
    //this method will run the timer of the game
    public void startGameTimer(){
        ActionListener secondCounter = new ActionListener(){
            public void actionPerformed(ActionEvent evt) {
               if (i > gEnd)
               {
                i = i - 1;
                theGameView.timeRem.setText(Integer.toString(i));
                if(i < 11){
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
}
