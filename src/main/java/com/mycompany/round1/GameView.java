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
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public final class GameView extends JFrame {

    private final GameController gameCtrl;
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
    GridLayout boardGrid = new GridLayout(9, 9);

    public GameView(GameController gameCtrl) {
        this.gameCtrl = gameCtrl;
        multipleOf_Spinner = new JSpinner();
        createInfoPanel();
        createGameBoardPanel(9, 9);
        this.setVisible(true);
        setBounds(0, 0, 750, 550);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void createInfoPanel() {
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(2, 3, 0, 50));
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

    public void createGameBoardPanel(int rows, int cols) {
        gamePanel = new JPanel();
        add(gamePanel);

        gamePanel.setLayout(new GridLayout(rows, cols));
        JButton[][] boardSquares = new JButton[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //Create buttons and add their values
                GameBox newGameBox = new GameBox();

                newGameBox.addActionListener(new GameBoxSelectedEvent(newGameBox));

                boardSquares[i][j] = newGameBox;
                gamePanel.add(boardSquares[i][j]);
            }
        }
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
            gameCtrl.modifyScore(theBox);
        }
    }
}
