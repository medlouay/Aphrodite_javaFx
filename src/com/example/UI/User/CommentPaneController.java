package com.example.UI.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.appointment.model.entities.Comment;
import com.example.appointment.model.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class CommentPaneController implements Initializable {

    @FXML
    private Label Context;
    @FXML
    private Label Date;
    @FXML
    private Label UserName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DeletComment(MouseEvent event) {
    }

    @FXML
    private void Modify(MouseEvent event) {
    }

    public void setData(Comment cmnts) {
        UserService su = new UserService();
        String authorName = su.getUserById(cmnts.getauthor_id());
        Context.setText(cmnts.getContent());
        Date.setText(cmnts.getpublished_at().toString());
        UserName.setText(authorName);
        
    }
}
