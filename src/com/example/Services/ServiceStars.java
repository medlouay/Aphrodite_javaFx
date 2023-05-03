package com.example.Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.aphrodite.entities.Stars;
import com.example.appointment.DB.DatabaseHandler;
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
public class ServiceStars  implements IService<Stars>{
    Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(Stars t) {
        String req = "INSERT INTO stars(idpost_id ,u_id , rate_index ) VALUES(? ,? ,?);";
        try {
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);

            
            pst.setInt(1, t.getIdPost());
            pst.setInt(2,t.getUserId() );
            pst.setDouble(3, t.getRateIndex());

           
            pst.executeUpdate();
            System.out.println("review ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Stars t) {
    }

    @Override
    public void supprimer(Stars t) {
    }

    @Override
    public List<Stars> afficher() {
        List<Stars> list = new ArrayList<>();
        
        String req = "SELECT * FROM Productreview";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new Stars(result.getInt("id"), result.getInt("idpost_id"), result.getInt("u_id"),result.getDouble("rate_index")));
            }
            System.out.println("Users récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
        public Double PostRate(int PostId) {
        List<Stars> list = new ArrayList<>();
        
        String req = "SELECT avg(rate_index) FROM Stars Where idpost_id = " + PostId;
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            if(result.next()) {
                return result.getDouble(1);
            }
            System.out.println("Users récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return 0d;
    }
    
}
