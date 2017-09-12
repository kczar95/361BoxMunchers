/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.round1;

/**
 *
 * @author jxr5398
 */
public class Leader {

    private final String name;
    private final int score;
    
    /**
     *
     * @param name
     * @param score
     */
    public Leader(String name, int score){
        this.name = name;
        this.score = score;
        
    }

    public String getName(){
        return name;
    }
    
    public int getScore(){
        return score;
    }
    
}
