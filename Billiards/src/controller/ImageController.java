/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author CREAT10N
 */
public class ImageController {

    // Handling background image
    public static Image getBackgroundImage() {
        return new ImageIcon("src/images/background.jpg").getImage();
    }

    public static JLabel getBackgroundLabel() {
        JLabel label = new JLabel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(ImageController.getBackgroundImage(), 0, 0, 450, 350, null);
            }
        };
        label.setSize(450, 350);
        return label;
    }

    // Handling pool table image
    public static Image getTableImage() {
        return new ImageIcon("src/images/table.png").getImage();
    }

    public static JLabel getTableLabel() {
        JLabel label = new JLabel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.drawImage(ImageController.getTableImage(), 0, 0, 500, 250, null);
            }
        };
        label.setSize(500, 250);
        return label;
    }
}
