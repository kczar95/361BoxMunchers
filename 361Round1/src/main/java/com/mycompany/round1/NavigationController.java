/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist361round1;

/**
 *
 * @author lap5508
 */
public class NavigationController {
    private HighScoreView theHighScoreView;
    private GameView theGameView;
    private NavigationView theNavigationView;
    
    public NavigationController(){                  
        theNavigationView = new NavigationView(this);
        theNavigationView.setLocationRelativeTo(null);
        theNavigationView.setVisible(true);
    }
}
