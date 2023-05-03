/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.entities;

/**
 *
 * @author dell
 */

public class Reclamation {
    private int id;
    private int id_patient_id;
    private String titre;
    private Long message;
    private String description;

    public Reclamation(int id, int id_patient_id, String titre, Long message, String description) {
        this.id = id;
        this.id_patient_id = id_patient_id;
        this.titre = titre;
        this.message = message;
        this.description = description;
    }

    public Reclamation(String titre, Long message, String description) {
        this.titre = titre;
        this.message = message;
        this.description = description;
    }
    
     public Reclamation(int id_patient_id, String titre, Long message, String description) {
        this.id_patient_id = id_patient_id;
        this.titre = titre;
        this.message = message;
        this.description = description;
    }

    public Reclamation() {
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_patient_id() {
        return id_patient_id;
    }

    public void setId_patient_id(int id_patient_id) {
        this.id_patient_id = id_patient_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Long getMessage() {
        return message;
    }

    public void setMessage(Long message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "reclamation{" + "id=" + id + ", id_patient_id=" + id_patient_id + ", titre=" + titre + ", message=" + message + ", description=" + description + '}';
    }

    
    
    
}