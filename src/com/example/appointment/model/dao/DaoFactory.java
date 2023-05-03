package com.example.appointment.model.dao;

import com.example.appointment.DB.DB;
import com.example.appointment.model.dao.impl.AppointmentDaoJDBC;
import com.example.appointment.model.dao.impl.FicheDaoJDBC;

public class DaoFactory {
    public static AppointmentDao createAppointmentDao(){
        return new AppointmentDaoJDBC(DB.getConnection());
    }

    public static FicheDao createFicheDao(){

        return new FicheDaoJDBC(DB.getConnection());
    }
}
