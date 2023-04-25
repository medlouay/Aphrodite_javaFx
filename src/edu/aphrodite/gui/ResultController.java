/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.aphrodite.gui;

import edu.aphrodite.entities.resultat;
import edu.aphrodite.service.ResultatService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benza
 */
public class ResultController implements Initializable {

    @FXML
    private TextField action_text;
    @FXML
    private TextField possibility_text;
    @FXML
    private TextField note_text;

    @FXML
    private Button enregistrer;
    @FXML
    private Button retourner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveresult(ActionEvent event) {
             if (action_text.getText().isEmpty() || possibility_text.getText().isEmpty() || note_text.getText().isEmpty() 
                ) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
             String regex = "^[a-zA-Z]+$";
    Pattern pattern = Pattern.compile(regex);
    String action = action_text.getText();
    if (!pattern.matcher(action).matches()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Saisie invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir uniquement des lettres (majuscules ou minuscules) !");
        alert.showAndWait();
        return;
    }
         edu.aphrodite.service.ResultatService rsuService = new edu.aphrodite.service.ResultatService();
        
    Pattern patternn = Pattern.compile(regex);
    String note = note_text.getText();
    if (!patternn.matcher(action).matches()) {
        // Afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Saisie invalide");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez saisir uniquement des lettres (majuscules ou minuscules) !");
        alert.showAndWait();
        return;
    }
         
        
         int possibility = Integer.parseInt(possibility_text.getText());
   
         
    int diagnostic_id = Aphrodite.id_diagselect;
        resultat res = new resultat(diagnostic_id,action, possibility, note);
        ResultatService ResService = new ResultatService();
        ResService.insertRes(res);
        action_text.clear();
        possibility_text.clear();
         note_text.clear();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("resultat ajouté avec succès");
        alert.showAndWait();
         String adressemail = "benzarti.aziz@hotmail.com";
         String sujet = "resultat";
         String motif = "urgence";
         SendEmailController.sendEmail(adressemail,sujet,motif);
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Succees");
            confirmationAlert.setHeaderText("Mail envoyé avec succès");
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Ajoutresult.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
