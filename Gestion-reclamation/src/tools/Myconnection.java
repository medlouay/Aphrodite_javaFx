/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class Myconnection {
    
    public String url="jdbc:mysql://localhost:3306/aphrodite";
    public String login="root";
    public String pwd="";
    Connection cnx;
    public static Myconnection instance;
    
    
    public Myconnection(){
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public Connection getcnx(){
        return cnx;
    }
    public static Myconnection getInstance(){
        if(instance == null){
            instance = new Myconnection();
        }
        return instance;
    }

}

