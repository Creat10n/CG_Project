/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

/**
 *
 * @author CREAT10N
 */
public class GameModeView extends JPanel {

    // "Simple Mode" button
    private JButton jButton1;

    // "Three Cushions Mode" button
    private JButton jButton2;

    public GameModeView(String playerName1, String playerName2) {
        initComponents(playerName1, playerName2);
        // Add background
        add(ImageController.getBackgroundLabel());
    }

    private void initComponents(String playerName1, String playerName2) {
        jButton1 = new JButton("Simple Mode");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new GameView(1, playerName1, playerName2));
            }
        });

        jButton2 = new JButton("Three Cushions Mode");
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new GameView(2, playerName1, playerName2));
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(jButton1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(jButton2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addGap(150, 150, 150))
                                .addContainerGap(125, Short.MAX_VALUE)
                        ));
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jButton1)
                                .addGap(50, 50, 50)
                                .addComponent(jButton2)
                                .addContainerGap(100, Short.MAX_VALUE))
        );
    }

//    public static void main(String[] args) {
//        AppContainer.changePanel(new GameModeView(""));
//    }
}
