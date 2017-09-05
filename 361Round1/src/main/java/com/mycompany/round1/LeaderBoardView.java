/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 *
 * @author lap55
 */
public class LeaderBoardView extends JFrame{
    
    private JPanel titlePanel;
    private JPanel scorePanel;
    private JPanel backPanel;
    private JTextArea theScoresField;
    private JLabel leaderBoardLabel;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JButton backButton;
    private GameModel gameModel;
    private NavigationController theNavCntl;
    
    public LeaderBoardView(NavigationController newNavCntl){
        
        theNavCntl = newNavCntl;
        createLabel();
        createScoreBoard();
        createBackButton();
        
        setBounds(0, 0, 450, 650);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    private void createLabel(){
        titlePanel = new JPanel();
        leaderBoardLabel = new JLabel("Leader Board", SwingConstants.CENTER);
        titlePanel.add(leaderBoardLabel);
            add(titlePanel, BorderLayout.NORTH);
    }
    
    private void createScoreBoard(){
        scorePanel = new JPanel();       
        //scorePanel.setLayout(new GridLayout(2,2,0,0));      
        theScoresField = new JTextArea(60, 30);
        scorePanel.add(theScoresField);
            add(scorePanel, BorderLayout.CENTER);
    }
    
    private void createBackButton(){
        backPanel = new JPanel();
        backButton = new JButton("Back");
        backPanel.add(backButton);
        add(backPanel,BorderLayout.SOUTH);
    }
    
}
