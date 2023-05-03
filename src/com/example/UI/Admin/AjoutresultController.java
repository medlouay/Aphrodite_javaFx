/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.UI.Admin;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.HelloApplication;
import com.example.appointment.model.entities.diagnostic;
import com.example.appointment.model.services.DiagnosticService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.Connection;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.StageStyle;
import javafx.stage.Stage;

        
/**
 * FXML Controller class
 *
 * @author 
 */
public class AjoutresultController implements Initializable {

    @FXML
    private TableColumn id_diagnostique;
    @FXML
    private TableColumn overweight;
    @FXML
    private TableColumn cigarettes;
    @FXML
    private TableColumn recently_injured;
    @FXML
    private TableColumn high_cholesterol;
    @FXML
    private TableColumn hyper_tension;
    @FXML
    private TableColumn diabetes;
    @FXML
    private TableColumn symptoms;
    @FXML
    private TableColumn date;
    @FXML
    private Button ajouterresultat;
    @FXML
    private TableView<diagnostic> tablediagnostic;
    @FXML
    private Button resultatenvoyer;
    @FXML
    private TableColumn age;
    @FXML
    private TextField rech;
    @FXML
    private Button supprimer;
    @FXML
    private Button generer;
     private Connection conn;
    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Pane most_inner_pane;
    @FXML
    private JFXButton home;
    @FXML
    private StackPane contentArea;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_diagnostique.setCellValueFactory(new PropertyValueFactory<>("id"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
           overweight.setCellValueFactory(new PropertyValueFactory<>("overweight"));
        cigarettes.setCellValueFactory(new PropertyValueFactory<>("cigarettes"));
        recently_injured.setCellValueFactory(new PropertyValueFactory<>("recently_injured"));
        high_cholesterol.setCellValueFactory(new PropertyValueFactory<>("high_cholesterol"));
        hyper_tension.setCellValueFactory(new PropertyValueFactory<>("hyper_tension"));
        diabetes.setCellValueFactory(new PropertyValueFactory<>("diabetes"));
         symptoms.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
          date.setCellValueFactory(new PropertyValueFactory<>("date"));
         DiagnosticService DiagService = new DiagnosticService();
      List<diagnostic> recList = DiagService.readAll();

        // affiche les données dans le tableau
        tablediagnostic.getItems().setAll(recList);
    }    

    @FXML
    private void ajouterresult(ActionEvent event) throws IOException {
       
          if (tablediagnostic.getSelectionModel().getSelectedItem() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("no diagnostic selected");
    alert.setContentText("no diagnostic selected");
    alert.showAndWait();
} else if (tablediagnostic.getSelectionModel().getSelectedItem() != null){
    // Récupérer la réclamation correspondante
   
    diagnostic di = tablediagnostic.getSelectionModel().getSelectedItem();
    Aphrodite.id_diagselect = di.getId();
              
     Parent root = FXMLLoader.load(getClass().getResource("ajouterResultat.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
        
    }
    
}

    @FXML
    private void resenvoyer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("resultats.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }

    @FXML
    private void recherche(KeyEvent event) {
        DiagnosticService diagService = new DiagnosticService();
          if (event.getCode() == KeyCode.ENTER) {
             int age = Integer.parseInt(rech.getText().toString());
            diagnostic searcheddiag = diagService.readByage(age);
            if (searcheddiag != null) {
                tablediagnostic.getItems().setAll(searcheddiag);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No diagnostic found");
                alert.setHeaderText(null);
                alert.setContentText("No diagnostic found with the given age.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void supp(ActionEvent event) {
         diagnostic selecteddiag = tablediagnostic.getSelectionModel().getSelectedItem();

// Vérifier que l'utilisateur a bien sélectionné un emploi
        if (selecteddiag == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun diagnostic sélectionné");
            alert.setContentText("Veuillez sélectionner un diagnostic à supprimer.");
            alert.showAndWait();
            return;
        }

// Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet diagnostic ?");
//alert.setContentText("Titre : " + tablereclamation.getTitre() + "\nDescription : " + tablereclamation.getDescription());

        if (alert.showAndWait().get() == ButtonType.OK) {
            // Supprimer l'emploi de la base de données
           DiagnosticService diagService = new DiagnosticService();
           diagnostic r = tablediagnostic.getSelectionModel().getSelectedItem();
           
//    emploiDAO.deleteReclamation(tablereclamation);
            diagService.deletediag(r.getId());

            // Supprimer l'emploi de la liste observable et de la table
            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Diagnostic supprimé avec succès");
//    confirmationAlert.setContentText("Titre : " + selectedEmploi.getTitre() + "\nDescription : " + selectedEmploi.getDescription());
            confirmationAlert.showAndWait();

                id_diagnostique.setCellValueFactory(new PropertyValueFactory<>("id"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
           overweight.setCellValueFactory(new PropertyValueFactory<>("overweight"));
        cigarettes.setCellValueFactory(new PropertyValueFactory<>("cigarettes"));
        recently_injured.setCellValueFactory(new PropertyValueFactory<>("recently_injured"));
        high_cholesterol.setCellValueFactory(new PropertyValueFactory<>("high_cholesterol"));
        hyper_tension.setCellValueFactory(new PropertyValueFactory<>("hyper_tension"));
        diabetes.setCellValueFactory(new PropertyValueFactory<>("diabetes"));
         symptoms.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
          date.setCellValueFactory(new PropertyValueFactory<>("date"));
         DiagnosticService DiagService = new DiagnosticService();
      List<diagnostic> recList = DiagService.readAll();

        // affiche les données dans le tableau
        tablediagnostic.getItems().setAll(recList);

        }
    }

    @FXML
    private void genererpdf(ActionEvent event) {
         com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("diagnostic.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(8); 

            // Add table headers
            table.addCell("id");
            table.addCell("age");
            table.addCell("overweight");
            table.addCell("cigarettes");
            table.addCell("recently_injured");
            table.addCell("high_cholesterol");
            table.addCell("hyper_tension");
            table.addCell("diabets");
            conn = DatabaseHandler.getInstance().getCnx();
            // Add table rows from the database
            String query = "SELECT * FROM diagnostic";
            ResultSet resultSet = conn.createStatement().executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int age = resultSet.getInt("age");
                String overweight = resultSet.getString("overweight");
               String cigarettes = resultSet.getString("cigarettes");
                 String recently_injured = resultSet.getString("recently_injured");
                 String high_cholesterol = resultSet.getString("high_cholesterol");
                 String hyper_tension = resultSet.getString("hyper_tension");
                       String diabets = resultSet.getString("diabetes");
                table.addCell(Integer.toString(id));   //ism les attribut ml base 
                table.addCell(Integer.toString(age));
                table.addCell(overweight);
                table.addCell(cigarettes);
                 table.addCell(recently_injured);
                 table.addCell(high_cholesterol);
                    table.addCell(hyper_tension);
                 table.addCell(diabets);   
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Les données des diagnostic ont été exportées dans le fichier diagnostic.pdf");

            // Open the generated PDF file
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("diagnostic.pdf"));
            }
        } catch (FileNotFoundException | DocumentException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation des données des diagnostic : " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'entrée/sortie : " + e.getMessage());
        }
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/Home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("promotion");
            stage.setScene(scene);
            stage.show();
            closeStage();
    }
    
    
    private void closeStage() {
        ((Stage) home.getScene().getWindow()).close();
    }
}
