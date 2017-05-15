/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.GroupLayout.*;

/**
 *
 * @author CREAT10N
 */
public class HelpView extends JPanel {

    // "Back" button
    private JButton jButton;

    // Game rules
    private JTextArea jTextArea;

    public HelpView() {
        initComponents();

        // Add background
        add(ImageController.getBackgroundLabel());
    }

    private void initComponents() {
        jButton = new JButton("Back");
        // Return to MenuView 
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new MenuView());
            }
        });

        jTextArea = new JTextArea();
        jTextArea.setEditable(false);

        // Contents of the game rules
        String str = "\t- - - CARROM THREE CUSHION - - -\n\n\n";
        str += "            OBJECT OF THE GAME\n\n\n";
        str += "            The objective of this game is that players try to score as many points as\n they can under 120 seconds (2 minutes). ";
        str += "In order to score points, players\n carrom their cue ball to hit a minimum of 3 cushions and collide with 2 other\n balls on the table in one strike.";
        jTextArea.setText(str);

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jButton, Alignment.TRAILING)
                                        .addComponent(jTextArea, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jTextArea, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton)
                                .addContainerGap())
        );
    }

//    public static void main(String[] args) {
//        AppContainer.changePanel(new HelpView());
//    }
}
