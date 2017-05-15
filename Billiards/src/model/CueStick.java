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

    private int x, y, WIDTH, HEIGHT, a, b;
    public double radian;
    private Graphics2D g;
    public ImageIcon stickIcon;

    public CueStick(int x, int y) {
        stickIcon = new ImageIcon("src/images/stick.png");
        this.x = x;
        this.y = y;
        radian = 0;
        WIDTH = stickIcon.getIconWidth();
        HEIGHT = stickIcon.getIconHeight();
        a = GameView.balls.get(0).getX() - WIDTH;
        b = GameView.balls.get(0).getY() + HEIGHT / 2;
    }

    public void paint(Graphics g) {
        this.g = (Graphics2D) g;
        this.g.rotate(-radian, x, y);
        this.g.drawImage(stickIcon.getImage(), a, b, null);
    }

    public void getRadian(double mouseX, double mouseY) {
        double tan, deltaHeight, angle;
        boolean isTurned = false;

        if ((x + WIDTH - mouseX) > 0) {
            deltaHeight = x + WIDTH - mouseX;
            a = GameView.balls.get(0).getX() - WIDTH;
            b = GameView.balls.get(0).getY() + HEIGHT / 2;
        } else {
            a = GameView.balls.get(0).getX() - WIDTH;
            b = GameView.balls.get(0).getY() + HEIGHT / 2;
            isTurned = true;
            deltaHeight = x + WIDTH - mouseX;
        }

        if (mouseY == y) {
            tan = 0;
        } else {
            tan = (mouseY - y - HEIGHT) / deltaHeight;
        }

        if (!isTurned) {
            angle = Math.atan(tan);
        } else {
            angle = Math.atan(tan) + Math.PI;
        }

        x = GameView.balls.get(0).getX() + 10;
        y = GameView.balls.get(0).getY() + 10;
        radian = angle;
    }
}
