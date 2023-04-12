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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
//import static GUI.AccueilController.fc;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter; 

//import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;
//import Tools.Smsapi;
//import entities.commande;
//import gui.PanierController;
import com.esprit.entities.Produit;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.esprit.services.ServiceProduit;
import tools.Connexion;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter; 
//import entities.panier;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitsController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private TableView<Produit> tabProd;
    @FXML
    private TableColumn<Produit, Integer> col_idp;
    @FXML
    private TableColumn<Produit, String> col_nomp;
    @FXML
    private TableColumn<Produit, Double> col_prixp;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
    @FXML
    private Button btnajoutqtpn;
    @FXML
    private Button btnajoutqtpn1;
    @FXML
    private TableColumn<Produit,Integer> col_promo_id;
    @FXML
    private TableColumn<Produit, Integer> col_qt;
    @FXML
    private TableColumn<Produit, String> col_categorie;
    @FXML
    private TableColumn<Produit, String> col_descrip;
    @FXML
    private TableColumn<Produit, String> col_img;
    ObservableList<Produit> produitList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Produit, String> col_lat;
    @FXML
    private TableColumn<Produit, String> col_lon;
    public void showProduits() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM produit";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Produit produits;
        
            while (rst.next()) {

//rst.getInt(1),
 produits = new Produit(rst.getInt(1),rst.getInt(2),rst.getString(3),rst.getInt(4),rst.getDouble(5),rst.getString(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10));
                produitList.add(produits);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        col_idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_promo_id.setCellValueFactory(new PropertyValueFactory<>("promo-id"));
        col_nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_qt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_prixp.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        col_descrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_img.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        

        

        tabProd.setItems(produitList);
    }
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showProduits();
         
    }    

    @FXML
    private void refreshP(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/produits.fxml"));
                     filsP.getChildren().setAll(pane);
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void Create_An_Order_Line(ActionEvent event) {
    }

   // @FXML
   // private void constpn(ActionEvent event) {
   // }

    @FXML
    private void homee(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("produits.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    @FXML
    private void constpn(ActionEvent event) {
    }
        
    }
    

