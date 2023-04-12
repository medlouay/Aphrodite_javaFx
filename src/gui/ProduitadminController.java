/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitadminController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private TextField PrixTF;
    @FXML
    private TextField idmodif;
    @FXML
    private Button modif;
    @FXML
    private Button suppP;
    @FXML
    private Button btnajoutP;
    @FXML
    private Button pdf;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
    @FXML
    private TableView<?> tabProd;
    @FXML
    private TableColumn<?, ?> col_idp;
    @FXML
    private TableColumn<?, ?> col_nomp;
    @FXML
    private TableColumn<?, ?> col_phop;
    @FXML
    private TableColumn<?, ?> col_des;
    @FXML
    private TableColumn<?, ?> col_dispop;
    @FXML
    private TableColumn<?, ?> col_prixp;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField PhotoTF;
    @FXML
    private TextField DescTF;
    @FXML
    private ComboBox<?> dispocombo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_modifier(ActionEvent event) {
    }

    @FXML
    private void btn_SupprP(ActionEvent event) {
    }

    @FXML
    private void AjoutProduit(ActionEvent event) {
    }

    @FXML
    private void extPDF(ActionEvent event) {
    }

    @FXML
    private void refreshP(ActionEvent event) {
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
    }

    @FXML
    private void homee(ActionEvent event) {
    }
    
}
