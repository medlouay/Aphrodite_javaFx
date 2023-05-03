/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.UI.Admin;

import com.example.appointment.HelloApplication;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Lou
 */
public class HomeController implements Initializable {

    @FXML
    private HBox root;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Pane most_inner_pane;
    @FXML
    private JFXButton btn_workbench;
    @FXML
    private JFXButton btn_workbench1;
    @FXML
    private JFXButton btn_workbench11;
    @FXML
    private JFXButton btn_workbench12;
    @FXML
    private JFXButton btn_workbench111;
    @FXML
    private JFXTextField txt_serach;
    @FXML
    private StackPane contentArea;
    @FXML
    private JFXButton gestBTN;
    @FXML
    private JFXButton commentBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /*private void gestionRendezVous(ActionEvent event,Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/appointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        
    }*/
     private void closeStage() {
        ((Stage) btn_workbench.getScene().getWindow()).close();
    }

    @FXML
    private void gestionRendezVous(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/appointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("RoadRevel");
            stage.setScene(scene);
            stage.show();
            closeStage();
    }

    @FXML
    private void gestionProduit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/produit.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("produit");
            stage.setScene(scene);
            stage.show();
            closeStage();
    }

    /*@FXML
    private void gestionPromotion(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/promotion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("promotion");
            stage.setScene(scene);
            stage.show();
            closeStage();
        
    }*/

    @FXML
    private void gestionPromotion(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/UI/Admin/promotion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("promotion");
            stage.setScene(scene);
            stage.show();
            closeStage();
    }

    @FXML
    private void gestionSymptome(ActionEvent event) {
    }

    @FXML
    private void gestionPosts(ActionEvent event) throws IOException {
                        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/posts/show_post.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    private void gestionComment(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/posts/Show_Comment.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

   
    

   

    
}
