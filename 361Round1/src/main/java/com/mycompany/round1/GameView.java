package com.mycompany.round1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

public final class GameView extends JFrame {
    
    private GameController gControl = null;
    
    //info panel components
    private JPanel infoPanel;
    private JLabel scoreLabel;
    private String[] multipleValues = {"3", "5", "7"};
    private SpinnerListModel multipleModel;
    private final JSpinner multipleOf_Spinner;
    private JButton playButton;
    private JButton pauseButton;
    private JLabel gameLabel;
    private JButton leaderboardButton;
    GridLayout infoGrid = new GridLayout(2, 3);
    
    
    //game board components
    private JPanel gamePanel;
    private JButton boardPiece;
    GridLayout boardGrid = new GridLayout(9, 9);
    JButton[][] gameBoard = new JButton[9][9];
    
    //game board data
    private int[][] gameData;

    public GameView(GameController g_control) {
        this.multipleOf_Spinner = new JSpinner(multipleModel);
        this.multipleModel = new SpinnerListModel(multipleValues);
        gControl = g_control;
        gameData = createGameData();
        createInfoPanel();
        createGameBoardPanel(gameData);
        this.setVisible(true);
        setBounds(0, 0, 650, 650);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void createInfoPanel(){
        infoPanel = new JPanel();
        GridLayout infoGrid = new GridLayout(2,3, 0, 50);
        infoPanel.setLayout(infoGrid);
        playButton = new JButton("Play");
            //
            //
        pauseButton = new JButton("Pause");
            //
            //
        gameLabel = new JLabel("Multiple Of:", SwingConstants.CENTER);
            //
            //
        //multipleOf_Spinner = new JSpinner("Multiple of:");
            //
            //
        leaderboardButton = new JButton("Leaderboard");
            leaderboardButton.addActionListener((java.awt.event.ActionEvent evt) -> {
                leaderboardButtonActionPerformed(evt);
        });
            //
        scoreLabel = new JLabel("Score:", SwingConstants.CENTER);
            //
            //
        infoPanel.add(playButton);
        infoPanel.add(pauseButton);
        infoPanel.add(leaderboardButton);
        infoPanel.add(scoreLabel);
        infoPanel.add(gameLabel);
        infoPanel.add(multipleOf_Spinner);
        add(infoPanel, BorderLayout.NORTH);       
    }
    
    //Creating the values for each button
    public int[][] createGameData(){
        int[][] tempData = new int[9][9];
        for(int i=0;i<tempData.length;i++){
            for(int j=0;j<tempData.length;j++){
                if(tempData[i][j] == 0){
                    tempData[i][j] = (int) (Math.random() * 100);
                }
            }
        }
        return tempData; 
    }
    
    
    public void createGameBoardPanel(int[][] gameData) {
        gamePanel = new JPanel();
        add(gamePanel);
        GridLayout grid = new GridLayout(9,9);
        gameBoard = new JButton[9][9];
        gamePanel.setLayout(grid);
        JButton[][] boardSquares = new JButton[9][9];
        for (int i=0; i<9;i++)
        {
            for(int j=0; j<9;j++)
            {
                //Create buttons and add their values
                if(gameBoard[i][j] == null )
                {
                    boardSquares[i][j] = new JButton(Integer.toString(gameData[i][j]));
                    gamePanel.add(boardSquares[i][j]);
                }
            }
        }
    }
    
    private void leaderboardButtonActionPerformed(ActionEvent evt){
        GameView.this.gControl.showLeaderBoard();
        this.setVisible(false);
    }
}
