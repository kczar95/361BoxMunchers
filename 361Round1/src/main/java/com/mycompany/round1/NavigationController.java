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
    
    public NavigationController(){                  
        theGameView = new GameView(this);
        theGameView.setLocationRelativeTo(null);
        theGameView.setVisible(true);
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
