package com.mycompany.round1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class GameBox extends JButton implements ActionListener
{
    String buttonText;
    GameBox(String str)
    {
        super();
        
        this.setText(str);
        this.addActionListener(this);
    }
    
    //When a button is clicked on
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        this.setBackground(Color.cyan.darker());
    }
}
