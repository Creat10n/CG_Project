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

/**
 *
 * @author CREAT10N
 */
public class LoginView extends JPanel {

    // "Player name" label
    private JLabel jLabel1;
    private JLabel jLabel2;

    // Text field for input name
    private JTextField jTextField1;
    private JTextField jTextField2;

    // "Play" button
    private JButton jButton;

    public LoginView(int players) {
        initComponents(players);
        // Add background
        add(ImageController.getBackgroundLabel());
    }

    private void initComponents(int players) {
        jLabel1 = new JLabel("Player name : ");
        jLabel1.setFont(new Font("Times New Roman", 3, 15));
        jLabel1.setForeground(new Color(255, 204, 51));

        jLabel2 = new JLabel("Player name: ");
        jLabel2.setFont(new Font("Times New Roman", 3, 15));
        jLabel2.setForeground(new Color(255, 204, 51));

        // Setup a text field for typing player name
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();

        if (players == 1) {
            jTextField2.setEditable(false);
        }

        jButton = new JButton("Next");
        // Go to GameView
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (players == 1) {
                    if (!jTextField1.getText().equals("")) {
                        AppContainer.changePanel(new GameModeView(jTextField1.getText(), jTextField2.getText()));
                    }
                } else if (players == 2) {
                    if (!jTextField1.getText().equals("")
                            && !jTextField2.getText().equals("")
                            && !jTextField1.getText().equals(jTextField2.getText())) {
                        AppContainer.changePanel(new GameModeView(jTextField1.getText(), jTextField2.getText()));
                    }
                }
            }
        });

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addContainerGap(100, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(jButton)
                                .addGap(100, 100, 100))
        );
    }

//    public static void main(String[] args) {
//        AppContainer.changePanel(new LoginView(1));
//    }
}
