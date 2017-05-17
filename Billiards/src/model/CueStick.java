/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.*;
import javax.swing.*;
import view.GameView;

/**
 *
 * @author CREAT10N
 */
public class CueStick {

    private final int WIDTH, HEIGHT;
    private int x, y, a, b;
    public double radian;
    private Graphics2D g;
    public ImageIcon stickIcon;

    public CueStick() {
        stickIcon = new ImageIcon("src/images/stick.png");
        // Position of the cue ball 
        // The cue stick will rotate around this point
        x = (int) GameView.balls.get(0).getX() + 10;
        y = (int) GameView.balls.get(0).getY() + 10;
        radian = 0;
        WIDTH = stickIcon.getIconWidth();
        HEIGHT = stickIcon.getIconHeight();
        // Position to draw the cue stick
        a = (int) GameView.balls.get(0).getX() - WIDTH;
        b = (int) GameView.balls.get(0).getY() + HEIGHT / 2;
    }

    public void paint(Graphics g) {
        this.g = (Graphics2D) g;
        this.g.rotate(-radian, x, y);
        this.g.drawImage(stickIcon.getImage(), a, b, null);
    }

    public void getRadian(double mouseX, double mouseY) {
        double tan, angle;
        boolean isTurned = false;
        double delta = x + WIDTH - mouseX;

        // Check if the mouse position is on the right of the cue ball
        if ((x + WIDTH - mouseX) <= 0) {
            isTurned = true;
        }

        if (mouseY == y) {
            tan = 0;
        } else {
            tan = (mouseY - (y + HEIGHT)) / delta;
        }

        if (!isTurned) {
            angle = Math.atan(tan);
        } else {
            angle = Math.atan(tan) + Math.PI;
        }

        // Set new postion of cue ball and cue stick
        a = (int) GameView.balls.get(0).getX() - WIDTH;
        b = (int) GameView.balls.get(0).getY() + HEIGHT / 2;
        x = (int) GameView.balls.get(0).getX() + 10;
        y = (int) GameView.balls.get(0).getY() + 10;
        radian = angle;
    }
}
