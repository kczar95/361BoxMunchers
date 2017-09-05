/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author lap5508
 */
public class GameView extends JFrame{
    private JPanel gamePanel;
    private JButton boardPiece;
    GridLayout boardGrid = new GridLayout(9,9);
    JButton[][] gameBoard = new JButton[9][9];

public GameView(GameController g_control){
    createGameBoardPanel();
    setBounds(0, 0, 650, 650);
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
}
public void createGameBoardPanel(){
    gamePanel = new JPanel();
    boardPiece = new JButton("test");
    gamePanel.setLayout(boardGrid);
    gameBoard = new JButton[9][9];
    for(int i =0;i<9;i++){
        for(int j=0;j<9;j++){
            add(boardPiece);
        }
    }
}
}