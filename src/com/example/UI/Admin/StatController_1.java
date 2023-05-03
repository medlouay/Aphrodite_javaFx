/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.UI.Admin;


import com.example.appointment.model.services.ServiceProduit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatController_1 implements Initializable {

    @FXML
    private PieChart chart;
   @FXML
    private Button produit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ServiceProduit cc = new ServiceProduit();
         chart.setTitle("LES STATISTIQUE DES PRODUITS"); 
        chart.getData().setAll(new PieChart.Data("categorie 30 %", 30), new PieChart.Data("quantite 30%", 30),   new PieChart.Data("quantite 30%", 30),   new PieChart.Data("quantite 30%", 30),  
                new PieChart.Data("promotion 40%", 40)
        ); 
        
    }    
    
    private Stage stage;
        private Scene scene;
    private Parent root;
   
 @FXML
    private void produit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("produitadmin.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

    
    }
