package com.example.appointment;

import com.example.appointment.model.services.AppointmentServices;
import com.example.appointment.util.Alerts;
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


public class HelloController implements Initializable {
    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void page1(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("page1.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
    
     public void tableauFichePatient(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("tableau2.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
    }
     public void calendar(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent fxml = FXMLLoader.load(getClass().getResource("/com/example/calendar/Calendar.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);
        //System.out.println("loading");
     
     }

    public void tableauAppointment(javafx.event.ActionEvent actionEvent) throws IOException{

        Parent fxml = FXMLLoader.load(getClass().getResource("tableau1.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(fxml);

    //    loadView("tableau1.fxml", (Tableau1 controller) ->  {
    //        controller.setAppointmentService(new AppointmentServices());
           // controller.updateTableView();
     //   });
    }
    
    public void statistiquehandler(javafx.event.ActionEvent actionEvent) throws IOException{

        Parent fxml = FXMLLoader.load(getClass().getResource("MenuAppWithChart.fxml"));
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
}