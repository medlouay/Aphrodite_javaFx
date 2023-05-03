/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.UI.User;

import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.Promotion;
import com.example.appointment.model.entities.SinglePost;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class PostController implements Initializable {

    @FXML
    private Label Date;
    @FXML
    private Label Title;
    @FXML
    private Label Slug;
    @FXML
    private JFXButton btnMore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnMore.setVisible(false);
        btnMore.setDisable(true);    }    

    @FXML
    private void ShowMore(ActionEvent event) throws IOException {
/*        SinglePost holder = SinglePost.getInstance();
        Post p = holder.getPlace();
        SinglePost hold = SinglePost.getInstance();
                hold.setPlace(p);
                System.out.println("the place on show more is "+ p.toString() );
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posts/ViewMoreUser.fxml"));
        Parent parent = loader.load();

        ViewMoreUserController controller = (ViewMoreUserController) loader.getController();
        controller.infalteUI(p);

        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Edit Member");
        stage.setScene(new Scene(parent));
        stage.show();
    */
    }

    @FXML
    private void BtnHide(MouseEvent event) {
        btnMore.setVisible(false);
        btnMore.setDisable(true);
    }

    @FXML
    private void BtnShow(MouseEvent event) {
                btnMore.setVisible(true);
        btnMore.setDisable(false);
    }

    public void setData(Post pv) {
        Date.setText(pv.getpublished_at().toString());
        Title.setText(pv.getTitle());
        Slug.setText(pv.getSlug());
 
                        EventHandler<ActionEvent> Showmore = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    SinglePost hold = SinglePost.getInstance();
                    hold.setPlace(pv);
                    System.out.println("the place on show more is "+ pv.toString() );
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewMoreUser.fxml"));
                    Parent parent = loader.load();
                    
                    ViewMoreUserController controller = (ViewMoreUserController) loader.getController();
                    controller.infalteUI(pv);
                    
                    Stage stage = new Stage(StageStyle.DECORATED);
                    stage.setTitle("Edit Member");
                    stage.setScene(new Scene(parent , 822,785));
                    stage.show();
                    closeStage();
                } catch (IOException ex) {
                    Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
                }
    

            }
        };


        btnMore.setOnAction(Showmore);
    }

        private void closeStage() {
        ((Stage) Title.getScene().getWindow()).close();
    }
}
