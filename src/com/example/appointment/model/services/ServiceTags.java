/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.services;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Tags;
import com.example.appointment.model.services.IService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nasr
 */
public class ServiceTags implements  IService<Tags> {
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(Tags t) {
               String req = "INSERT INTO post_tag(post_id  ,tag_id ) VALUES(? ,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);

            
            pst.setInt(1, t.getPostid());
            pst.setInt(2, t.getTagid());

            pst.executeUpdate();
            System.out.println("tag ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void modifier(Tags t) {
    }

    @Override
    public void supprimer(Tags t) {
                String req = "DELETE FROM post_tag WHERE post_id=? and tag_id=? ";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getPostid());
            pst.setInt(2, t.getTagid());
            pst.executeUpdate();
            System.out.println("tag removed !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public List<Tags> afficher() {
             List<Tags> list = new ArrayList<>();
        
        String req = "SELECT * FROM tag";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Tags(result.getString("name")));
            }
            System.out.println("tags récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list; 
        
    }
    
}
