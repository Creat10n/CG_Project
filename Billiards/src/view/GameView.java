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
    private JLabel jLabel10;
    // "Score" label 
    private JLabel jLabel2;
    private JLabel jLabel11;
    // "Power" label
    private JLabel jLabel3;
    // "0" label
    private JLabel jLabel4;
    // Player1 label
    private JLabel jLabel5;
    // "Time" label
    private JLabel jLabel6;
    // countdown label
    private JLabel jLabel7;
    // "0" label
    private JLabel jLabel8;
    // Player2 label
    private JLabel jLabel9;

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
    public static Player p1;
    public static Player p2;

    // Score points
    private int score1;
    private int score2;

    // Animation timer 
    private javax.swing.Timer timer;

    // Countdown timer
    private java.util.Timer countdown;
    private int interval = 120;

    // Enable mouse listener
    private boolean enabled = true;

    // Play mode
    private int gameMode = 0;

    public static boolean turn = true;

    public GameView(int mode, String playerName1, String playerName2) {
        // Set window size
        setPreferredSize(new Dimension(500, 350));

        // Initialize player and score
        p1 = new Player(playerName1);
        p2 = new Player(playerName2);
        score1 = 0;
        score2 = 0;

        // Initialize JComponent
        initComponents(playerName1, playerName2);
        // Set background color
        setBackground(new Color(144, 238, 144));

        // Add background of the pool table
        add(ImageController.getTableLabel());

        if (p2.getName().equals("")) {
            // Setup white cue ball
            b1 = new Ball(START_X, START_Y, 1);
            b1.setVx(0);
            b1.setVy(0);

            // Setup 2 object balls
            b2 = new Ball(START_X + 250, START_Y - 50, 2); // (yellow)
            b3 = new Ball(START_X + 250, START_Y + 50, 3); // (red)
        } else {
            // Setup object ball (red)
            b1 = new Ball(START_X, START_Y, 3);
            b1.setVx(0);
            b1.setVy(0);

            // Setup 2 cue balls
            b2 = new Ball(START_X + 250, START_Y - 50, 1); // (white)
            b3 = new Ball(START_X + 250, START_Y + 50, 2); // (yellow)
        }

        // Add 3 balls to ArrayList
        balls = new ArrayList();
        balls.add(b1);
        balls.add(b2);
        balls.add(b3);

        // Setup cue stick
        if (p2.getName().equals("")) {
            stick = new CueStick();
        } else {
            stick = new CueStick(b2);
        }

        // Handling physics and input events
        physicsController = PhysicsController.getInstance();
        addMouseMotionListener(new MouseActionListener());
        addMouseListener(new MouseActionListener());

        // Setup timer
        timer = new javax.swing.Timer(10, new TimeListener());
        timer.start();

        gameMode = mode;
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

    private void initComponents(String playerName1, String playerName2) {

        // Setup labels for name, score, power and checkdown timer
        jLabel1 = new JLabel("Player1:");
        jLabel1.setFont(new Font("Times New Roman", 3, 16));
        jLabel10 = new JLabel("Player2:");
        jLabel10.setFont(new Font("Times New Roman", 3, 16));

        jLabel2 = new JLabel("Score:");
        jLabel2.setFont(new Font("Times New Roman", 3, 16));
        jLabel11 = new JLabel("Score:");
        jLabel11.setFont(new Font("Times New Roman", 3, 16));

        jLabel3 = new JLabel("Power:");
        jLabel3.setFont(new Font("Times New Roman", 3, 16));

        jLabel4 = new JLabel("0");
        jLabel4.setFont(new Font("Times New Roman", 3, 16));

        jLabel5 = new JLabel(playerName1);
        jLabel5.setFont(new Font("Times New Roman", 3, 16));

        jLabel6 = new JLabel();
        jLabel6.setFont(new Font("Times New Roman", 3, 16));

        jLabel7 = new JLabel();
        jLabel7.setFont(new Font("Times New Roman", 3, 16));

        if (p2.getName().equals("")) {
            jLabel6.setText("Time:");
            jLabel7.setText("120");
        }

        jLabel8 = new JLabel();
        jLabel8.setFont(new Font("Times New Roman", 3, 16));
        if (!p2.getName().equals("")) {
            jLabel8.setText("0");
        }

        jLabel9 = new JLabel(playerName2);
        jLabel9.setFont(new Font("Times New Roman", 3, 16));

        // Setup countdown timer for 1Player mode
        countdown = new java.util.Timer();
        if (p2.getName().equals("")) {
            countdown.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    jLabel7.setText("" + setInterval());
                    // End of the game
                    if (interval == 0) {
                        for (Ball b : GameView.balls) {
                            b.setVx(0);
                            b.setVy(0);
                        }
                        JOptionPane.showMessageDialog(null, "Game Over\nThank you for playing!");
                        // Disable mouse listener
                        enabled = false;
                        // Save new player name and score
                        p1.setScore(score1);
                        ScoreController.saveScore(p1);
                        // Display highscore
                        AppContainer.changePanel(new HighScoresView());
                    }
                }
            }, 1000, 1000);
        }

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
                                                                .addComponent(jLabel7))))
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel10)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel9)))
                                                .addGap(35, 35, 35)
                                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel11)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(jLabel8))))
                                )
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
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel11)
                                        .addComponent(jLabel8))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap())
        );

    }

    private class TimeListener implements ActionListener {

        // Old score for checking score calculation
        private int oldScore1 = score1;
        private int oldScore2 = score2;

        @Override
        public void actionPerformed(ActionEvent e) {
            check++;
            // Increase power by time when mouse dragged
            if (isMax) {
                if (power != 100) {
                    power = power + 1;
                } else {
                    power = 0;
                }
            } else {
                power = 0;
            }

            // For each 0.5 second, decrease velocity of each moving ball
            if (check == 50) {
                physicsController.decreaseVelocity(GameView.balls);
                check = 0;
            }

            for (Ball b : GameView.balls) {
                // Control balls movements
                try {
                    physicsController.moveBall(b);
                } catch (Exception ex) {
                    System.out.println(ex);
                }

                if (p2.getName().equals("") || !turn) {
                    // Calculate and display score
                    if (physicsController.calculateScore(gameMode)) {
                        score1++;
                        jLabel4.setText(score1 + "");
                    }

                    // Check to increase score by 1 point at most for each round
                    if (((oldScore1 + 1) == score1)
                            && ((b1.getVx() != 0 || b1.getVy() != 0)
                            || (b2.getVx() != 0 || b2.getVy() != 0)
                            || (b3.getVx() != 0 || b3.getVy() != 0))) {
                        // Reset variables for calculating score in next strike
                        PhysicsController.first = false;
                        PhysicsController.second = false;
                        PhysicsController.edgeCollide = 0;
                        PhysicsController.checkFirst = 0;
                    }

                    // Reset variables for calculating score in next strike
                    if ((b1.getVx() == 0 && b1.getVy() == 0)
                            && (b2.getVx() == 0 && b2.getVy() == 0)
                            && (b3.getVx() == 0 && b3.getVy() == 0)) {
                        // End of round so set old score with the new current score for further checking
                        oldScore1 = score1;
                        PhysicsController.first = false;
                        PhysicsController.second = false;
                        PhysicsController.edgeCollide = 0;
                        PhysicsController.checkFirst = 0;
                    }
                } else if (turn) {
                    // Calculate and display score
                    if (physicsController.calculateScore(gameMode)) {
                        score2++;
                        jLabel8.setText(score2 + "");
                    }

                    // Check to increase score by 1 point at most for each round
                    if (((oldScore2 + 1) == score2)
                            && ((b1.getVx() != 0 || b1.getVy() != 0)
                            || (b2.getVx() != 0 || b2.getVy() != 0)
                            || (b3.getVx() != 0 || b3.getVy() != 0))) {
                        // Reset variables for calculating score in next strike
                        PhysicsController.first = false;
                        PhysicsController.second = false;
                        PhysicsController.edgeCollide = 0;
                        PhysicsController.checkFirst = 0;
                    }

                    // Reset variables for calculating score in next strike
                    if ((b1.getVx() == 0 && b1.getVy() == 0)
                            && (b2.getVx() == 0 && b2.getVy() == 0)
                            && (b3.getVx() == 0 && b3.getVy() == 0)) {
                        // End of round so set old score with the new current score for further checking
                        oldScore2 = score2;
                        PhysicsController.first = false;
                        PhysicsController.second = false;
                        PhysicsController.edgeCollide = 0;
                        PhysicsController.checkFirst = 0;
                    }
                }
            }

            // End of the game
            // Whoever reaches 10 points first win the game
            if (score1 == 10) {
                for (Ball b : GameView.balls) {
                    b.setVx(0);
                    b.setVy(0);
                }
                JOptionPane.showMessageDialog(null, "Game Over\nPlayer1 wins");
                // Disable mouse listener
                enabled = false;
                score1 = 0;
                score2 = 0;
                // Go back to homescreen
                AppContainer.changePanel(new MenuView());
            } else if (score2 == 10) {
                for (Ball b : GameView.balls) {
                    b.setVx(0);
                    b.setVy(0);
                }
                JOptionPane.showMessageDialog(null, "Game Over\nPlayer2 wins!");
                // Disable mouse listener
                enabled = false;
                score1 = 0;
                score2 = 0;
                // Go back to homescreen
                AppContainer.changePanel(new MenuView());
            }

            // Fill up the power bar with the current power
            if (isMax) {
                powerBar.setValue(power);
            }
            repaint();
        }
    }

    private class MouseActionListener implements MouseMotionListener, MouseListener {

        @Override
        public void mouseDragged(MouseEvent e) {
            // Allow to accept mouse input
            if (enabled) {
                // Allow to drag the cue stick when all the balls stop moving
                if ((b1.getVx() == 0 && b1.getVy() == 0)
                        && (b2.getVx() == 0 && b2.getVy() == 0)
                        && (b3.getVx() == 0 && b3.getVy() == 0)) {

                    // Show power bar
                    powerBar.setVisible(true);

                    // Get the angle to calculate cue ball movement
                    if (p2.getName().equals("")) {
                        stick.getRadian(e.getX(), e.getY());
                    } else {
                        if (turn) {
                            stick.getRadian(b2, e.getX(), e.getY());
                        } else {
                            stick.getRadian(b3, e.getX(), e.getY());
                        }
                    }

                    // Allow power bar to increase
                    isMax = true;

                    // Repaint cue stick for each mouse drag
                    stick.stickIcon = new ImageIcon(CueStick.stickURL);
                    repaint();
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // Allow to accept mouse input
            if (enabled) {
                // Calculate x_velocity and y_velocity of the cue ball
                if (isMax) {
                    double vx, vy;
                    vx = Math.cos(-stick.radian) * powerBar.getPercentComplete() * 10;
                    vy = Math.sin(-stick.radian) * powerBar.getPercentComplete() * 10;

                    if (p2.getName().equals("")) {
                        b1.setVx(vx);
                        b1.setVy(vy);
                    } else {
                        if (turn) {
                            b2.setVx(vx);
                            b2.setVy(vy);
                        } else {
                            b3.setVx(vx);
                            b3.setVy(vy);
                        }

                        turn = !turn;
                    }

                    // Remove cue stick after the mouse is released
                    stick.stickIcon = new ImageIcon("");
                    repaint();
                }
                // Reset power 
                isMax = false;
                powerBar.setVisible(false);
            }
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

//    public static void main(String[] args) {
//        AppContainer.changePanel(new GameView(1, "", "a"));
//    }
}
