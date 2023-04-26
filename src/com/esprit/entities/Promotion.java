/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author oumaima
 */
import java.time.LocalDate;
import java.util.Date;

public class Promotion {

    private int id;

   // private LocalDate startDate;
       private Date startDate;


    private Date endDate;

    private int percentage;

    // Constructor
    
    public Promotion() {
    }

    public Promotion(Date startDate, Date endDate, int percentage) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    public Promotion(int id, Date startDate, Date endDate, int percentage) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.percentage = percentage;
    }

    public Promotion(int aInt) {
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", percentage=" + percentage + '}';
    }
    
    
}
