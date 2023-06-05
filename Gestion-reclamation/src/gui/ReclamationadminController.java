/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import java.util.ResourceBundle;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import entities.Reclamation;

import Service.ServiceReclamation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import tools.Connexion;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class ReclamationadminController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private Button btn_SupprP;
    @FXML
    private Button pdf;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
    @FXML
    private Button Historique;
    @FXML
    private TableView<Reclamation> tabRec;
    @FXML
    private TextField serch;
    @FXML
    private TableColumn<Reclamation, Integer> col_idp;
    @FXML
    private TableColumn<Reclamation, String> col_titre;
    @FXML
    private TableColumn<Reclamation, Long> col_message;
    @FXML
    private TableColumn<Reclamation, String> col_desc;
    ObservableList<Reclamation> reclamationList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
    public void showReclamation() {

        try {
            //     Connexion cnx = Connexion.getInstance().getCnx();
            Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM reclamation";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Reclamation reclamations;

            while (rst.next()) {

//rst.getInt(1),
//(String nom, int quantite, double prix, String categorie, String description, String image, String lat, String lon, int promoId) 
                reclamations = new Reclamation(rst.getInt("id_patient_id") , rst.getString("titre"), rst.getLong("message"), rst.getString("description"));
                reclamationList.add(reclamations);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }

        
        col_idp.setCellValueFactory(new PropertyValueFactory<>("id_patient_id"));
        col_titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        col_message.setCellValueFactory(new PropertyValueFactory<>("message"));
        col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        tabRec.setItems(reclamationList);
    }
 @FXML
    private void search(KeyEvent event) {
         FilteredList<Reclamation> filteredList;
        filteredList = new FilteredList<>(reclamationList, (e -> true));
        serch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(predicateUser -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (String.valueOf(predicateUser.getId_patient_id()).contains(searchKey)) {
                    return true;
                } else if (predicateUser.getTitre().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateUser.getMessage()).contains(searchKey)) {
                    return true;
                } else if (predicateUser.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                
                }
                return false;
            });
        });
        SortedList<Reclamation> sortList = new SortedList<>(filteredList);
        sortList.comparatorProperty().bind(tabRec.comparatorProperty());
        tabRec.setItems(sortList);
    }
    
   
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showReclamation();
    }    

    @FXML
    private void SuppProd(ActionEvent event)throws SQLException {
        reclamationList=tabRec.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=reclamationList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from reclamation WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);
    }

    @FXML
    private void extPDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, ClassNotFoundException {
        Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\dell\\Downloads\\aphrodite\\impression pdf\\test1.pdf"));
    document.open();
    Paragraph ph1 = new Paragraph("Bienvenue à Aphrodite!");

    Paragraph ph2 = new Paragraph(".");
    PdfPTable table;
    table = new PdfPTable(6);
    table.addCell("ID PATIENT");
    table.addCell("TITRE");
    table.addCell("MESSAGE");
    table.addCell("DESCRIPTION");
   

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aphrodite", "root", "");
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM reclamation ORDER BY id_patient_id");
    while (rs.next()) {
        table.addCell(rs.getString("id_patient_id"));
        table.addCell(rs.getString("titre"));
        table.addCell(rs.getString("message"));
        table.addCell(rs.getString("description"));
      
    }
    document.add(table);
    document.close();
    }

    @FXML
    private void refreshP(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reclamationadmin.fxml"));
                     filsP.getChildren().setAll(pane);
    }

    @FXML
    private void Quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void homee(ActionEvent event) {
    }

    @FXML
    private void historique(ActionEvent event) {
    }
       

   
    
}
