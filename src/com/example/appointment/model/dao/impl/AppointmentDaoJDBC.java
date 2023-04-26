package com.example.appointment.model.dao.impl;

import com.example.appointment.DB.DB;
import com.example.appointment.DB.DbException;
import com.example.appointment.model.dao.AppointmentDao;
import com.example.appointment.model.dao.FicheDao;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.FichePatient;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoJDBC implements AppointmentDao {
    private Connection conn;
    public AppointmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Appointment obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO appointment_request " +
                            "(Name, LastName, Birthday, Gender, PhoneNumber, Email, New_Old, " +
                            "AppointmentProcedure, AppointmentDate, AppointmenTime, Type, Lien, " +
                            "Status, id_patient_id) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setDate(3, Date.valueOf(obj.getBirthday()));
            st.setString(4, obj.getGender());
            st.setString(5, obj.getPhonenumber());
            st.setString(6, obj.getEmail());
            st.setString(7, obj.getNew_old());
            st.setString(8, obj.getAppointmentprocedure());
            st.setDate(9, Date.valueOf(obj.getAppointmentdate()));
            st.setTime(10, Time.valueOf(obj.getAppointmentime()));
            st.setString(11, obj.getType());
            st.setString(12, obj.getLien());
            st.setString(13, obj.getStatus());
            st.setInt(14, obj.getFichePatient().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void update(Appointment obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE appointment_request " +
                            "SET Name = ?, LastName = ?, Birthday = ?, Gender = ?, PhoneNumber = ?, Email = ?, NewOld = ?, " +
                            "AppointmentProcedure = ?, AppointmentDate = ?, AppointmenTime = ?, Type = ?, Lien = ?, Status = ?, " +
                            "id_patient_id = ? " +
                            "WHERE Id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getLastname());
            st.setDate(3, Date.valueOf(obj.getBirthday()));
            st.setString(4, obj.getGender());
            st.setString(5, obj.getPhonenumber());
            st.setString(6, obj.getEmail());
            st.setString(7, obj.getNew_old());
            st.setString(8, obj.getAppointmentprocedure());
            st.setDate(9, Date.valueOf(obj.getAppointmentdate()));
            st.setTime(10, Time.valueOf(obj.getAppointmentime()));
            st.setString(11, obj.getType());
            st.setString(12, obj.getLien());
            st.setString(13, obj.getStatus());
            st.setInt(14, obj.getFichePatient().getId());
            st.setInt(15, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM appointment_request WHERE id = ?"
            );

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Failed to delete appointment with id: " + id);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    @Override
    public Appointment findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM appointment_request WHERE Id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Appointment obj = new Appointment();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                obj.setLastname(rs.getString("LastName"));
                obj.setBirthday(rs.getDate("Birthday").toLocalDate());
                obj.setGender(rs.getString("Gender"));
                obj.setPhonenumber(rs.getString("PhoneNumber"));
                obj.setEmail(rs.getString("Email"));
                obj.setNew_old(rs.getString("NewOld"));
                obj.setAppointmentprocedure(rs.getString("AppointmentProcedure"));
                obj.setAppointmentdate(rs.getDate("AppointmentDate").toLocalDate());
                obj.setAppointmentime(rs.getTime("AppointmenTime").toLocalTime());
                obj.setType(rs.getString("Type"));
                obj.setLien(rs.getString("Lien"));
                obj.setStatus(rs.getString("Status"));
                obj.setFichePatient(FicheDao.findById(rs.getInt("FichePatientId")));
                return obj;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
    @Override
    public List<Appointment> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM appointment_request");
            rs = st.executeQuery();

            List<Appointment> list = new ArrayList<>();

            while (rs.next()) {
                Appointment obj = new Appointment();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLastname(rs.getString("lastname"));
                obj.setBirthday(rs.getObject("birthday", LocalDate.class));
                obj.setGender(rs.getString("gender"));
                obj.setPhonenumber(rs.getString("phonenumber"));
                obj.setEmail(rs.getString("email"));
                obj.setNew_old(rs.getString("new_old"));
                obj.setAppointmentprocedure(rs.getString("appointmentprocedure"));
                obj.setAppointmentdate(rs.getObject("appointmentdate", LocalDate.class));
                obj.setAppointmentime(rs.getObject("appointmentime", LocalTime.class));
                obj.setType(rs.getString("type"));
                obj.setLien(rs.getString("lien"));
                obj.setStatus(rs.getString("status"));


                list.add(obj);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

}
