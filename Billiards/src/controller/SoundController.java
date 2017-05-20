/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import javax.sound.sampled.*;

/**
 *
 * @author CREAT10N
 */
public class SoundController {

    private static SoundController soundController;

    public SoundController() {
    }

    public static SoundController getInstance() {
        if (soundController == null) {
            soundController = new SoundController();
        }
        return soundController;
    }

    // Play sound when ball-to-ball collision or ball-to-edge collision
    public void playCollision() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        File f = new File("src/sounds/collision.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(f);
        Clip clip = null;
        try {
            DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            ais.close();
        }
        clip.start();
    }
}
