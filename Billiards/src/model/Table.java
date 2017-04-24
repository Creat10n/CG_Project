/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.*;

/**
 *
 * @author CREAT10N
 */
public class Table {

    private Point[] corners;

    public Table() {
    }

    public Table(Point[] corners) {
        this.corners = corners;
    }

    /**
     * @return the corners
     */
    public Point[] getCorners() {
        return corners;
    }

    /**
     * @param corners the corners to set
     */
    public void setCorners(Point[] corners) {
        this.corners = corners;
    }
}
