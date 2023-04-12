/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Produit;
import com.esprit.entities.Promotion;
import com.esprit.utils.DataSource;
import com.esprit.utils.DataSource;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;     
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author oumaima
 */
public class ServiceProduit implements IService<Produit> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Produit p) {
        String req = "INSERT INTO produit (promo_id, nom, quantite, prix, categorie, description, image, lat, lon) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getPromotion().getId());
            pst.setString(2, p.getNom());
            pst.setInt(3, p.getQuantite());
            pst.setDouble(4, p.getPrix());
            pst.setString(5, p.getCategorie());
            pst.setString(6, p.getDescription());
            pst.setString(7, p.getImage());
            pst.setString(8, p.getLat());
            pst.setString(9, p.getLon());
            pst.executeUpdate();
            System.out.println("Produit ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Produit p) {
        String req = "UPDATE produit SET promo_id=?, nom=?, quantite=?, prix=?, categorie=?, description=?, image=?, lat=?, lon=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getPromotion().getId());
            pst.setString(2, p.getNom());
            pst.setInt(3, p.getQuantite());
            pst.setDouble(4, p.getPrix());
            pst.setString(5, p.getCategorie());
            pst.setString(6, p.getDescription());
            pst.setString(7, p.getImage());
            pst.setString(8, p.getLat());
            pst.setString(9, p.getLon());
            pst.setInt(10, p.getId());
            pst.executeUpdate();
            System.out.println("Produit modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Produit p) {
        String req = "DELETE FROM produit WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Produit> afficher() {
        List<Produit> list = new ArrayList<>();
        String req = "SELECT * FROM produit";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produit p = new Produit(
                        rs.getInt("id"),
                        new Promotion(rs.getInt("promo_id")),
                        rs.getString("nom"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix"),
                        rs.getString("categorie"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("lat"),
                        rs.getString("lon")
                );
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}

