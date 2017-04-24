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
public class CueStick {

    int x, y, WIDTH, HEIGHT, a, b;
    double radian;
    Graphics2D g;
    ImageIcon stickIcon;

    public CueStick(int x, int y) {
        stickIcon = new ImageIcon("src/images/cuestick.jpg");
        this.x = x;
        this.y = y;
        radian = 0;
//        WIDTH = stickIcon.getIconWidth();
//        HEIGHT = stickIcon.getIconHeight();

        
    }
    
    public void paint(Graphics g) {
        this.g = (Graphics2D)g;
        this.g.rotate(-radian, x, y);
        this.g.drawImage(stickIcon.getImage(), a, b, null);
    }
    
//    public void getRadian(double mouseX, double mouseY) {
//        double tan, deltaHeight, angle;
//        
//    }
}
