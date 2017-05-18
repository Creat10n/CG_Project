/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import model.*;

/**
 *
 * @author CREAT10N
 */
public class GameView extends JPanel {

    // "Name" label
    private JLabel jLabel1;
    // "Score" label 
    private JLabel jLabel2;
    // "Power" label
    private JLabel jLabel3;
    // "0" label
    private JLabel jLabel4;
    // Playername label
    private JLabel jLabel5;
    // "Time" label
    private JLabel jLabel6;
    // countdown label
    private JLabel jLabel7;

    // Physics Controller
    private PhysicsController physicsController;

    // 3 balls
    public static ArrayList<Ball> balls;
    private Ball b1, b2, b3;
    private final int START_X = 110, START_Y = 115;

    // Cue Stick
    private CueStick stick;

    // Power bar for indicating power
    private JProgressBar powerBar;
    private int power;

    // Power level max or not
    private boolean isMax = false;

    // Check when to decrease balls velocity
    private int check;

    // Player
    private static Player p;

    // Score points
    private int score;

    // Animation timer 
    private javax.swing.Timer timer;

    // Countdown timer
    private java.util.Timer countdown;
    private int interval = 120;

    public GameView(String playerName) {
        // Set window size
        setPreferredSize(new Dimension(500, 350));

        // Initialize JComponent
        initComponents(playerName);

        // Set background color
        setBackground(new Color(144, 238, 144));

        // Add background of the pool table
        add(ImageController.getTableLabel());

        // Initialize player and score
        p = new Player(playerName);
        score = 0;

        // Setup white cue ball
        b1 = new Ball(START_X, START_Y, 1);
        b1.setVx(0);
        b1.setVy(0);

        // Setup 2 object balls
        b2 = new Ball(START_X + 250, START_Y - 50, 2);
        b3 = new Ball(START_X + 250, START_Y + 50, 3);

        // Add 3 balls to ArrayList
        balls = new ArrayList();
        balls.add(b1);
        balls.add(b2);
        balls.add(b3);

        // Setup cue stick
        stick = new CueStick();

        // Handling physics and input events
        physicsController = PhysicsController.getInstance();
        addMouseMotionListener(new MouseActionListener());
        addMouseListener(new MouseActionListener());

        // Setup timer
        timer = new javax.swing.Timer(10, new TimeListener());
        timer.start();
    }

    // Paint balls and stick
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (Ball b : GameView.balls) {
            b.paint(g);
        }
        stick.paint(g);
    }

    // Decrease interval by 1 for each second
    private int setInterval() {
        if (interval == 1) {
            countdown.cancel();
        }
        return --interval;
    }

    private void initComponents(String playerName) {

        // Setup labels for name, score, power and checkdown timer
        jLabel1 = new JLabel("Name:");
        jLabel1.setFont(new Font("Times New Roman", 3, 16));

        jLabel2 = new JLabel("Score:");
        jLabel2.setFont(new Font("Times New Roman", 3, 16));

        jLabel3 = new JLabel("Power:");
        jLabel3.setFont(new Font("Times New Roman", 3, 16));

        jLabel4 = new JLabel("0");
        jLabel4.setFont(new Font("Times New Roman", 3, 16));

        jLabel5 = new JLabel(playerName);
        jLabel5.setFont(new Font("Times New Roman", 3, 16));

        jLabel6 = new JLabel("Time:");
        jLabel6.setFont(new Font("Times New Roman", 3, 16));

        jLabel7 = new JLabel("120");
        jLabel7.setFont(new Font("Times New Roman", 3, 16));

        countdown = new java.util.Timer();
        countdown.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jLabel7.setText("" + setInterval());
                if (interval == 0) {
                    for (Ball b : GameView.balls) {
                        b.setVx(0);
                        b.setVy(0);
                    }
                    JOptionPane.showMessageDialog(null, "Game Over\nThank you for playing!");
                    p.setScore(score);
                    ScoreController.saveScore(p);
                    AppContainer.changePanel(new HighScoresView());
                }
            }
        }, 1000, 1000);

        // Setup power bar
        powerBar = new JProgressBar();

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(powerBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel5)))
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel4)))
                                                .addGap(125, 125, 125)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel6)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel7)))))
                                .addContainerGap(125, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(275, 275, 275)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(powerBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap())
        );

    }

    private class TimeListener implements ActionListener {

        private int oldScore = score;

        @Override
        public void actionPerformed(ActionEvent e) {
            check++;
            if (isMax) {
                if (power != 100) {
                    power = power + 1;
                } else {
                    power = 0;
                }
            } else {
                power = 0;
            }

            if (check == 35) {
                physicsController.decreaseVelocity(GameView.balls);
                check = 0;
            }

            for (Ball b : GameView.balls) {
                // Control balls movements
                physicsController.moveBall(b);

                // Calculate score and display
                if (physicsController.calculateScore()) {
                    score++;
                    jLabel4.setText(score + "");
                }

                // Check to increase score by 1 point at most for each round
                if (((oldScore + 1) == score)
                        && ((balls.get(0).getVx() != 0 || balls.get(0).getVy() != 0)
                        || (balls.get(1).getVx() != 0 || balls.get(1).getVy() != 0)
                        || (balls.get(2).getVx() != 0 || balls.get(2).getVy() != 0))) {
                    // Reset first and second touch for calculating score in next strike
                    PhysicsController.first = false;
                    PhysicsController.second = false;
                }
                if ((balls.get(0).getVx() == 0 && balls.get(0).getVy() == 0)
                        && (balls.get(1).getVx() == 0 && balls.get(1).getVy() == 0)
                        && (balls.get(2).getVx() == 0 && balls.get(2).getVy() == 0)) {
                    oldScore = score;
                    // Reset first and second touch for calculating score in next strike
                    PhysicsController.first = false;
                    PhysicsController.second = false;
                }
            }

            if (isMax) {
                powerBar.setValue(power);
            }
            repaint();
        }
    }

    private class MouseActionListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // Allow to drag the cue stick when all the balls stop moving
            if ((balls.get(0).getVx() == 0 && balls.get(0).getVy() == 0)
                    && (balls.get(1).getVx() == 0 && balls.get(1).getVy() == 0)
                    && (balls.get(2).getVx() == 0 && balls.get(2).getVy() == 0)) {

                // Show power bar
                powerBar.setVisible(true);

                // Get the angle to calculate cue ball movement
                stick.getRadian(e.getX(), e.getY());

                // Allow power bar to increase
                isMax = true;

                // Repaint cue stick for each mouse drag
                stick.stickIcon = new ImageIcon("src/images/stick.png");
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Calculate x_velocity and y_velocity of the cue ball
            if (isMax) {
                double vx, vy;
                vx = Math.cos(-stick.radian) * powerBar.getPercentComplete() * 10;
                vy = Math.sin(-stick.radian) * powerBar.getPercentComplete() * 10;
                b1.setVx(vx);
                b1.setVy(vy);

                // Remove cue stick after the mouse is released
                stick.stickIcon = new ImageIcon("src/images/.png");
                repaint();
            }
            // Reset power 
            isMax = false;
            powerBar.setVisible(false);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    public static void main(String[] args) {
        AppContainer.changePanel(new GameView(""));
    }
}
