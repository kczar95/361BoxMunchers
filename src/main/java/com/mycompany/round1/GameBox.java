package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GameBox extends JButton implements ActionListener {

    boolean selected = false;

    public GameBox() {
        super();
        int value = (int) (Math.random() * 200);
        this.setText(value + "");
        this.addActionListener(this);
    }

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
