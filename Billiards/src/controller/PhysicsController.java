/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
import view.GameView;

/**
 *
 * @author CREAT10N
 */
public class PhysicsController {

    private static PhysicsController physicsController;
    private static SoundController soundController;
    public static boolean first = false;
    public static boolean second = false;
    public static int edgeCollide = 0;
    public static int checkFirst = 0;

    public PhysicsController() {
        soundController = SoundController.getInstance();
    }

    public static PhysicsController getInstance() {
        if (physicsController == null) {
            physicsController = new PhysicsController();
        }
        return physicsController;
    }

    public void moveBall(Ball b) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        // Check ball collision
        detectCollision(b);

        // Playing area 
        // X coordinate: 30 - 475
        // Y coordinate: 30 - 220
        // Check x coordinates of the pool table
        // Change ball direction if collide 
        if (!(b.getX() + b.getVx() > 455)) {
            b.setX(b.getX() + b.getVx());
        }

        // Right edge collision
        if (b.getX() + b.getVx() > 455) {
            soundController.playCollision();
            b.setVx(-b.getVx());
            if (b.equals(GameView.balls.get(0))) {
                edgeCollide++;
            }
        }

        // Left edge collision
        if (b.getX() + b.getVx() < 30) {
            soundController.playCollision();
            b.setVx(-b.getVx());
            b.setX(b.getX() + b.getVx());
            if (b.equals(GameView.balls.get(0))) {
                edgeCollide++;
            }
        }

        // Check y coordinates of the pool table
        // Change ball direction if collide 
        if (!(b.getY() + b.getVy() > 200)) {
            b.setY(b.getY() + b.getVy());
        }

        // Bottom edge collision
        if (b.getY() + b.getVy() > 200) {
            soundController.playCollision();
            b.setVy(-b.getVy());
            if (b.equals(GameView.balls.get(0))) {
                edgeCollide++;
            }
        }

        // Top edge collision
        if (b.getY() + b.getVy() < 30) {
            soundController.playCollision();
            b.setVy(-b.getVy());
            b.setY(b.getY() + b.getVy());
            if (b.equals(GameView.balls.get(0))) {
                edgeCollide++;
            }
        }
    }

    public boolean detectCollision(Ball b) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        Point p1, p2;
        // Center point of the ball
        p1 = new Point((int) b.getX() + 10, (int) b.getY() + 10);

        // Check collision of that ball with the others
        for (Ball b1 : GameView.balls) {
            if (!b1.equals(b)) {
                // Center point of the second ball
                p2 = new Point((int) b1.getX() + 10, (int) b1.getY() + 10);

                // If 2 balls collide, change their velocity directions
                if (p1.distance(p2) <= 20) {
                    soundController.playCollision();
                    handleCollision(b, b1);
                    return true;
                }
            }
        }
        return false;
    }

    public void handleCollision(Ball b1, Ball b2) {

        // Distances to the collision point
        double Dx1, Dx2, Dy1, Dy2;

        // Center points of each ball
        double X1, X2, Y1, Y2;

        // Real distances (collision overlap)
        double DxR, DyR;

        // Real distance between ball centers
        double distance;

        // Ideal distances (no overlap)
        double Dx, Dy;

        // Straight and perpendicular velocities to collision
        double Vp1, Vp2, Vs1, Vs2;

        // New straight velocites during calculations
        double newVs1, newVs2;

        // Center = position + radius
        X1 = b1.getX() + 10;
        Y1 = b1.getY() + 10;
        X2 = b2.getX() + 10;
        Y2 = b2.getY() + 10;
//        System.out.println(X1);
//        System.out.println(Y1);
//        System.out.println(X2);
//        System.out.println(Y2);

        // Difference in x and y between 2 balls
        DxR = (X2 - X1);
        DyR = (Y2 - Y1);
//        System.out.println(DxR);
//        System.out.println(DyR);

        // Distance between 2 balls
        distance = Math.sqrt(DxR * DxR + DyR * DyR);
//        System.out.println(distance);

        // Change X and Y coordinates to get a perfect collision which is 20
        Dx = 20 * DxR / distance;
        Dy = 20 * DyR / distance;
//        System.out.println(Dx);
//        System.out.println(Dy);

        X2 = (X2 + (Dx - DxR));
        Y2 = (Y2 + (Dy - DyR));
        b2.setX(X2 - 10);
        b2.setY(Y2 - 10);
//        System.out.println(X2);
//        System.out.println(Y2);
//        System.out.println(b2.getX());
//        System.out.println(b2.getY());

        // Find the x and y distances from the centers of each ball to the collision point
        Dx1 = 0.5 * (X2 - X1);
        Dx2 = 0.5 * (X2 - X1);

        Dy1 = 0.5 * (Y2 - Y1);
        Dy2 = 0.5 * (Y2 - Y1);

        // Calculate the velocity components of each ball parallel to the collision and perpendicular to it
        Vs1 = paVelocity(b1.getVx(), b1.getVy(), Dx1, Dy1, 10);
        Vp1 = peVelocity(b1.getVx(), b1.getVy(), Dx1, Dy1, 10);
//        System.out.println(Vs1);
//        System.out.println(Vp1);

        Vs2 = paVelocity(b2.getVx(), b2.getVy(), Dx2, Dy2, 10);
        Vp2 = peVelocity(b2.getVx(), b2.getVy(), Dx2, Dy2, 10);
//        System.out.println(Vs2);
//        System.out.println(Vp2);

        // Find new parallel velocities for each ball
        newVs1 = collisionVelocity(Vs1, Vs2, 1, 1);
        newVs2 = collisionVelocity(Vs2, Vs1, 1, 1);
//        System.out.println(newVs1);
//        System.out.println(newVs2);

        // Find new X and Y velocities for each ball using the new parallel velocity and the unchanged perpendicular velocity 
        b1.setVx(xVelocity(newVs1, Vp1, Dx1, Dy1, 10));
        b1.setVy(yVelocity(newVs1, Vp1, Dx1, Dy1, 10));
//        System.out.println(b1.getVx());
//        System.out.println(b1.getVy());

        b2.setVx(xVelocity(newVs2, Vp2, Dx2, Dy2, 10));
        b2.setVy(yVelocity(newVs2, Vp2, Dx2, Dy2, 10));
//        System.out.println(b2.getVx());
//        System.out.println(b2.getVy());
    }

    // Velocity parallel to the collision
    public double paVelocity(double Vx, double Vy, double Dx, double Dy, double R) {
        return (Vx * Dx + Vy * Dy) / R;
    }

    // Velocity perpendicular to the collision
    public double peVelocity(double Vx, double Vy, double Dx, double Dy, double R) {
        return (Vy * Dx - Vx * Dy) / R;
    }

    // x velocity from paVelocity and peVelocity
    public double xVelocity(double Vs, double Vp, double Dx, double Dy, double R) {
        return (Vs * Dx - Vp * Dy) / R;
    }

    // y velocity from paVelocity and peVelocity
    public double yVelocity(double Vs, double Vp, double Dx, double Dy, double R) {
        return (Vs * Dy + Vp * Dx) / R;
    }

    // Returns velocity of a ball after collision
    public double collisionVelocity(double V1, double V2, double m1, double m2) {
        return (V1 * (m1 - m2) + V2 * (2 * m2)) / (m1 + m2);
    }

    public boolean decreaseVelocity(ArrayList<Ball> balls) {
        // Each ball will have its velocity decreased by 0.8/time
        for (Ball b : balls) {
            if (!(b.getVx() == 0 && b.getVy() == 0)) {
                if (b.getVy() == 0) {
                    b.setVx(b.getVx() * 0.8);
                } else if (b.getVx() == 0) {
                    b.setVy(b.getVy() * 0.8);
                } else {
                    double tan = b.getVy() / b.getVx();
                    b.setVx(b.getVx() * 0.8);
                    b.setVy(b.getVx() * tan);
                }
                double velocity = Math.sqrt(Math.pow(b.getVx(), 2) + Math.pow(b.getVy(), 2));
                if (velocity < 0.2) {
                    b.setVx(0);
                    b.setVy(0);
                }
            }
        }
        return true;
    }

    public boolean calculateScore(int mode) {
        Point p1, p2, p3;

        // Center point of each balls
        p1 = new Point((int) GameView.balls.get(0).getX() + 10, (int) GameView.balls.get(0).getY() + 10);
        p2 = new Point((int) GameView.balls.get(1).getX() + 10, (int) GameView.balls.get(1).getY() + 10);
        p3 = new Point((int) GameView.balls.get(2).getX() + 10, (int) GameView.balls.get(2).getY() + 10);

        // Score Calculation for 1Player mode
        if (GameView.p2.getName().equals("")) {
            // Check which is the first ball that the cue ball collides
            if (checkFirst == 0) {
                if (p1.distance(p2) <= 20) {
                    checkFirst = 1;
                    first = true;
                }
                if (p1.distance(p3) <= 20) {
                    checkFirst = 2;
                    second = true;
                }
            }

            // After having the first ball-to-ball collision
            // Check the number of edges collided and second ball-to-ball collision
            // Simple mode
            if (mode == 1) {
                if (checkFirst == 1) {
                    if (p2.distance(p3) <= 20) {
                        second = true;
                    }
                } else if (checkFirst == 2) {
                    if (p3.distance(p2) <= 20) {
                        first = true;
                    }
                }
                // Three Cushions mode
            } else if (mode == 2) {
                if (checkFirst == 1) {
                    if ((p1.distance(p3) <= 20) && (edgeCollide > 2)) {
                        second = true;
                    }
                } else if (checkFirst == 2) {
                    if ((p1.distance(p2) <= 20) && (edgeCollide > 2)) {
                        first = true;
                    }
                }
            }
            // Score Calculation for 2Player mode 
        } else {
            if (!GameView.turn) {
                // Check which is the first ball that the 1st player's cue ball collides
                if (checkFirst == 0) {
                    if (p2.distance(p1) <= 20) {
                        checkFirst = 1;
                        first = true;
                    }
                    if (p2.distance(p3) <= 20) {
                        checkFirst = 2;
                        second = true;
                    }
                }

                // After having the first ball-to-ball collision
                // Check the number of edges collided and second ball-to-ball collision
                // Simple mode
                if (mode == 1) {
                    if (checkFirst == 1) {
                        if (p1.distance(p3) <= 20) {
                            second = true;
                        }
                    } else if (checkFirst == 2) {
                        if (p3.distance(p1) <= 20) {
                            first = true;
                        }
                    }
                    // Three Cushions mode
                } else if (mode == 2) {
                    if (checkFirst == 1) {
                        if ((p2.distance(p3) <= 20) && (edgeCollide > 2)) {
                            second = true;
                        }
                    } else if (checkFirst == 2) {
                        if ((p2.distance(p1) <= 20) && (edgeCollide > 2)) {
                            first = true;
                        }
                    }
                }
            } else {
                // Check which is the first ball that the 2nd player's cue ball collides
                if (checkFirst == 0) {
                    if (p3.distance(p1) <= 20) {
                        checkFirst = 1;
                        first = true;
                    }
                    if (p3.distance(p2) <= 20) {
                        checkFirst = 2;
                        second = true;
                    }
                }

                // After having the first ball-to-ball collision
                // Check the number of edges collided and second ball-to-ball collision
                // Simple mode
                if (mode == 1) {
                    if (checkFirst == 1) {
                        if (p1.distance(p2) <= 20) {
                            second = true;
                        }
                    } else if (checkFirst == 2) {
                        if (p2.distance(p1) <= 20) {
                            first = true;
                        }
                    }
                    // Three Cushions mode
                } else if (mode == 2) {
                    if (checkFirst == 1) {
                        if ((p3.distance(p2) <= 20) && (edgeCollide > 2)) {
                            second = true;
                        }
                    } else if (checkFirst == 2) {
                        if ((p3.distance(p1) <= 20) && (edgeCollide > 2)) {
                            first = true;
                        }
                    }
                }
            }
        }

        // If met all the requirements then return true
        if (first && second) {
            return true;
        }
        return false;
    }
}
