/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    private GameController theGameCntl;
    
    public LeaderBoardView(GameController newGameCntl){
        
        theGameCntl = newGameCntl;
        gameModel = new GameModel();
        createLabel();
        createScoreBoard();
        createBackButton();
        
        setBounds(0, 0, 200, 300);
        setResizable(false);
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
        ArrayList<String> leaders = gameModel.getLeaders();
        Object rowData[][] = {{leaders.get(8), leaders.get(9)}, {leaders.get(6), leaders.get(7)}, 
                             {leaders.get(4), leaders.get(5)}, 
                             {leaders.get(2), leaders.get(3)}, {leaders.get(0), leaders.get(1)}};
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
        LeaderBoardView.this.theGameCntl.hideLeaderBoard();
        //this.setVisible(false);
    }
    
}
