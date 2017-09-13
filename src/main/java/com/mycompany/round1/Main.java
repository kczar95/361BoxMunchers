package com.mycompany.round1;
import javax.swing.*;

public class Main 
{
    public static void main(String[] args)
    {
        GameController cntl2 = new GameController();
      
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {e.printStackTrace();}
    }
}
