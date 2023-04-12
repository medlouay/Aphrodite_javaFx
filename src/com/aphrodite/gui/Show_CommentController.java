/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.aphrodite.gui;

import com.aphrodite.entities.Comment;
import com.aphrodite.entities.Post;
import com.aphrodite.entities.SinglePost;
import static com.aphrodite.gui.ViewMoreController.loadWindow;
import com.aphrodite.services.ServiceComment;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class Show_CommentController implements Initializable {
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
         SinglePost holder = SinglePost.getInstance();
          Post p = holder.getPlace();
          Place_Name.setMaxWidth(200) ;
           Label lba = new Label(" Content ");
            Review_txt.getItems().add(lba);
            Label lbb = new Label(" Published At ");
            published_At.getItems().add(" Published At ");
                        Place_Name.setText(p.getTitle());

        List<Comment> list = sc.ShowByPost(p.getId()) ;
        if (list == null){
            System.out.println("qiufbiquzfuqfhiuqhu");
        }
        for( Comment pv : list ){
            System.out.println("My title"+ p.getTitle());

            Label lbl = new Label(pv.getContent());
            Review_txt.getItems().add(lbl);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
String strDate = dateFormat.format(pv.getpublished_at());
            Label lb2 = new Label(strDate);
            published_At.getItems().add(strDate);
    
    
    }    }    

    @FXML
    private void handleReturn(ActionEvent event) {
        closeStage();
        loadWindow(getClass().getResource("/com/aphrodite/gui/Show_post.fxml"), "Show Posts", null);
    }
          private void closeStage() {
        ((Stage) Place_Name.getScene().getWindow()).close();
    }
}
