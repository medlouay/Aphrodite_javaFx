/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.calendar;

import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.services.AppointmentServices;
import java.util.List;

/**
 *
 * @author Lou
 */
public class NewClass {
AppointmentServices aps = new AppointmentServices();
public void test(){
List<Appointment> applis = aps.afficher();

for (Appointment a : applis) {
    int yearq = a.getAppointmentdate().getYear();
    int monthq = a.getAppointmentdate().getMonth().getValue();
    int day = a.getAppointmentdate().getDayOfMonth();
    int hour = a.getAppointmentime().getHour();
    int minute = a.getAppointmentime().getMinute();

    System.out.println("Appointment date and time: " + yearq + "-" + monthq + "-" + day + " " + hour + ":" + minute);
}}
}
