/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.*;

/**
 *
 * @author afp5136
 */

//The game's toolbar
public class HighScoreView extends JPanel
{
    JLabel score = new JLabel();
    JLabel multipleOf_Label = new JLabel();
    
    JButton menu = new JButton("Menu");
    JButton play = new JButton();
    JButton pause = new JButton();
    JButton quit = new JButton();
    JButton leaderboard = new JButton();
    
    //constructor
    HighScoreView()
    {
        score = new JLabel();
        multipleOf_Label = new JLabel();
        menu = new JButton("Menu");
        play = new JButton();
        pause = new JButton();
        quit = new JButton();
        leaderboard = new JButton();
        
        createPanel();
    }
    
    private void createPanel()
    {
        this.add(score);
        this.add(menu);
        this.add(play);
        this.add(pause);
        this.add(multipleOf_Label);
        this.add(quit);
        this.add(leaderboard);

    }
}
