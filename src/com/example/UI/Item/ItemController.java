/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.UI.Item;


import com.example.appointment.*;
import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.FichePatient;
import com.example.appointment.model.services.AppointmentServices;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Lou
 */
public class ItemController implements Initializable {
    
    

  
     
    private Connection cnx = DatabaseHandler.getInstance().getCnx();
    @FXML
    private Label name;
    @FXML
    private Label appointmentime;
    @FXML
    private Label appointmentdate;
    @FXML
    private Label type;
    @FXML
    private Label status;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        // TODO
    }  
    
    
   
    
    public void setData(Appointment appt) throws MalformedURLException {
        name.setText(appt.getName());
        appointmentime.setText(appt.getAppointmentime().toString());
        appointmentdate.setText(appt.getAppointmentdate().toString());
        type.setText(appt.getType());
        status.setText(appt.getStatus());
    }
    
    
}
