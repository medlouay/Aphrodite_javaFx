/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.entities;

/**
 *
 * @author benza
 */
public class resultat {
   private int id;
     private int diagnostic_id ;
    private String action ;
    private int possibility;
    private String doctor_note;
   private String urgency ;

    public resultat(int diagnostic_id, String action, int possibility, String doctor_note) {
        this.diagnostic_id = diagnostic_id;
        this.action = action;
        this.possibility = possibility;
        this.doctor_note = doctor_note;
    }

    public resultat(int id, int diagnostic_id, String action, int possibility, String doctor_note, String urgency) {
        this.id = id;
        this.diagnostic_id = diagnostic_id;
        this.action = action;
        this.possibility = possibility;
        this.doctor_note = doctor_note;
        this.urgency = urgency;
    }

    public resultat(String action, int possibility, String doctor_note) {
        this.action = action;
        this.possibility = possibility;
        this.doctor_note = doctor_note;
    }
    
  
   

    public resultat() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDiagnostic_id() {
        return diagnostic_id;
    }

    public void setDiagnostic_id(int diagnostic_id) {
        this.diagnostic_id = diagnostic_id;
    }

    public int getPossibility() {
        return possibility;
    }

    public void setPossibility(int possibility) {
        this.possibility = possibility;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDoctor_note() {
        return doctor_note;
    }

    public void setDoctor_note(String doctor_note) {
        this.doctor_note = doctor_note;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

   
    
    
}

