package com.example.UI.Admin;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

import com.example.appointment.model.entities.Comment;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.SinglePost;
import com.example.appointment.model.services.ServiceComment;
import static com.example.appointment.util.Utils.loadWindow;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class Show_CommentController implements Initializable {
    private ObservableList<Label> reviewList = FXCollections.observableArrayList();
private ObservableList<String> publishedAtList = FXCollections.observableArrayList();

    ServiceComment sc = new ServiceComment();
    @FXML
    private JFXListView<Label> Review_txt;
    @FXML
    private Label Place_Name;
    @FXML
    private JFXListView<String> published_At;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 loadData();
    }    
private void loadData(){
    SinglePost holder = SinglePost.getInstance();
    Post p = holder.getPlace();
    Place_Name.setMaxWidth(200);
    reviewList.clear();
    publishedAtList.clear();
    Label lba = new Label(" Content ");
    reviewList.add(lba);
    Label lbb = new Label(" Published At ");
    publishedAtList.add(" Published At ");
    Place_Name.setText(p.getTitle());

    List<Comment> list = sc.ShowByPost(p.getId());
    if (list == null){
        System.out.println("qiufbiquzfuqfhiuqhu");
    }
    for (Comment pv : list) {
        System.out.println("My title"+ p.getTitle());

        Label lbl = new Label(pv.getContent());
        reviewList.add(lbl);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(pv.getpublished_at());
        Label lb2 = new Label(strDate);
        publishedAtList.add(strDate);
    }
    Review_txt.setItems(reviewList);
    published_At.setItems(publishedAtList);
}

    @FXML
    private void handleReturn(ActionEvent event) {
        closeStage();
        loadWindow(getClass().getResource("Home.fxml"), "Show Posts", null);
    }
          private void closeStage() {
        ((Stage) Place_Name.getScene().getWindow()).close();
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

  @FXML
private void handleEdit(ActionEvent event) {
    Label selectedLabel = Review_txt.getSelectionModel().getSelectedItem();
    if (selectedLabel != null) {
        String selectedContent = selectedLabel.getText();
        Comment selectedComment = sc.getCommentId(selectedContent);
        if (selectedComment != null) {
            TextInputDialog dialog = new TextInputDialog(selectedComment.getContent());
            dialog.setTitle("Edit Comment");
            dialog.setHeaderText(null);
            dialog.setContentText("Please enter new content:");

            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String newContent = result.get();
                selectedComment.setContent(newContent);
                boolean success = sc.update(selectedComment);
                if (success) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Comment updated successfully.");
                    alert.showAndWait();
                    loadData();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Failed to update comment.");
                    alert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Selected comment not found.");
            alert.showAndWait();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText("Please select a comment to edit.");
        alert.showAndWait();
    }
}



@FXML
private void handleDelete(ActionEvent event) {
    String selectedCont = Review_txt.getSelectionModel().getSelectedItem().getText();
        int selectedIndex = Review_txt.getSelectionModel().getSelectedIndex();

    if (selectedIndex <0) {
        return;
    }
    Comment selectedComment = sc.getCommentId(selectedCont);
    if (selectedComment == null) {
        return;
    }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to delete this comment?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK) {
        sc.supprimer(selectedComment);
        reviewList.remove(selectedIndex);
        publishedAtList.remove(selectedIndex);

    }
}


}
