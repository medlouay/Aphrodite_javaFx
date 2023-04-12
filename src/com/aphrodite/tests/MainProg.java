package com.aphrodite.tests;

import com.aphrodite.gui.ShowAllPostController;
import com.aphrodite.services.ServicePost;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainProg extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aphrodite/gui/show_post.fxml"));
        AnchorPane root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
