/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.*;

/**
 *
 * @author CREAT10N
 */
public class AppContainer extends JFrame {

    private static JPanel currentPanel = new JPanel();
    private static AppContainer mainFrame;

    private AppContainer() {
        mainFrame = this;
        setResizable(false);
        currentPanel = new JPanel();
        add(currentPanel);
        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private AppContainer(JPanel initial) {
        this();
        changePanel(initial);
    }

    public static void changePanel(JPanel newPanel) {
        if (mainFrame == null) {
            mainFrame = new AppContainer();
        }
        mainFrame.remove(currentPanel);
        currentPanel = newPanel;
        mainFrame.add(currentPanel);
        mainFrame.pack();
    }

    public static void main(String[] args) {
        new AppContainer(new MainMenuPanel());
    }
}
