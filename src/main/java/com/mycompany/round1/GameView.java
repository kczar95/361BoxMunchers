/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public final class GameView extends JFrame {

    private final GameController gameCtrl;
    //info panel components
    private JPanel infoPanel;
    private JPanel scorePanel;
    private JLabel scoreCounter;
    private JLabel scoreLabel;
    private String score = "0";
    private JComboBox optionsBox;
    
    private SpinnerModel multipleModel;
    private JSpinner multipleOf_Spinner;

    private JButton playButton;
    JButton timeRem;
    private JLabel gameLabel;
    private JButton leaderboardButton;

    GridLayout infoGrid = new GridLayout(2, 3);

    //game board components
    private JPanel gamePanel;
    GridLayout boardGrid = new GridLayout(9, 9);

    public GameView(GameController gameCtrl, int rows, int cols) {
        this.gameCtrl = gameCtrl;
        multipleOf_Spinner = new JSpinner();
        createInfoPanel();
        //createGameBoardPanel(rows, cols);
        this.setVisible(true);
        setBounds(0, 0, 750, 550);
        setResizable(false);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 3, 0, 50));
        playButton = new JButton("Play");
        playButton.addActionListener((java.awt.event.ActionEvent evnt) -> 
                {
                  playButtonActionPerformed(evnt);  
                });
        
    
        
        timeRem = new JButton();
        //timeRem.setEditable(false);
        
        gameLabel = new JLabel("Game Mode:", SwingConstants.CENTER);

//        //Creating a spinner model: starts at 3, ends at 17, increments by 2
//        multipleModel = new SpinnerNumberModel(3, 3, 17, 2);
//        multipleOf_Spinner = new JSpinner(multipleModel);

        String[] gameModes = {"Prime numbers", "Other games will go here", "Once we implement them"};
        optionsBox = new JComboBox(gameModes);

        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener((java.awt.event.ActionEvent evt) -> {
            leaderboardButtonActionPerformed(evt);
        });

        scorePanel = new JPanel();
        Font myFont = new Font("Serif", Font.BOLD, 20);

        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreCounter = new JLabel(score);
        scoreCounter.setFont(myFont);
        scoreCounter.setForeground(Color.blue);
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreCounter);
        timeRem.setFont(myFont);

        infoPanel.add(playButton);
        infoPanel.add(timeRem);
        infoPanel.add(leaderboardButton);
        infoPanel.add(gameLabel);
        infoPanel.add(optionsBox);
        infoPanel.add(scorePanel);
        add(infoPanel, BorderLayout.NORTH);
    }

    public void incScore() {
        int scoreInt = Integer.parseInt(score) + 1;
        score = Integer.toString(scoreInt);
        updateScore();
        if (Integer.parseInt(score) == 10) {
            boolean placed = gameCtrl.checkLeaders();
            gameCtrl.showLeaderBoard();
        }
    }

    public void decScore() {
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
    
    public void setGameOver(){
        this.getContentPane().remove(infoPanel);
        infoPanel.remove(scorePanel);
        scorePanel.remove(scoreCounter);
        scoreCounter.setText("GAME OVER");
        scoreCounter.setForeground(Color.RED);
        infoPanel.add(scoreCounter);
        this.getContentPane().add(infoPanel, BorderLayout.NORTH);
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }
    
    private void removeCurrentBoard() {
        this.getContentPane().remove(gamePanel);
        gamePanel.removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void createGameBoardPanel(int rows, int cols) {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(rows, cols));

        for (int i = 0; i < rows * cols; i++) {
            GameBox newGameBox = new GameBox();
            newGameBox.addActionListener(new GameBoxSelectedEvent(newGameBox));
            gamePanel.add(newGameBox);
        }
        
        add(gamePanel);
    }
    
    

    private void leaderboardButtonActionPerformed(ActionEvent evt) {
        GameView.this.gameCtrl.showLeaderBoard();
    }

    class GameBoxSelectedEvent implements ActionListener {

        private final GameBox theBox;

        public GameBoxSelectedEvent(GameBox theBox) {
            this.theBox = theBox;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(gameCtrl.isPlaying()){
                gameCtrl.modifyScore(theBox);
            }
            
        }
    }
    private void playButtonActionPerformed(ActionEvent evnt){
        try{
            GameView.this.removeCurrentBoard();
        }catch (Exception e){
            
        }
        
        GameView.this.createGameBoardPanel(9, 9);
        GameView.this.gameCtrl.play();
    }
}
