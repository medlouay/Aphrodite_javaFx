/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import com.esprit.entities.Promotion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Connexion;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PromoFroutController implements Initializable {

    @FXML
    private TableView<Promotion> tabpromo;
    @FXML
    private TableColumn<Promotion, Integer> idcell;
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
 promotions = new Promotion(rst.getText(),rst.getText(),rst.getText(),rst.getText());
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
         // TODO
        showPromotion();
         
    }    

    @FXML
    private void modifChamps(MouseEvent event) {
    }

    @FXML
    private void homee(ActionEvent event) {
    }
    
}
