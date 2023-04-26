/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.appointment.model.services;

import com.example.appointment.model.entities.FichePatient;
import com.example.appointment.DB.DatabaseHandler;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lou
 */
public class FichePatientServices implements IService<FichePatient>{
    
        Connection cnx = DatabaseHandler.getInstance().getCnx();


    @Override
public void ajouter(FichePatient t) {
    String req = "INSERT INTO fiche_patient (name, symptome, medicament, progres,rendez_vous_id ) "
            + "VALUES ('" + t.getName() + "','" + t.getSymptome() + "','" + t.getMedicament() + "','" + t.getProgres()+ "','" + 22 + "')";
    try {
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Fiche patient ajoutée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

    @Override
public void modifier(FichePatient t) {
    String req = "UPDATE fiche_patient SET name='" + t.getName() + "', symptome='" + t.getSymptome() 
                 + "', medicament='" + t.getMedicament() + "', progres='" + t.getProgres() + "' WHERE id=" + t.getId();
    try {
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("FichePatient modifiée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
public void supprimer(FichePatient fichePatient) {
    String req = "DELETE FROM fiche_patient WHERE id = " + fichePatient.getId();
    try {
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Fiche patient supprimée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
public List<FichePatient> afficher() {
    List<FichePatient> fichePatientList = new ArrayList<>();
    try {
        Statement statement = cnx.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM fiche_patient");

        while (resultSet.next()) {
            FichePatient fichePatient = new FichePatient();
            fichePatient.setId(resultSet.getInt("id"));
            fichePatient.setName(resultSet.getString("name"));
            fichePatient.setSymptome(resultSet.getString("symptome"));
            fichePatient.setMedicament(resultSet.getString("medicament"));
            fichePatient.setProgres(resultSet.getString("progres"));
            fichePatientList.add(fichePatient);
        }
    } catch (SQLException ex) {
        System.out.println("Error while fetching fiche patients from database: " + ex.getMessage());
    }

    return fichePatientList;
}
}
