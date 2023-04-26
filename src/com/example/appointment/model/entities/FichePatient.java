package com.example.appointment.model.entities;

import java.util.Objects;

public class FichePatient {
    private Integer id;
    private String name;
    private String symptome;
    private String medicament;
    private String progres;
    private Appointment rendezVous;

    public FichePatient() {}

    public FichePatient(Integer id, String name, String symptome, String medicament, String progres, Appointment rendezVous) {
        this.id = id;
        this.name = name;
        this.symptome = symptome;
        this.medicament = medicament;
        this.progres = progres;
        this.rendezVous = rendezVous;
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

    public String getSymptome() {
        return symptome;
    }

    public void setSymptome(String symptome) {
        this.symptome = symptome;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getProgres() {
        return progres;
    }

    public void setProgres(String progres) {
        this.progres = progres;
    }

    public Appointment getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(Appointment rendezVous) {
        this.rendezVous = rendezVous;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FichePatient)) return false;
        FichePatient that = (FichePatient) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "FichePatient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symptome='" + symptome + '\'' +
                ", medicament='" + medicament + '\'' +
                ", progres='" + progres + '\'' +
                ", rendezVous=" + rendezVous +
                '}';
    }
}
