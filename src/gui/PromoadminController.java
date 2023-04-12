/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.esprit.entities.Promotion;
import com.esprit.services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PromoadminController implements Initializable {

    @FXML
    private AnchorPane filsP;
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
    private Button home;
    @FXML
    private TextField datedebutTF;
    @FXML
    private TextField datefinTF;
    @FXML
    private TextField pourcentageTF;
    @FXML
    private TableView<Promotion> tabpromo;
    @FXML
    private TableColumn<Promotion,Integer> idcell;
    @FXML
    private TableColumn<Promotion, Date> nomcell;
    @FXML
    private TableColumn<Promotion, Date> prixcell;
    @FXML
    private TableColumn<Promotion, Integer> desccell;
    ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
public void showPromotion() {
              
             try {
       //     Connexion cnx = Connexion.getInstance().getCnx();
                    Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM promotion";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Promotion promotions;
        
            while (rst.next()) {

//rst.getInt(1),
 promotions = new Promotion(rst.getInt(1),rst.getDate(2),rst.getDate(3),rst.getInt(4));
                promotionList.add(promotions);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }
       
        idcell.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcell.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        prixcell.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        desccell.setCellValueFactory(new PropertyValueFactory<>("percentage"));
       
        

        

        tabpromo.setItems(promotionList);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         showPromotion();
    }    


    
    
    
    
    @FXML
    private void btn_SupprP(ActionEvent event) throws SQLException {
         promotionList=tabpromo.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=promotionList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from promotion WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showPromotion();      
    }


    private void Ajoutproduit(ActionEvent event) throws ParseException{
                Promotion t;
        ServicePromotion ps = new ServicePromotion();
         if (datedebutTF.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie la date de debut");
            alert.show();

        }
         else if (datefinTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie la date de fin");
            alert.show();
         }
        else if  (pourcentageTF.getText().length() == 0) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("Vous navez pas saisie le pourcentage");
            alert.show();
         }
            else {
            
    t = new Promotion(datedebutTF.getText(), datefinTF.getText(),pourcentageTF.getText()
 );
    ps.ajouter(t);} 
 
   
          
    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
    alert.setContentText("Promotion ajoutee");
        //Smsapi.sendSMS("Commande effectuée avec succes");

    alert.show();
    datedebutTF.setText("");
    datefinTF.setText("");
    pourcentageTF.setText("");
    
    }
    
    
    @FXML
    private void extPDF(ActionEvent event) {
    }

    @FXML
    private void refreshP(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/promoadmin.fxml"));
                     filsP.getChildren().setAll(pane);
    }

   @FXML
    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void homee(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("promoadmin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    @FXML
    private void modifChamps(MouseEvent event) {
    }

    @FXML
    private void AjoutProduit(ActionEvent event) {
    }
}

   
    

    

