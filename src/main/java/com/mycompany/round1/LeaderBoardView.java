/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lap55
 */
public class LeaderBoardView extends JFrame {

    private JPanel titlePanel;
    private JPanel scorePanel;
    private JPanel backPanel;
    private JLabel leaderBoardLabel;
    private JButton backButton;
    private final GameModel gameModel;
    private final GameController theGameCntl;

    public LeaderBoardView(GameController theGameCntl) {
        this.theGameCntl = theGameCntl;
        gameModel = new GameModel();
        createLabel();
        createScoreBoard();
        createBackButton();

        setBounds(0, 0, 200, 300);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    }

    private void createLabel() {
        titlePanel = new JPanel();
        leaderBoardLabel = new JLabel("Leader Board", SwingConstants.CENTER);
        titlePanel.add(leaderBoardLabel);
        add(titlePanel, BorderLayout.NORTH);
    }

    private void createScoreBoard() {
        scorePanel = new JPanel();
        JTable theTable = new JTable();
        
        Object columnNames[] = {"Player Name", "Score"};
        DefaultTableModel tableModel = (DefaultTableModel) theTable.getModel();
        tableModel.setColumnIdentifiers(columnNames);
        
//        for(Leader leader : gameModel.getLeaders()){
//            Object row[] = {leader.getName(), leader.getScore()};
//            tableModel.addRow(row);
//        }
        
        scorePanel.add(theTable);
        add(scorePanel, BorderLayout.CENTER);
    }

    private void createBackButton() {
        backPanel = new JPanel();
        backButton = new JButton("Back");

        backButton.addActionListener((ActionEvent evt) -> {
            backButtonActionPerformed(evt);
        });

        backPanel.add(backButton);
        add(backPanel, BorderLayout.SOUTH);
    }

    private void backButtonActionPerformed(ActionEvent evt) {
        LeaderBoardView.this.theGameCntl.hideLeaderBoard();
        //this.setVisible(false);
    }

}
