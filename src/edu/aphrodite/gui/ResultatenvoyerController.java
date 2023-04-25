/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aphrodite.gui;

import edu.aphrodite.entities.diagnostic;
import edu.aphrodite.entities.resultat;
import edu.aphrodite.service.DiagnosticService;
import edu.aphrodite.service.ResultatService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benza
 */
public class ResultatenvoyerController implements Initializable {

    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn id_resultat;
    @FXML
    private TableColumn id_diagnostic;
    @FXML
    private TableColumn action;
    @FXML
    private TableColumn possibility;
    @FXML
    private TableColumn doctor_note;
    @FXML
    private TableColumn urgency;
    @FXML
    private TableView<resultat> result_table;
    @FXML
    private Label username;
    @FXML
    private Button dec;
    @FXML
    private Button profile;
    @FXML
    private Button retourner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          id_resultat.setCellValueFactory(new PropertyValueFactory<>("id"));
           id_diagnostic.setCellValueFactory(new PropertyValueFactory<>("diagnostic_id"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        possibility.setCellValueFactory(new PropertyValueFactory<>("possibility"));
        doctor_note.setCellValueFactory(new PropertyValueFactory<>("doctor_note"));
        urgency.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        
         ResultatService resService = new ResultatService();
      List<resultat> recList = resService.readAll();

        // affiche les données dans le tableau
        result_table.getItems().setAll(recList);
    }    

 
   

    @FXML
    private void modifier_resultat(ActionEvent event) throws IOException {
          resultat selectedresultat = result_table.getSelectionModel().getSelectedItem();

// Vérifier que l'utilisateur a bien sélectionné un emploi
        if (selectedresultat == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun resultat sélectionné");
            alert.setContentText("Veuillez sélectionner un resultat à supprimer.");
            alert.showAndWait();
            return;
        }

// Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de modification");
        alert.setHeaderText("Voulez-vous vraiment modifier cet resultat ?");
         if (alert.showAndWait().get() == ButtonType.OK) {
            // Supprimer l'emploi de la base de données
           ResultatService resService = new ResultatService();
           Aphrodite.resSelected = result_table.getSelectionModel().getSelectedItem();
            Parent root = FXMLLoader.load(getClass().getResource("editres.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
           
    }}

    @FXML
    private void supprimer_resultat(ActionEvent event) {
         
        resultat selectedresultat = result_table.getSelectionModel().getSelectedItem();

// Vérifier que l'utilisateur a bien sélectionné un emploi
        if (selectedresultat == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun resultat sélectionné");
            alert.setContentText("Veuillez sélectionner un resultat à supprimer.");
            alert.showAndWait();
            return;
        }

// Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet resultat ?");
//alert.setContentText("Titre : " + tablereclamation.getTitre() + "\nDescription : " + tablereclamation.getDescription());

        if (alert.showAndWait().get() == ButtonType.OK) {
            // Supprimer l'emploi de la base de données
           ResultatService resService = new ResultatService();
           resultat r = result_table.getSelectionModel().getSelectedItem();
           
//    emploiDAO.deleteReclamation(tablereclamation);
            resService.delete(r.getId());

            // Supprimer l'emploi de la liste observable et de la table
            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("resultat supprimé avec succès");
//    confirmationAlert.setContentText("Titre : " + selectedEmploi.getTitre() + "\nDescription : " + selectedEmploi.getDescription());
            confirmationAlert.showAndWait();

               id_resultat.setCellValueFactory(new PropertyValueFactory<>("id"));
           id_diagnostic.setCellValueFactory(new PropertyValueFactory<>("diagnostic_id"));
        action.setCellValueFactory(new PropertyValueFactory<>("action"));
        possibility.setCellValueFactory(new PropertyValueFactory<>("possibility"));
        doctor_note.setCellValueFactory(new PropertyValueFactory<>("doctor_note"));
        urgency.setCellValueFactory(new PropertyValueFactory<>("urgency"));
        
         
      List<resultat> recList = resService.readAll();

        // affiche les données dans le tableau
        result_table.getItems().setAll(recList);

        }
    }

    @FXML
    private void retrourres(ActionEvent event) throws IOException {
                    Parent root = FXMLLoader.load(getClass().getResource("ajoutresult.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    }
    

