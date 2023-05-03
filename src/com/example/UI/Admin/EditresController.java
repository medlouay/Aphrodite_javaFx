/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.UI.Admin;

import com.example.appointment.model.entities.resultat;
import com.example.appointment.model.services.ResultatService;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 
 */
public class EditresController implements Initializable {
    
resultat resultatsel =   Aphrodite.resSelected;
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
       action_text.setText(Aphrodite.resSelected.getAction());
       possibility_text.setText(String.valueOf(Aphrodite.resSelected.getPossibility()));

        note_text.setText(String.valueOf(Aphrodite.resSelected.getDoctor_note()));
       
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
           //controle de saisie
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
         ResultatService rsuService = new ResultatService();
         
   
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

        int possibility = Integer.parseInt(possibility_text.getText().toString());

   

        

//     mettre à jour l'utilisateur
        Aphrodite.resSelected.setAction(action);
        Aphrodite.resSelected.setPossibility(possibility);
        Aphrodite.resSelected.setDoctor_note(note);
       

        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir modifier cet resultat ?");
        ButtonType buttonTypeOK = new ButtonType("OK");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOK) {
            rsuService.updateRes(resultatsel);
            // Afficher un message de confirmation
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Confirmation");
            alert2.setHeaderText(null);
            alert2.setContentText("resultat modifié avec succès !");
            alert2.showAndWait();
        } else {
            // User chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("resultats.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
