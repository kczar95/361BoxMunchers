package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GameBox extends JButton implements ActionListener
{
    GameController parentCntl;
    boolean selected = false;
    String buttonText;
    GameBox(String str, GameController theCtrl)
    {
        super();
        parentCntl = theCtrl;
        this.setText(str);
        this.addActionListener(this);
    }
    
    //When a button is clicked on
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(parentCntl.correctBox(3, Integer.parseInt(this.getText()), 0) && !selected){
            this.setBackground(Color.GREEN);
            parentCntl.incScore();
            selected = true;
        } else if (!selected){
            this.setBackground(Color.RED);
            parentCntl.decScore();
            selected = true;
        }
        
    }
}
