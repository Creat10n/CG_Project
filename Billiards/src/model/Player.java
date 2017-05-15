/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author CREAT10N
 */
public class Player {

    private static String name;
    private int score;

    public Player(String name) {
        Player.name = name;
    }

    /**
     * @return the name
     */
    public static String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public static void setName(String name) {
        Player.name = name;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }  
}
