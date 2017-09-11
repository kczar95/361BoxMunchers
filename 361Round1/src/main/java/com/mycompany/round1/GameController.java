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
    
    public GameController (){
       
    }

    /*
    public GameController(){
        theGameView = new GameView(this);
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }*/
    public void showLeaderBoard() {
        //theLeaderBoardView = new LeaderBoardView(this);
        theLeaderBoardView.setVisible(true);
    }
    
    //this method will be run when the PLay button is clicked on
    public void play()
    {
        
    }

    public void getNewLeader(int theScore) {
        theNavCntl = new NavigationController();
        theGameModel = new GameModel();
        name = "";
        boolean placed = theGameModel.checkScore(theScore);
        score = String.valueOf(theScore);
        ArrayList<String> theScoreAndPlayer = new ArrayList();
        theScoreAndPlayer.add(name);
        theScoreAndPlayer.add(score);
        if (placed) {
            theGameModel.submitScore(theScoreAndPlayer);
            theNavCntl.showLeaderBoard();
        } else {
            theNavCntl.showLeaderBoard();
        }
    }
    
    //gameMode is a 1,2 or 3; 1=MULTIPLES,2=FACTORS,3=PRIMES.
    //boxNumber is the value in the box.
    //gameValue is the value set to compare to the box.
    //correctBox returns int correct as true or false.
    public boolean correctBox(int gameMode, int boxNumber, int gameValue) {
        int answerCheck;
        
        switch (gameMode) {
            case 1:
                answerCheck = boxNumber % gameValue;
                return answerCheck == 0;
            case 2:
                answerCheck = gameValue % boxNumber;
                return answerCheck == 0;
            default:
                return isPrime(boxNumber);
        }
    }

    private boolean isPrime(int boxNumber) {
        switch (boxNumber) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return false;
            default:
//                boolean prime = false;
//                for (int i = boxNumber - 1; i > 2; i--) {
//                    int primeCheck = boxNumber % i;
//                    prime = primeCheck == 0;
//                }
//                return prime;
                //check if n is a multiple of 2
                if (boxNumber%2==0) return false;
                //if not, then just check the odds
                for(int i=3;i*i<=boxNumber;i+=2) {
                    if(boxNumber%i==0)
                        return false;
                }
                return true;
        }
    }
}
