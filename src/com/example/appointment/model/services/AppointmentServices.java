package com.example.appointment.model.services;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Appointment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class AppointmentServices implements IService<Appointment> {

   private Connection cnx = DatabaseHandler.getInstance().getCnx();

    
    @Override
   public void ajouter(Appointment appointment) {
    String req = "INSERT INTO appointment_request (name, lastname, birthday, gender, phonenumber, email, new_old, appointmentprocedure, appointmentdate, appointmentime, type, lien, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try {
        PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, appointment.getName());
        st.setString(2, appointment.getLastname());
        st.setObject(3, appointment.getBirthday());
        st.setString(4, appointment.getGender());
        st.setString(5, appointment.getPhonenumber());
        st.setString(6, appointment.getEmail());
        st.setString(7, appointment.getNew_old());
        st.setString(8, appointment.getAppointmentprocedure());
        st.setObject(9, appointment.getAppointmentdate());
        st.setObject(10, appointment.getAppointmentime());
        st.setString(11, appointment.getType());
        st.setString(12, appointment.getLien());
        st.setString(13, "pending");
        
        st.executeUpdate();
        System.out.println("Appointment added!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


       @Override
public void modifier(Appointment t) {
    String req = "UPDATE appointment SET "
                + "name='" + t.getName() + "', "
                + "lastname='" + t.getLastname() + "', "
                + "birthday='" + t.getBirthday() + "', "
                + "gender='" + t.getGender() + "', "
                + "phonenumber='" + t.getPhonenumber() + "', "
                + "email='" + t.getEmail() + "', "
                + "new_old='" + t.getNew_old()+ "', "
                + "procedure='" + t.getAppointmentprocedure() + "', "
                + "appointmentdate='" + t.getAppointmentdate() + "', "
                + "appointmenttime='" + java.sql.Time.valueOf(t.getAppointmentime()) + "', "
                + "type='" + t.getType() + "', "
                + "lien='" + t.getLien() + "', "
                + "status='" + t.getStatus() + "' "
                + "WHERE id=" + t.getId();

    try {
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Appointment modified!");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
}


    @Override
    public void supprimer(Appointment t) {
        String req = "DELETE FROM appointment_request WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1,  t.getId());
            pst.executeUpdate(req);
            System.out.println("Appointment supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
public List<Appointment> afficher() {
    List<Appointment> list = new ArrayList<>();

    String req = "SELECT * FROM appointment_request";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setName(rs.getString("name"));
            appointment.setLastname(rs.getString("lastname"));
            appointment.setBirthday(rs.getDate("birthday").toLocalDate());
            appointment.setGender(rs.getString("gender"));
            appointment.setPhonenumber(rs.getString("phonenumber"));
            appointment.setEmail(rs.getString("email"));
            appointment.setNew_old(rs.getString("new_old"));
            appointment.setAppointmentprocedure(rs.getString("appointmentprocedure"));
            appointment.setAppointmentdate(rs.getDate("appointmentdate").toLocalDate());
            appointment.setAppointmentime(rs.getTime("appointmentime").toLocalTime());
            appointment.setType(rs.getString("type"));
            appointment.setLien(rs.getString("lien"));
            appointment.setStatus(rs.getString("status"));
            list.add(appointment);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
}

