/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import model.*;

/**
 *
 * @author CREAT10N
 */
public class GameView extends JPanel {

    // "MainMenu" button
    private JButton jButton;

    private JProgressBar jProgressBar;

    // "Name" label
    private JLabel jLabel1;

    // "Score" label 
    private JLabel jLabel2;

    // "Power" label
    private JLabel jLabel3;
    
    // List of the 3 balls
    public static ArrayList<Ball> balls;
    private Ball b1, b2, b3;
    private final int START_X = 60, START_Y = 125;
    
    // Bar for indicating power
    public JProgressBar powerBar;
    
    // Player
    private static Player p;
    
    // Score points
    private int score;
    
    // Countdown timer
    public static Timer timer;

    public GameView(String playerName) {
        initComponents();
        setLayout(null);
        setPreferredSize(new Dimension(500, 350));
        // Add background of the pool table
        add(ImageController.getTableLabel());
        // Create player
        p = new Player(playerName);
        score = 0;
        
        powerBar = new JProgressBar(1, 10);
        add(powerBar);
        powerBar.setVisible(false);

        // Setup white cue ball
        b1 = new Ball(START_X, START_Y, 1);
        b1.setVx(0);
        b1.setVy(0);

        // Setup 2 object balls
        b2 = new Ball(START_X + 240, START_Y - 50, 2);
        b3 = new Ball(START_X + 240, START_Y + 50, 3);
    
        balls = new ArrayList();
        balls.add(b1);
        balls.add(b2);
        balls.add(b3);
        
//        timer = new Timer(20, TimerListener());
//        timer.start();
    }

    private void initComponents() {

    }
    
    public static void main(String[] args) {
        AppContainer.changePanel(new GameView(""));
    }
}
