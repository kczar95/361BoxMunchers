/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public final class GameView extends JFrame {

    private NavigationController nControl = null;
    private GameController gameCtrl = null;

    //info panel components
    private JPanel infoPanel;
    private JPanel scorePanel;
    private JLabel scoreCounter;
    private JLabel scoreLabel;
    private String score = "0";
    
    private SpinnerModel multipleModel;
    private JSpinner multipleOf_Spinner;
    
    private JButton playButton;
    private JButton pauseButton;
    private JLabel gameLabel;
    private JButton leaderboardButton;
    
    GridLayout infoGrid = new GridLayout(2, 3);

    //game board components
    private JPanel gamePanel;
    private JButton boardPiece;
    GridLayout boardGrid = new GridLayout(9, 9);

    //game board data
    private int[][] gameData;

    public GameView(NavigationController n_control, GameController gameCtrl) {
        nControl = n_control;
        this.gameCtrl = gameCtrl;
        multipleOf_Spinner = new JSpinner();
        createInfoPanel();
        gameData = createGameData();
        createGameBoardPanel(gameData);
        this.setVisible(true);
        setBounds(0, 0, 750, 550);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void createInfoPanel() {
        infoPanel = new JPanel();
        
        GridLayout infoGrid = new GridLayout(2, 3, 0, 50);
        infoPanel.setLayout(infoGrid);
        
        playButton = new JButton("Play");
        
        pauseButton = new JButton("Pause");
        gameLabel = new JLabel("Multiple Of:", SwingConstants.CENTER);

        //Creating a spinner model: starts at 3, ends at 17, increments by 2
        multipleModel = new SpinnerNumberModel(3, 3, 17, 2);
        multipleOf_Spinner = new JSpinner(multipleModel);
        
        
        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            leaderboardButtonActionPerformed(evt);
        });
        scorePanel = new JPanel();
        
        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreCounter = new JLabel(score);
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreCounter);
        
        infoPanel.add(playButton);
        infoPanel.add(pauseButton);
        infoPanel.add(leaderboardButton);
        infoPanel.add(gameLabel);
        infoPanel.add(multipleOf_Spinner);
        infoPanel.add(scorePanel);
        add(infoPanel, BorderLayout.NORTH);
    }
    
    public void incScore(){
        int scoreInt = Integer.parseInt(score) + 1;
        score = Integer.toString(scoreInt);
        updateScore();
        if(Integer.parseInt(score) == 10){
            boolean placed = nControl.checkLeaders();
            nControl.showLeaderBoard();
            
        }
    }
    
    public void decScore(){
        int scoreInt = Integer.parseInt(score) - 1;
        score = Integer.toString(scoreInt);
        updateScore();
    }
    
    public void updateScore() {
        this.getContentPane().remove(infoPanel);
        infoPanel.remove(scorePanel);
        scorePanel.remove(scoreCounter);
        scoreCounter.setText(score);
        scorePanel.add(scoreCounter);
        infoPanel.add(scorePanel);
        this.getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    //Creating the values for each button
    public int[][] createGameData() {
        int[][] tempData = new int[9][9];
        for (int i = 0; i < tempData.length; i++) {
            for (int j = 0; j < tempData.length; j++) {
                if (tempData[i][j] == 0) {
                    tempData[i][j] = (int) (Math.random() * 200);
                }
            }
        }
        return tempData;
    }

    public void createGameBoardPanel(int[][] gameData) {
        gamePanel = new JPanel();
        add(gamePanel);

        gamePanel.setLayout(new GridLayout(9, 9));
        JButton[][] boardSquares = new JButton[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //Create buttons and add their values
                    boardSquares[i][j] = new GameBox(Integer.toString(gameData[i][j]), gameCtrl);
                    gamePanel.add(boardSquares[i][j]);
            }
        }
    }

    private void leaderboardButtonActionPerformed(ActionEvent evt) {
        GameView.this.nControl.showLeaderBoard();
    }
}
