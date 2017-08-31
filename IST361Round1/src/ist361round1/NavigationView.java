/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist361round1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author lap5508
 */
public class NavigationView extends JFrame{
    
    private JPanel thePanel;
    private JPanel buttonPanel;
    private JPanel[] cellNumbers;
    private JLabel titleLabel;
    private JButton highScoresButton;
    private JButton gameButton;
    private JButton closeButton;
    private NavigationController parentNavigationCntl = null;
        
    public NavigationView(NavigationController newParentNavigationCntl){
        
        parentNavigationCntl = newParentNavigationCntl;
        createLabelPanel();
        createNavigationPanel();
        createClosePanel();
        setBounds(0, 0, 650, 650);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
     
    }  
    
    public void createLabelPanel(){
        titleLabel = new JLabel("Box Munchers", JLabel.CENTER);
            titleLabel.setFont(new Font("Serif", Font.PLAIN, 48));
            add(titleLabel, BorderLayout.NORTH);
    }
    
    public void createNavigationPanel(){
        JPanel navigationPanel = new JPanel();
        
        JButton gameButton = new JButton("Play Game");
            gameButton.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        //gameButtonActionPerformed(evt);
                    }
                });
            
        JButton scoresButton = new JButton("Contact");
            scoresButton.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        //scoresButtonActionPerformed(evt);
                    }
                });
            
            
        navigationPanel.setLayout(new GridLayout(1, 2));
            navigationPanel.add(gameButton);
            navigationPanel.add(scoresButton);
            add(navigationPanel, BorderLayout.CENTER);
    }
    
    public void createClosePanel(){
        JPanel closePanel = new JPanel();
        
        JButton closeButton = new JButton("Exit");
            closeButton.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                    closeButtonActionPerformed(evt);
                }
            });
            
            
        closePanel.setLayout(new GridLayout(0, 1));
            closePanel.add(closeButton);
            add(closePanel, BorderLayout.SOUTH);
    }
    
    private void gameButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        //NavigationView.this.theNavigationCntl.requestGameCntl();
    }
    
    private void scoresButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        //NavigationView.this.theNavCntl.requestScoreCntl();
    }
    
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);    
    }
}
