/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
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
    private JTable scoresTable;
    private Object rowData[][];
    private Object columnNames[];
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
        
        setBounds(0, 0, 200, 300);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }
    
    private void createLabel(){
        titlePanel = new JPanel();
        leaderBoardLabel = new JLabel("Leader Board", SwingConstants.CENTER);
        titlePanel.add(leaderBoardLabel);
            add(titlePanel, BorderLayout.NORTH);
    }
    
    private void createScoreBoard(){
        scorePanel = new JPanel();             
        Object rowData[][] = {{"Test Player 1", "Test Score 1"}, {"Test Player 2", "Test Score 2"}, 
                             {"Test Player 3", "Test Score 3"}, 
                             {"Test Player 4", "Test Score 4"}, {"Test Player 5", "Test Score 5"}};
        Object columnNames[] = {"Player Name", "Score"};
        
        scoresTable = new JTable(rowData, columnNames);
        
        scorePanel.add(scoresTable);
            add(scorePanel, BorderLayout.CENTER);
    }
    
    private void createBackButton(){
        backPanel = new JPanel();
        backButton = new JButton("Back");
        backButton.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                    backButtonActionPerformed(evt);
                }
            });
        backPanel.add(backButton);
        add(backPanel,BorderLayout.SOUTH);
    }
    
    private void backButtonActionPerformed(ActionEvent evt){
        LeaderBoardView.this.theNavCntl.hideLeaderBoard();
        //this.setVisible(false);
    }
    
}
