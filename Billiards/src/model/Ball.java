/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

/**
 *
 * @author CREAT10N
 */
public class Ball {

    private double x, y;
    private final static int SIZE = 20;
    private double vx, vy;
    private ImageIcon ballIcon;
    private boolean collide;
    private URL ballURL;

    public Ball(int x, int y, int type) {
        setPoint(x, y);
        vx = 0;
        vy = 0;
        setType(type);
        collide = false;
    }

    public void setPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setType(int type) {
        ballURL = Ball.class.getResource("images/ball" + type + ".png");
        ballIcon = new ImageIcon(ballURL);
    }

    public void paint(Graphics g) {
        g.drawImage(getType().getImage(), (int)getX(), (int)getY(), SIZE, SIZE, null);
    }

    /**
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return the horizontal velocity
     */
    public double getVx() {
        return vx;
    }

    /**
     * @param vx the horizontal velocity to set
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @return the vertical velocity
     */
    public double getVy() {
        return vy;
    }

    /**
     * @param vy the vertical velocity to set
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * @return the ballIcon
     */
    public ImageIcon getType() {
        return ballIcon;
    }

    /**
     * @param ballIcon the ballIcon to set
     */
    public void setType(ImageIcon ballIcon) {
        this.ballIcon = ballIcon;
    }

    /**
     * @return the hit
     */
    public boolean isCollide() {
        return collide;
    }

    /**
     * @param collide the collide to set
     */
    public void setCollide(boolean collide) {
        this.collide = collide;
    }
}
