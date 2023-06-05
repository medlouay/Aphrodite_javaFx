/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import service.commentairecrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class StatController_1 implements Initializable {

    @FXML
    private PieChart chart;
    @FXML
    private Button blog;
    @FXML
    private Button rep;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        commentairecrud cc = new commentairecrud();
         chart.setTitle("nbr QR"); 
        chart.getData().setAll(new PieChart.Data("nbrj", 50), new PieChart.Data("nbr_q", 30),  
                new PieChart.Data("commentaire", 25), new PieChart.Data("reponse", 42)
        ); 
        
    }    
    
    private Stage stage;
        private Scene scene;
    private Parent root;
    @FXML
    private void blog(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("commentaire.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reponse(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reponse.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
}
