package com.example.appointment.model.dao.impl;

import com.example.appointment.DB.DB;
import com.example.appointment.DB.DbException;
import com.example.appointment.model.dao.AppointmentDao;
import com.example.appointment.model.dao.FicheDao;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.FichePatient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FicheDaoJDBC implements FicheDao {
    private Connection conn;
    public FicheDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(FichePatient fichePatient) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO fiche_patient "
                            + "(name, symptome, medicament, progres, rendez_vous_id) "
                            + "VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, fichePatient.getName());
            st.setString(2, fichePatient.getSymptome());
            st.setString(3, fichePatient.getMedicament());
            st.setString(4, fichePatient.getProgres());
            st.setInt(5, fichePatient.getRendezVous().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    fichePatient.setId(id);
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
    public void update(FichePatient fichePatient) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE fiche_patient "
                            + "SET name = ?, symptome = ?, medicament = ?, progres = ?, appointment_id = ? "
                            + "WHERE id = ?");

            st.setString(1, fichePatient.getName());
            st.setString(2, fichePatient.getSymptome());
            st.setString(3, fichePatient.getMedicament());
            st.setString(4, fichePatient.getProgres());
            st.setInt(5, fichePatient.getRendezVous().getId());
            st.setInt(6, fichePatient.getId());

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
            st = conn.prepareStatement("DELETE FROM fiches_patients WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }


    public FichePatient findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM fiche_patient WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                FichePatient obj = new FichePatient();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setSymptome(rs.getString("symptome"));
                obj.setMedicament(rs.getString("medicament"));
                obj.setProgres(rs.getString("progres"));
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
    public List<FichePatient> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM fiche_patient");
            rs = st.executeQuery();

            List<FichePatient> list = new ArrayList<>();

            while (rs.next()) {
                FichePatient obj = new FichePatient();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setSymptome(rs.getString("symptome"));
                obj.setMedicament(rs.getString("medicament"));
                obj.setProgres(rs.getString("progres"));



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
