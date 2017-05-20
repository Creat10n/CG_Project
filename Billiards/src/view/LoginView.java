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
    private JLabel jLabel;

    // Text field for input name
    private JTextField jTextField;

    // "Play" button
    private JButton jButton;

    public LoginView() {
        initComponents();
        // Add background
        add(ImageController.getBackgroundLabel());
    }

    private void initComponents() {
        jLabel = new JLabel("Player name: ");
        jLabel.setFont(new Font("Times New Roman", 3, 15));
        jLabel.setForeground(new Color(255, 204, 51));

        // Setup a text field for typing player name
        jTextField = new JTextField();

        jButton = new JButton("Play");
        // Go to GameView
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println(jTextField.getText());
                if (!jTextField.getText().equals("")) {
                    AppContainer.changePanel(new GameView(jTextField.getText()));
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
                                        .addComponent(jLabel))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jTextField, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                                .addContainerGap(110, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(100, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jLabel)
                                        .addComponent(jTextField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
                                .addGap(50, 50, 50)
                                .addComponent(jButton)
                                .addGap(100, 100, 100))
        );
    }

//    public static void main(String[] args) {
//        AppContainer.changePanel(new LoginView());
//    }
}
