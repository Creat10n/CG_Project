/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;

/**
 *
 * @author CREAT10N
 */
public class DatabaseController {

    // Getting database connection
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/comgraph","comgraphadmin","billiards");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Billiards", "cgAdmin", "billiards");
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
