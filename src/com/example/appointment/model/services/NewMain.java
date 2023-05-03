/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.example.appointment.model.services;

import com.example.appointment.model.entities.User;

/**
 *
 * @author Lou
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserService su = new UserService();
        String user="testy";
        String pword ="test";
        User used = new User();
       used = su.GetUser(user, pword);
       if (used.isAdmin()){
           System.out.println("Admin");
       }else{
           System.out.println("User");
       }
        
    }
    
}
