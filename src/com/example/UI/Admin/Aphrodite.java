package com.example.UI.Admin;

import com.example.appointment.model.entities.resultat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Aphrodite extends Application {
  public static int id_diagselect;
  public static resultat resSelected;
    @Override
     public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ajoutresult.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}