/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Ball;
import java.awt.*;
import java.util.*;
import view.GameView;

/**
 *
 * @author CREAT10N
 */
public class PhysicsController {

    private static PhysicsController physicsController;
    public static boolean first = false;
    public static boolean second = false;

    public PhysicsController() {
    }

    public static PhysicsController getInstance() {
        if (physicsController == null) {
            physicsController = new PhysicsController();
        }
        return physicsController;
    }

    public boolean moveBall(Ball b) {
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
        if (b.getX() + b.getVx() > 455) {
            b.setVx(-b.getVx());
        }
        if (b.getX() + b.getVx() < 30) {
            b.setVx(-b.getVx());
            b.setX(b.getX() + b.getVx());
        }

        // Check y coordinates of the pool table
        // Change ball direction if collide 
        if (!(b.getY() + b.getVy() > 200)) {
            b.setY(b.getY() + b.getVy());
        }
        if (b.getY() + b.getVy() > 200) {
            b.setVy(-b.getVy());
        }
        if (b.getY() + b.getVy() < 30) {
            b.setVy(-b.getVy());
            b.setY(b.getY() + b.getVy());
        }
        return true;
    }

    public boolean detectCollision(Ball b) {
        Point p1, p2;
        // Center point of the ball
        p1 = new Point((int) b.getX() + 10, (int) b.getY() + 10);

        // Check collision of that ball with the others
        for (Ball b1 : GameView.balls) {
            if (!b1.equals(b)) {
                // Center point of the second ball
                p2 = new Point((int) b1.getX() + 10, (int) b1.getY() + 10);

                // If 2 balls collide, change their velocity directions
                if (p1.distance(p2) < 20) {
                    handleCollision(b, b1);
                    return true;
                }
            }
        }
        return false;
    }

    public void handleCollision(Ball b1, Ball b2) {
        double x, y, d;
        y = b2.getY() - b1.getY();
        x = b2.getX() - b1.getX();

        // Squared distance of 2 balls
        d = x * x + y * y;

        double kii, kji, kij, kjj;

        // i is b1 
        // j is b2
        kji = (x * b1.getVx() + y * b1.getVy()) / d; // k of j due to i
        kii = (x * b1.getVy() - y * b1.getVx()) / d; // k of i due to i
        kij = (x * b2.getVx() + y * b2.getVy()) / d; // k of i due to j
        kjj = (x * b2.getVy() - y * b2.getVx()) / d; // k of j due to j

        double x1, y1, x2, y2;
        x1 = kij * x - kii * y;
        y1 = kij * y + kii * x;
        x2 = kji * x - kjj * y;
        y2 = kji * y + kjj * x;

        // Set velocity of b1
        b1.setVy(y1);
        b1.setVx(x1);

        // Set velocity of b2
        b2.setVy(y2);
        b2.setVx(x2);
    }

    public boolean decreaseVelocity(ArrayList<Ball> balls) {
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

    public boolean calculateScore() {
        Point p1, p2, p3;

        // Center point of each balls
        p1 = new Point((int) GameView.balls.get(0).getX() + 10, (int) GameView.balls.get(0).getY() + 10);
        p2 = new Point((int) GameView.balls.get(1).getX() + 10, (int) GameView.balls.get(1).getY() + 10);
        p3 = new Point((int) GameView.balls.get(2).getX() + 10, (int) GameView.balls.get(2).getY() + 10);

        // Check first ball collision
        if (p1.distance(p2) < 20) {
            first = true;
            if (p1.distance(p3) < 20) {
                second = true;
            }
        }

        // Check second ball collision
        if (p1.distance(p3) < 20) {
            second = true;
            if (p1.distance(p2) < 20) {
                first = true;
            }
        }

        // If the cue ball both collide with the other balls then return true
        if (first && second) {
            return true;
        }
        return false;
    }
}
