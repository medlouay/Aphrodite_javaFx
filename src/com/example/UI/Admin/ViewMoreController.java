
    package com.example.UI.Admin;

import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.SinglePost;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

    public class ViewMoreController {

        @FXML
        private TextArea contentTextArea;

        @FXML
        private JFXTextField summaryTextArea;

        @FXML
        private TextField slugTextField;

        @FXML
        private TextField titleTextField;
    @FXML
    private TextField publishedCol;
    @FXML
    private JFXButton AddComment;
    @FXML
    private JFXButton SComment;
    @FXML
    private WebView WebRes;

        @FXML
        void HandleShowComments(ActionEvent event) {

        }
        public void infalteUI(Post selectedForEdit) {
            titleTextField.setText(selectedForEdit.getTitle());
            slugTextField.setText(selectedForEdit.getSlug());
            summaryTextArea.setText(selectedForEdit.getSummary());
            contentTextArea.setText(selectedForEdit.getContent());
           //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

         ///   publishedCol.setText(selectedForEdit.getpublished_at().toString());
            WebEngine webeng = WebRes.getEngine();
        webeng.loadContent(selectedForEdit.getContent(), "text/html");
                    EventHandler<ActionEvent> Grate = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                SinglePost holder = SinglePost.getInstance();
                holder.setPlace(selectedForEdit);
                loadWindow(getClass().getResource("add_comment.fxml"), "Add New Place", null);

            }
        };
        EventHandler<ActionEvent> SReviws = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                SinglePost holder = SinglePost.getInstance();
                holder.setPlace(selectedForEdit);
                loadWindow(getClass().getResource("Show_Comment.fxml"), "Add New Place", null);

            }
        };
        AddComment.setOnAction(Grate);
        SComment.setOnAction(SReviws);
        }
  
        public static Object loadWindow(URL loc, String title, Stage parentStage) {
        Object controller = null;
        try {
            FXMLLoader loader = new FXMLLoader(loc);
            Parent parent = loader.load();
            controller = loader.getController();
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent ));
            stage.show();
        } catch (IOException ex) {
            ex.getMessage();
        }
        return controller;
    }

    @FXML
    private void handleAddComments(ActionEvent event) {
        
    }

    @FXML
    private void handleReturn(ActionEvent event) {
                        closeStage();
        loadWindow(getClass().getResource("Home.fxml"), "Show Posts", null);
    }
          private void closeStage() {
        ((Stage) summaryTextArea.getScene().getWindow()).close();
    }
    }


