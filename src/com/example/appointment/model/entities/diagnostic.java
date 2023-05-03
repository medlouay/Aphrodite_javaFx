/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author benza
 */
public class diagnostic {

    private int id;
     private int age;
     private String overweight;
     private String cigarettes;
      private String recently_injured;
        private String high_cholesterol;
        private String hyper_tension;
         private String diabetes;
         private List<String> symptoms;
           private Date date;

    public diagnostic(int id, int age, String overweight, String cigarettes, String recently_injured, String high_cholesterol, String hyper_tension, String diabetes, List<String> symptoms, Date date) {
        this.id = id;
        this.age = age;
        this.overweight = overweight;
        this.cigarettes = cigarettes;
        this.recently_injured = recently_injured;
        this.high_cholesterol = high_cholesterol;
        this.hyper_tension = hyper_tension;
        this.diabetes = diabetes;
        this.symptoms = symptoms;
        this.date = date;
    }
  

  

    public diagnostic() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getOverweight() {
        return overweight;
    }

    public void setOverweight(String overweight) {
        this.overweight = overweight;
    }

    public String getCigarettes() {
        return cigarettes;
    }

    public void setCigarettes(String cigarettes) {
        this.cigarettes = cigarettes;
    }

    public String getRecently_injured() {
        return recently_injured;
    }

    public void setRecently_injured(String recently_injured) {
        this.recently_injured = recently_injured;
    }

    public String getHigh_cholesterol() {
        return high_cholesterol;
    }

    public void setHigh_cholesterol(String high_cholesterol) {
        this.high_cholesterol = high_cholesterol;
    }

    public String getHyper_tension() {
        return hyper_tension;
    }

    public void setHyper_tension(String hyper_tension) {
        this.hyper_tension = hyper_tension;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    
    
}


