/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist361round1;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
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
public class NavigationUITest extends JFrame{
    
    public MainMenuUI(NavigationController parentNavCntl){
        
        theNavCntl = parentNavCntl;
        createLabelPanel();
        createNavigationPanel();
        createClosePanel();
        setBounds(0, 0, 650, 650);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }
    
    public void createLabelPanel(){
        menuLabel = new JLabel("Easy Task", JLabel.CENTER);
            menuLabel.setFont(new Font("Serif", Font.PLAIN, 48));
            add(menuLabel, BorderLayout.NORTH);
    }
    
    public void createNavigationPanel(){
        JPanel navigationPanel = new JPanel();
        
        JButton taskButton = new JButton("Task");
            taskButton.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        taskButtonActionPerformed(evt);
                    }
                });
            
        JButton contactButton = new JButton("Contact");
            contactButton.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        contactButtonActionPerformed(evt);
                    }
                });
            
        JButton notificationButton = new JButton("Notification");
            notificationButton.addActionListener(new java.awt.event.ActionListener(){
                    public void actionPerformed(java.awt.event.ActionEvent evt){
                        notificationButtonActionPerformed(evt);
                    }
                });
        
            
        navigationPanel.setLayout(new GridLayout(1, 2));
            navigationPanel.add(taskButton);
            navigationPanel.add(contactButton);
            navigationPanel.add(notificationButton);
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
            
        JButton logOutButton = new JButton("Log out :(");
            logOutButton.addActionListener(new java.awt.event.ActionListener(){
                public void actionPerformed(java.awt.event.ActionEvent evt){
                    logOutButtonPerformed(evt);
                }
            });
            
        closePanel.setLayout(new GridLayout(0, 1));
            closePanel.add(logOutButton);
            closePanel.add(closeButton);
            add(closePanel, BorderLayout.SOUTH);
    }
    
    private void taskButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        MainMenuUI.this.theNavCntl.requestTaskCntl();
    }
    
    private void contactButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        MainMenuUI.this.theNavCntl.requestContactCntl();
    }
    
    private void notificationButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        MainMenuUI.this.theNavCntl.requestNotificationCntl();
    }
    private void logOutButtonPerformed(java.awt.event.ActionEvent evt) {
        this.setVisible(false);
        MainMenuUI.this.theNavCntl.requestLoginCntl();
    }
    
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);    
    }
}
