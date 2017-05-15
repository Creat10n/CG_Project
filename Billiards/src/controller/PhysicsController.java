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
    public static int first = 0;
    public static int second = 0;
    public static boolean isScore = false;

    public PhysicsController() {
    }

    public static PhysicsController getInstance() {
        if (physicsController == null) {
            physicsController = new PhysicsController();
        }
        return physicsController;
    }

    public boolean moveBall(Ball b) {
        detectCollision(b);
        
        //checking x coordinates
        if (!(b.getX() + b.getVx() > 455)) {
            b.setCx(b.getCx() + b.getVx());
            b.setX((int) b.getCx());
        }
        if (b.getX() + b.getVx() > 455) {
            b.setVx(-b.getVx());
        }
        if (b.getX() + b.getVx() < 28) {
            b.setVx(-b.getVx());
            b.setCx(b.getCx() + b.getVx());
            b.setX((int) b.getCx());
        }

        //checking y coordinates
        if (!(b.getY() + b.getVy() > 200)) {
            b.setCy(b.getCy() + b.getVy());
            b.setY((int) b.getCy());
        }
        if (b.getY() + b.getVy() > 200) {
            b.setVy(-b.getVy());
        }
        if (b.getY() + b.getVy() < 28) {
            b.setVy(-b.getVy());
            b.setCy(b.getCy() + b.getVy());
            b.setY((int) b.getCy());
        }
        return true;
    }

    public boolean detectCollision(Ball b) {
        Point p1, p2;
        p1 = new Point(b.getX() + 10, b.getY() + 10);
        try {
            for (Ball b1 : GameView.balls) {
                if (!b1.equals(b)) {
                    p2 = new Point(b1.getX() + 10, b1.getY() + 10);
                    if (p1.distance(p2) <= 20) {
                        handleCollision(b, b1);
                        return true;
                    }
                }
            }
        } catch (Exception ex) {
            determineScore(b);
        }
        return false;
    }

    public boolean handleCollision(Ball b1, Ball b2) {
        double x, y, d2;
        y = (b2.getY() - b1.getY());
        x = (b2.getX() - b1.getX());

        // distance squared
        d2 = x * x + y * y;
        if (d2 == 0) {
            return false;
        }
        double kii, kji, kij, kjj;

        // i is b1 
        // j is b2
        kji = (x * b1.getVx() + y * b1.getVy()) / d2; // k of j due to i
        kii = (x * b1.getVy() - y * b1.getVx()) / d2; // k of i due to i
        kij = (x * b2.getVx() + y * b2.getVy()) / d2; // k of i due to j
        kjj = (x * b2.getVy() - y * b2.getVx()) / d2; // k of j due to j
        
        double x1, y1, x2, y2;
        x1 = kij * x - kii * y;
        y1 = kij * y + kii * x;
        x2 = kji * x - kjj * y;
        y2 = kji * y + kjj * x;
        
        // set velocity of b1
        b1.setVy(y1);
        b1.setVx(x1);
        
        // set velocity of b2
        b2.setVy(y2);
        b2.setVx(x2);

        return true;
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

    public void determineScore(Ball b) {
        Point p1, p2;
        p1 = new Point(b.getX() + 10, b.getY() + 10);
        for (Ball b1 : GameView.balls) {
            if (!b1.equals(b)) {
                p2 = new Point(b1.getX() + 10, b1.getY() + 10);
                if (p1.distance(p2) <= 20
                        && (b1 == GameView.balls.get(1)
                        && b == GameView.balls.get(0))) {
                    first = 1;
                    first += second;
                    if (first == 2) {
                        isScore = true;
                    }
                    handleCollision(b, b1);
                }
                if (p1.distance(p2) <= 20
                        && (b1 == GameView.balls.get(2)
                        && b == GameView.balls.get(0))) {
                    second = 1;
                    second += first;
                    if (second == 2) {
                        isScore = true;
                    }
                    handleCollision(b, b1);
                }
            }
        }
    }
}
