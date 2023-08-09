package com.example.appointment.model.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Appointment {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String lastname;
    private LocalDate birthday;
    private String gender;
    private String phonenumber;
    private String email;
    private String new_old;
    private String appointmentprocedure;
    private LocalDate appointmentdate;
    private LocalTime appointmentime;
    private String type;
    private String lien;
    private String status;
    //relation
    private FichePatient fichePatient;

    public Appointment() {
    }

    public Appointment(String name, String lastname, LocalDate birthday, String gender, String phonenumber, String email, String new_old, String appointmentprocedure, LocalDate appointmentdate, LocalTime appointmentime, String type, String lien, String status) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.new_old = new_old;
        this.appointmentprocedure = appointmentprocedure;
        this.appointmentdate = appointmentdate;
        this.appointmentime = appointmentime;
        this.type = type;
        this.lien = lien;
        this.status = status;
    }

    public Appointment(Integer id, String name, String lastname, LocalDate birthday, String gender, String phonenumber, String email, String new_old, String appointmentprocedure, LocalDate appointmentdate, LocalTime appointmentime, String type, String lien, String status, FichePatient fichePatient) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.phonenumber = phonenumber;
        this.email = email;
        this.new_old = new_old;
        this.appointmentprocedure = appointmentprocedure;
        this.appointmentdate = appointmentdate;
        this.appointmentime = appointmentime;
        this.type = type;
        this.lien = lien;
        this.status = "pending";
        this.fichePatient = fichePatient;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNew_old() {
        return new_old;
    }

    public void setNew_old(String new_old) {
        this.new_old = new_old;
    }

    public String getAppointmentprocedure() {
        return appointmentprocedure;
    }

    public void setAppointmentprocedure(String appointmentprocedure) {
        this.appointmentprocedure = appointmentprocedure;
    }

    public LocalDate getAppointmentdate() {
        return appointmentdate;
    }

    public void setAppointmentdate(LocalDate appointmentdate) {
        this.appointmentdate = appointmentdate;
    }

    public LocalTime getAppointmentime() {
        return appointmentime;
    }

    public void setAppointmentime(LocalTime appointmentime) {
        this.appointmentime = appointmentime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FichePatient getFichePatient() {
        return fichePatient;
    }

    public void setFichePatient(FichePatient fichePatient) {
        this.fichePatient = fichePatient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", email='" + email + '\'' +
                ", new_old='" + new_old + '\'' +
                ", appointmentprocedure='" + appointmentprocedure + '\'' +
                ", appointmentdate=" + appointmentdate +
                ", appointmentime=" + appointmentime +
                ", type='" + type + '\'' +
                ", lien='" + lien + '\'' +
                ", status='" + status + '\'' +
                ", fichePatient=" + fichePatient +
                '}';
    }
}
