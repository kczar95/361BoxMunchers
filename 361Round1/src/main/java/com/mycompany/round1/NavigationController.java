/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

/**
 *
 * @author lap5508
 */
public class NavigationController {
    //private HighScoreView theHighScoreView;
    private GameView theGameView;
    private NavigationView theNavigationView;
    private LeaderBoardView theLeaderBoardView;
    private GameController gameCtrl;
    
    public NavigationController(GameController theCntl){         
        gameCtrl = theCntl;
        theGameView = new GameView(this, gameCtrl);
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
    }
    
    //
    public boolean checkLeaders(){
        return false;
    }
    
    public void incScore () {
        theGameView.incScore();
    }
    
    public void decScore () {
        theGameView.decScore();
    }
    
    public void showLeaderBoard(){
        theLeaderBoardView = new LeaderBoardView(this);
        theLeaderBoardView.setLocationRelativeTo(null);
        theLeaderBoardView.setVisible(true);
    }
    
     public void hideLeaderBoard(){      
        theLeaderBoardView.setVisible(false);
        theLeaderBoardView.dispose();
     }
     
}
