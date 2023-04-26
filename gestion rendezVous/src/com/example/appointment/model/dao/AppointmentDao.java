package com.example.appointment.model.dao;

import com.example.appointment.model.entities.Appointment;

import java.util.List;

public interface AppointmentDao {
    void insert(Appointment obj);
    void update(Appointment obj);
    void deleteById(Integer id);
    Appointment findById(Integer id);
    List<Appointment> findAll();

}
