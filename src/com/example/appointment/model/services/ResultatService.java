/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.services;


import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.resultat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benza
 */
public class ResultatService {  
    private Connection conn;
    private String requete;

    public ResultatService() {
        conn = DatabaseHandler.getInstance().getCnx();
    }
    public void insertRes(resultat r) {
        String requete = "INSERT INTO resultat (diagnostic_id,action,possibility,doctor_note,urgency) VALUES (?,?,?,?,?)";
        
        
        try {
           
            

            
            PreparedStatement rec = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            rec.setInt(1, r.getDiagnostic_id());
            rec.setString(2, r.getAction());
            rec.setInt(3, r.getPossibility());
            rec.setString(4, r.getDoctor_note());
         
            rec.setString(5, "Yes");
               

            

          
            rec.executeUpdate();

            ResultSet rs = rec.getGeneratedKeys();
            if (rs.next()) {
                int recID = rs.getInt(1);
                r.setId(recID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<resultat> readAll() {
        String requete = "SELECT resultat.* FROM resultat ";
        List<resultat> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                resultat r;
                
                r = new resultat(rs.getInt("id"), rs.getInt("diagnostic_id"), rs.getString("action"), rs.getInt("possibility"), rs.getString("doctor_note"),rs.getString("urgency"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ResultatService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     public void delete(int id) {
   
    String requete = "DELETE FROM resultat WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ResultatService.class.getName()).log(Level.SEVERE, null, ex);
    
}}
public  void updateRes( resultat res) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE resultat SET action = ?, possibility = ?, doctor_note = ? WHERE id = ?");

            ps.setString(1, res.getAction());
            ps.setInt(2, res.getPossibility());
            ps.setString(3, res.getDoctor_note());
            ps.setInt(4, res.getId());
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ResultatService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }}
