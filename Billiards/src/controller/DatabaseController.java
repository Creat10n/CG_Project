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

    private static Connection con;

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/carrom", "cgAdmin", "billiards");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    public void addHighScore(String name, String score) {
        try {
            String sql = "INSERT INTO HighScore(Name, Score) values(?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, score);
            
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
