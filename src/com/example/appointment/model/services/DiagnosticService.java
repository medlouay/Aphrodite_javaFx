/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.services;
import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.diagnostic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author benza
 */
public class DiagnosticService {
    
     private Connection conn;
    private String requete;

    public DiagnosticService() {
        conn = DatabaseHandler.getInstance().getCnx();
    }
    
        public List<diagnostic> readAll() {
        String requete = "SELECT diagnostic.* FROM diagnostic";
        List<diagnostic> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                diagnostic dia;
                
                dia = new diagnostic(rs.getInt("id"), rs.getInt("age"),rs.getString("overweight"), rs.getString("cigarettes"), rs.getString("recently_injured"), rs.getString("high_cholesterol"), rs.getString("hyper_tension"),rs.getString("diabetes"),Arrays.asList(rs.getString("symptoms")),rs.getDate("date"));
                list.add(dia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiagnosticService .class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public diagnostic readByage(int age) {
        diagnostic diag = null;
        requete = "SELECT diagnostic.* FROM diagnostic where age= ?";
        try (PreparedStatement stmt = conn.prepareStatement(requete)) {
            stmt.setInt(1, age);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                

    diag = new diagnostic(rs.getInt("id"), rs.getInt("age"),rs.getString("overweight"), rs.getString("cigarettes"), rs.getString("recently_injured"), rs.getString("high_cholesterol"), rs.getString("hyper_tension"),rs.getString("diabetes"),Arrays.asList(rs.getString("symptoms")),rs.getDate("date"));            }
        } catch (SQLException ex) {
            Logger.getLogger(DiagnosticService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return diag;
    }
         public void deletediag(int id) {
   
    String requete = "DELETE FROM diagnostic WHERE id = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(DiagnosticService.class.getName()).log(Level.SEVERE, null, ex);
    
}}
}
