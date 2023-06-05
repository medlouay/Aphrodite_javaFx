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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Service.ServiceReclamation;

import entities.Reclamation;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
/**
 * FXML Controller class
 *
 * @author dell
 */
public class ReclamationFrontController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private TextField descTF;
    @FXML
    private Button btnajoutP;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
    private TextField id_pTF;
    @FXML
    private TextField titreTF;
    @FXML
    private TextField messageTF;
    @FXML
    private ComboBox<?> user_comboBox;

    /**
     * Initializes the controller class.
     */
    
    
     public void reclamationuserList() {
        List<String> userlist = new ArrayList<>();
        //ServiceUser ServiceUser = new ServiceUser();
       // for (Reclamation data : ServiceUser.afficher()) {
            //userlist.add(data.getId() + "-" );
            ObservableList listData = FXCollections.observableArrayList(userlist);
            user_comboBox.setItems(listData); }//}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
       String value=(String) user_comboBox.getValue();
        int id_patient_id=Integer.parseInt((String) value.subSequence(0,value.indexOf("-")));
        
        Reclamation reclamation=new Reclamation( id_patient_id, titreTF.getText(), Long.parseLong(messageTF.getText()), descTF.getText());
        System.out.println(reclamation);
        ServiceReclamation serviceRec=new ServiceReclamation();
        serviceRec.ajouter(reclamation);
    
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
