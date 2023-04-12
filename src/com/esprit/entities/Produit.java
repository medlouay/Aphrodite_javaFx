/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.entities;

/**
 *
 * @author oumaima
 */

public class Produit {
    private int id;

    private Promotion promotion;

    private String nom;

    private int quantite;

    private double prix;

    private String categorie;

    private String description;

    private String image;

    private String lat;

    private String lon;

    public Produit(int id) {
        this.id = id;
    }
    
    

    // Constructor
    public Produit(String nom, int quantite, double prix, String categorie, String description, String image, String lat, String lon) {
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }

    public Produit(int id, Promotion promotion, String nom, int quantite, double prix, String categorie, String description, String image, String lat, String lon) {
        this.id = id;
        this.promotion = promotion;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.categorie = categorie;
        this.description = description;
        this.image = image;
        this.lat = lat;
        this.lon = lon;
    }
    

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", promotion=" + promotion + ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + ", categorie=" + categorie + ", description=" + description + ", image=" + image + ", lat=" + lat + ", lon=" + lon + '}';
    }
    

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
