package com.mycompany.round1;
import javax.swing.*;

public class Main 
{
    public static void main(String[] args)
    {
        //NavigationController cntl = new NavigationController(new GameController());
        GameController cntl2 = new GameController();
        //GameModel gasd = new GameModel();
      
        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {e.printStackTrace();}
    }
}
