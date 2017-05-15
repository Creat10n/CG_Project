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
public class MenuView extends JPanel {

    // "Carrom" label
    private JLabel jLabel;
    
    // "New Game" button
    private JButton jButton1;
    
    // "High Score" button
    private JButton jButton2;
    
    // "Help" button
    private JButton jButton3;
    
    // "Exit" button
    private JButton jButton4;

    public MenuView() {
        initComponents();
        // Add background
        add(ImageController.getBackgroundLabel());
    }

    private void initComponents() {
        // Title
        jLabel = new JLabel("Carrom");
        jLabel.setFont(new Font("Lucida Calligraphy", 3, 36));
        jLabel.setForeground(new Color(255, 204, 51));

        jButton1 = new JButton("New Game");
        // Go to LoginView
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new LoginView());
            }
        });

        jButton2 = new JButton("High Score");
        // Go to HighScoresView
        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new HighScoresView());
            }
        });

        jButton3 = new JButton("Help");
        // Go to HelpView
        jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new HelpView());
            }
        });

        jButton4 = new JButton("Exit");
        // Exit program
        jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(110, Short.MAX_VALUE)
                                .addComponent(jLabel, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
                                .addGap(100, 100, 100))
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(jButton1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(jButton2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(jButton3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                                        .addComponent(jButton4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGap(40, 40, 40)
                                .addComponent(jButton1)
                                .addGap(20, 20, 20)
                                .addComponent(jButton2)
                                .addGap(20, 20, 20)
                                .addComponent(jButton3)
                                .addGap(20, 20, 20)
                                .addComponent(jButton4)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
    }
}
