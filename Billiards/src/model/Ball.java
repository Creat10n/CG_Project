/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author CREAT10N
 */
public class Ball {

    private int x, y;
    private final static int SIZE = 20;
    private double vx, vy, cx, cy;
    private ImageIcon ballIcon;
    private boolean collide;

    public Ball(int x, int y, int type) {
        setPoint(x, y);
        cx = x;
        cy = y;
        vx = 0;
        vy = 0;
        setType(type);
        collide = false;
    }

    public void setPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setType(int type) {
        ballIcon = new ImageIcon("src/images/ball" + type + ".png");
    }

    public void paint(Graphics g) {
        g.drawImage(getType().getImage(), getX(), getY(), SIZE, SIZE, null);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the vx
     */
    public double getVx() {
        return vx;
    }

    /**
     * @param vx the vx to set
     */
    public void setVx(double vx) {
        this.vx = vx;
    }

    /**
     * @return the vy
     */
    public double getVy() {
        return vy;
    }

    /**
     * @param vy the vy to set
     */
    public void setVy(double vy) {
        this.vy = vy;
    }

    /**
     * @return the cx
     */
    public double getCx() {
        return cx;
    }

    /**
     * @param cx the cx to set
     */
    public void setCx(double cx) {
        this.cx = cx;
    }

    /**
     * @return the cy
     */
    public double getCy() {
        return cy;
    }

    /**
     * @param cy the cy to set
     */
    public void setCy(double cy) {
        this.cy = cy;
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
