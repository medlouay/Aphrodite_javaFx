package com.example.appointment.model.dao;

import com.example.appointment.model.entities.FichePatient;

import java.util.List;

public interface FicheDao {
    void insert(FichePatient obj);
    void update(FichePatient obj);
    void deleteById(Integer id);

    static FichePatient findById(Integer id) {
        return null;
    }

    List<FichePatient> findAll();
}
