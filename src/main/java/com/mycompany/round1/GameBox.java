package com.mycompany.round1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GameBox extends JButton implements ActionListener {

    boolean selected = false;
    GameView theView;

    public GameBox(String str, GameView gv) {
        super();
        theView = gv;
        this.setText(str);
        this.setFont(new Font("Serif", 0, 20));
        this.addActionListener(this);
        this.setOpaque(true);
        this.setBackground(null);
//        this.setBorderPainted(false);
        //this.setFocusable(false);
        
    }

    @Override
    //Everything that happens when a gameboard button is pressed
    public void actionPerformed(ActionEvent e) {
        
        if (isCorrect(theView.multipleOf.getText(), Integer.parseInt(this.getText())) && !selected && theView.gameCtrl.isPlaying()) {
            this.setBackground(Color.GREEN);
            this.setForeground(Color.GREEN);
            selected = true;
            theView.gameCtrl.incScore();
        } else if (!selected && theView.gameCtrl.isPlaying()) {
            this.setBackground(Color.RED);
            selected = true;
            theView.gameCtrl.decScore();
        }
    }
    
    //check if the selected value 
    //is correct based on the
    //game mode
    public boolean isCorrect(String mo, int bn) {
        // TODO: Change these:
        int multipleOfValue = 0;
        int gameMode = theView.optionsBox.getSelectedIndex();

        int answerCheck;
        int boxNumber = bn;

        switch (gameMode) {
            case 0:
                return isPrime(boxNumber);
            case 1:
                multipleOfValue = Integer.parseInt(mo);
                answerCheck = boxNumber % multipleOfValue;
                return answerCheck == 0;
            case 2:
                multipleOfValue = Integer.parseInt(mo);
                answerCheck = multipleOfValue % boxNumber;
                return answerCheck == 0;
            default:
                return true;
        }
    }
    
    public boolean wasSelected() {
        return selected;
    }

    private boolean isPrime(int boxNumber) {
        if (boxNumber == 1) {
            return true;
        }

        if (boxNumber == 0 || boxNumber == 2 || boxNumber % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= boxNumber; i += 2) {
            if (boxNumber % i == 0) {
                return false;
            }
        }
        return true;
    }

}
