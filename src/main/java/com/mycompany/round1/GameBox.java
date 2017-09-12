package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GameBox extends JButton implements ActionListener {

    boolean selected = false;
    String buttonText;

    GameBox(String str) {
        super();
        this.setText(str);
        this.addActionListener(this);
    }

    //When a button is clicked on
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isCorrect() && !selected) {
            this.setBackground(Color.GREEN);
            selected = true;
        } else if (!selected) {
            this.setBackground(Color.RED);
            selected = true;
        }
    }

    public boolean isCorrect() {
        // TODO: Change these:
        int gameValue = 0;
        int gameMode = 3;
        
        int answerCheck;
        int boxNumber = getBoxNumber();

        switch (gameMode) {
            case 1:
                answerCheck = boxNumber % gameValue;
                return answerCheck == 0;
            case 2:
                answerCheck = gameValue % boxNumber;
                return answerCheck == 0;
            default:
                return isPrime(boxNumber);
        }
    }

    private int getBoxNumber() {
        return Integer.parseInt(this.getText());
    }

    private boolean isPrime(int boxNumber) {
        switch (boxNumber) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return false;
            default:
                if (boxNumber % 2 == 0) {
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

}
