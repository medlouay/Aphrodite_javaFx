package com.example.appointment;

import com.example.appointment.model.services.AppointmentServices;
import com.example.appointment.util.Alerts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class HelloController implements Initializable {
    @FXML
    private StackPane contentArea;
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
    private JFXButton btn_workbench1111;
    @FXML
    private JFXTextField txt_serach;
    @FXML
    private Pane inner_pane1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void page1(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("page1.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
    @FXML
     public void tableauFichePatient(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("tableau2.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }

    @FXML
    public void tableauAppointment(javafx.event.ActionEvent actionEvent) throws IOException{

        Parent fxml = FXMLLoader.load(getClass().getResource("tableau1.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    //    loadView("tableau1.fxml", (Tableau1 controller) ->  {
    //        controller.setAppointmentService(new AppointmentServices());
           // controller.updateTableView();
     //   });
    }

    private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox newVBox = loader.load();

            Scene mainScene = HelloApplication.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(newVBox.getChildren());

            T controller = loader.getController();
            initializingAction.accept(controller);

        } catch(IOException e) {
            Alerts.showAlert("IOException", "Error loader view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void BlogTable(ActionEvent event) throws IOException {
                Parent fxml = FXMLLoader.load(getClass().getResource("/com/aphrodite/gui/show_post.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
}