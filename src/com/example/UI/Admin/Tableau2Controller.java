/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.UI.Admin;


import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.FichePatient;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lou
 */
public class Tableau2Controller implements Initializable {
    
     ObservableList<FichePatient> list = FXCollections.observableArrayList();

    @FXML
    private TableView<FichePatient> tableView2;
    
     @FXML
    private TableColumn<FichePatient, String> tableColumnName;
     @FXML
    private TableColumn<FichePatient, String> tableColumnSymptom;
     @FXML
    private TableColumn<FichePatient, String> tableColumnMedicament;
     @FXML
    private TableColumn<FichePatient, String> tableColumnProgres;
     
    private Connection cnx = DatabaseHandler.getInstance().getCnx();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initCol();
        loadData();
        // TODO
    }  
    private void initCol() {
            tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableColumnSymptom.setCellValueFactory(new PropertyValueFactory<>("symptome"));
            tableColumnMedicament.setCellValueFactory(new PropertyValueFactory<>("medicament"));
            tableColumnProgres.setCellValueFactory(new PropertyValueFactory<>("progres"));
        }
    
    private void loadData() {
        list.clear();
        String req = "SELECT * FROM fiche_patient";
  
                    PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
               FichePatient obj = new FichePatient();
                obj.setId(rs.getInt("id"));
                System.out.println(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setSymptome(rs.getString("symptome"));
                obj.setMedicament(rs.getString("medicament"));
                obj.setProgres(rs.getString("progres"));
                


                list.add(obj);
                System.out.println(obj.toString());
            }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
            
        tableView2.setItems(list);
    }
    
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }
    
    
}
