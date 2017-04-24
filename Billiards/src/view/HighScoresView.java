/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author CREAT10N
 */
public class HighScoresView extends JPanel {

    // "HighScore" title
    private JLabel jLabel;

    // "Back" button
    private JButton jButton;

    // Player names and scores
    private JTextArea jTextArea;

    public HighScoresView() {
        initComponents();

        // Add background
        add(ImageController.getBackgroundLabel());

        // Get highscores from database
        ScoreController.highScores();

        // Print out names and scores
        ArrayList<Integer> scores = ScoreController.getScores();
        ArrayList<String> names = ScoreController.getNames();
        String str = "";
        for (int i = 0; i < scores.size(); i++) {
            if (i == scores.size() - 1) {
                str += "    " + names.get(i) + "\t\t" + scores.get(i);
                break;
            }
            str += "    " + names.get(i) + "\t\t" + scores.get(i) + "\n\n";
        }
        jTextArea.setText(str);
    }

    private void initComponents() {
        jLabel = new JLabel("High Scores");
        jLabel.setFont(new Font("Lucida Calligraphy", 3, 36));
        jLabel.setForeground(new Color(255, 204, 51));

        jButton = new JButton("Back");
        // Return to MainMenu
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppContainer.changePanel(new MainMenuPanel());
            }
        });

        jTextArea = new JTextArea();
        jTextArea.setColumns(18);
        jTextArea.setEditable(false);
        jTextArea.setEnabled(false);
        jTextArea.setDisabledTextColor(Color.black);

        // Layout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jTextArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(100, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(90, 90, 90)
                                .addComponent(jLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(110, 110, 110))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton)
                                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel)
                                .addGap(30, 30, 30)
                                .addComponent(jTextArea, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jButton)
                                .addContainerGap())
        );
    }
    
//    public static void main(String[] args) {
//        AppContainer.changePanel(new HighScoresView());
//    }
}
