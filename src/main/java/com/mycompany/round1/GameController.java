package com.mycompany.round1;

import java.util.ArrayList;

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

    public GameController() {
        theGameView = new GameView(this, 9, 9);
    }

    public void showGameView() {
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }

    public void modifyScore(GameBox theBox) {
        if (theBox.isCorrect() && !theBox.isSelected()) {
            theGameView.incScore();
        } else if (!theBox.isSelected()) {
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

    //this method will be run when the PLay button is clicked on
    public void play() {

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
