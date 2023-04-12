/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.tests;

import com.esprit.entities.Produit;
import com.esprit.entities.Promotion;
import com.esprit.services.IService;
import com.esprit.services.ServiceProduit;
import com.esprit.services.ServicePromotion;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author abdel
 */
public class MainProg {
    
    public static void main(String[] args) {
        // Create instances of services
        IService<Produit> serviceProduit = new ServiceProduit();
        IService<Promotion> servicePromotion = new ServicePromotion();
        
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Test adding a product with a promotion
        Promotion promotion = null;
        try {
            promotion = new Promotion(1, sdf.parse("2023-04-01"), sdf.parse("2023-04-01"), 10);
        } catch (ParseException ex) {
            Logger.getLogger(MainProg.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  Produit produit = new Produit(1,promotion, "Laptop", 10, 2000, "Electronics", "A powerful laptop", "laptop.jpg", "37.7749° N", "122.4194° W", promotion);
        servicePromotion.ajouter(promotion);
     //   serviceProduit.ajouter(produit);

        // Test displaying all products with their promotions
        List<Produit> produits = serviceProduit.afficher();
        for (Produit p : produits) {
            System.out.println(p);
        }

        // Test modifying a product's promotion
        Promotion newPromotion = null;
        try {
            newPromotion = new Promotion(2, sdf.parse("2023-04-01"), sdf.parse("2023-04-01"), 20);
        } catch (ParseException ex) {
            Logger.getLogger(MainProg.class.getName()).log(Level.SEVERE, null, ex);
        }
        servicePromotion.ajouter(newPromotion);
       // produit.setPromotion(newPromotion);
       // serviceProduit.modifier(produit);

        // Test displaying all products with their promotions after modification
        produits = serviceProduit.afficher();
        for (Produit p : produits) {
            System.out.println(p);
        }

        // Test deleting a product
      //  serviceProduit.supprimer(produit);
        Produit pr = new Produit(17);
        
        serviceProduit.supprimer(pr);
        // Test displaying all products with their promotions after deletion
        produits = serviceProduit.afficher();
        for (Produit p : produits) {
            System.out.println(p);
        }
    }
}
