/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appointment.model.services;


import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Promotion;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;     
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

 

/**
 *
 * @author oumaima
 */
public class ServicePromotion implements IService<Promotion> {

    Connection cnx = DatabaseHandler.getInstance().getCnx();

    @Override
    public void ajouter(Promotion t) {
        String req = "INSERT INTO promotion (date_debut_at, date_fin_at, pourcentage) VALUES (?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
           pst.setObject(1, t.getStartDate());
           pst.setObject(2, t.getEndDate());
        

            pst.setInt(3, t.getPercentage());
            pst.executeUpdate();
            System.out.println("Promotion ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    

    @Override
    public void modifier(Promotion t) {
        String req = "UPDATE promotion SET date_debut_at=?, date_fin_at=?, pourcentage=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setDate(1, new java.sql.Date(t.getStartDate().getTime()));
            pst.setDate(2, new java.sql.Date(t.getEndDate().getTime()));
            pst.setInt(3, t.getPercentage());
            pst.setInt(4, t.getId());
            pst.executeUpdate();
            System.out.println("Promotion modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Promotion t) {
        String req = "DELETE FROM promotion WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Promotion supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Promotion> afficher() {
        List<Promotion> list = new ArrayList();

        String req = "SELECT * FROM promotion";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date dateDebutAt = rs.getDate("date_debut_at");
                Date dateFinAt = rs.getDate("date_fin_at");
                int pourcentage = rs.getInt("pourcentage");

                Promotion promotion = new Promotion(id, dateDebutAt, dateFinAt, pourcentage);
                list.add(promotion);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    public Promotion findOne(int id) {
        Promotion prom = new Promotion();

        String req = "SELECT * FROM promotion WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                
                prom.setStartDate( rs.getDate("date_debut_at"));
                prom.setEndDate(rs.getDate("date_fin_at"));
                prom.setPercentage(rs.getInt("pourcentage"));

                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return prom;
    }

   
}
