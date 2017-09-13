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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public final class GameView extends JFrame {

    final GameController gameCtrl;
    //info panel components
    JPanel infoPanel;
    JPanel scorePanel;
    JPanel optionsPanel;
    JLabel scoreCounter;
    JLabel scoreLabel;
    private String score = "0";
    JComboBox optionsBox;
    
    private SpinnerModel multipleModel;
    private JSpinner multipleOf_Spinner;

    JButton playButton;
    JButton timeRem;
    private JLabel gameLabel;
    JButton leaderboardButton;
    
    JTextField multipleOf;

    GridLayout infoGrid = new GridLayout(2, 3);

    //game board components
    private JPanel gamePanel;
    GridLayout boardGrid = new GridLayout(9, 9);

    public GameView(GameController gameCtrl) {
        this.gameCtrl = gameCtrl;
        multipleOf_Spinner = new JSpinner();
        createInfoPanel();
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
        
        timeRem = new JButton();
        //timeRem.setEditable(false);
        
        gameLabel = new JLabel("Game Mode:", SwingConstants.CENTER);

        //Creating a spinner model: starts at 3, ends at 17, increments by 2
        multipleModel = new SpinnerNumberModel(3, 3, 17, 2);
        multipleOf_Spinner = new JSpinner(multipleModel);

        String[] gameModes = {"Prime numbers", "Multiples of", "Divisors of"};
        optionsBox = new JComboBox(gameModes);

        leaderboardButton = new JButton("Leaderboard");

        scorePanel = new JPanel();
        Font myFont = new Font("Serif", Font.BOLD, 20);

        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER);
        scoreCounter = new JLabel(score);
        scoreCounter.setFont(myFont);
        scoreCounter.setForeground(Color.blue);
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreCounter);
        timeRem.setFont(myFont);
        
        optionsPanel = new JPanel();
        optionsPanel.add(optionsBox);
        multipleOf = new JTextField(2);
        optionsPanel.add(multipleOf);

        infoPanel.add(playButton);
        infoPanel.add(timeRem);
        infoPanel.add(leaderboardButton);
        infoPanel.add(gameLabel);
        infoPanel.add(optionsPanel);
 //       infoPanel.add(multipleOf_Spinner);
        infoPanel.add(scorePanel);
        add(infoPanel, BorderLayout.NORTH);
    }
    
    void removeCurrentBoard() {
        this.getContentPane().remove(gamePanel);
        gamePanel.removeAll();
        this.getContentPane().revalidate();
        this.getContentPane().repaint();
    }

    public void createGameBoardPanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(9, 9));

        for (int i = 0; i < 9 * 9; i++) {
            GameBox newGameBox = new GameBox(this);
            gamePanel.add(newGameBox);
        }
        add(gamePanel);
    }

}
