package com.example.UI.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.example.appointment.model.entities.Comment;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.SinglePost;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.ServiceComment;
import com.example.appointment.model.services.ServiceStars;
import static com.example.appointment.util.Utils.loadWindow;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Nasr
 */
public class ViewMoreUserController implements Initializable {
ServiceStars ss = new ServiceStars();
    ServiceComment sc = new ServiceComment();
    List<Comment> list ;
    @FXML
    private WebView Content;
    @FXML
    private StackPane AddComent;
    @FXML
    private Rating PageRate;
Double rati ;
    @FXML
    private StackPane Comments;
    @FXML
    private GridPane PlacesGrid;
    @FXML
    private JFXButton btnQR;
    @FXML
    private JFXButton btnRet;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SinglePost hold = SinglePost.getInstance();
          Post p = hold.getPlace();
          System.out.println(p.toString());
          int colmn = 0;
        int row = 1;
          list = sc.ShowByPost(p.getId());
       
          try {
            SinglePost holder = SinglePost.getInstance();
                holder.setPlace(p);
            Parent fxml = FXMLLoader.load(getClass().getResource("add_commentUser.fxml"));
            AddComent.getChildren().removeAll();
            AddComent.getChildren().setAll(fxml);
            for (Comment cmnts :list){
                            FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("CommentPane.fxml"));
                Pane pane = loader.load();
                CommentPaneController plc = loader.getController();
                plc.setData(cmnts);
                System.out.println("colmn  " + colmn + " row  " + row);
                if (colmn == 1) {
                    colmn = 0;
                    row++;
                }
                PlacesGrid.add(pane, colmn++, row);
                GridPane.setMargin(pane, new Insets(20));
            }
            
            } catch (IOException ex) {
            System.out.println(ex.getMessage());    }
                EventHandler<ActionEvent> scanQR = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if (p == null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a post in the table.", ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                String qrCodeContent = "http://192.168.1.68:8000/en/blog/posts/" + p.getSlug();
                try {
                    ByteArrayOutputStream baos = QRCode.from(qrCodeContent).to(ImageType.PNG).stream();
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                    BufferedImage bufferedImage = ImageIO.read(bais);
                    File tempFile = File.createTempFile("qrcode", ".png");
                    ImageIO.write(bufferedImage, "png", tempFile);
                    Desktop.getDesktop().open(tempFile);
                } catch (IOException ex) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Failed to generate QR code.", ButtonType.OK);
                    alert.showAndWait();
                    ex.printStackTrace();
                }
            }
        };


        btnQR.setOnAction(scanQR);
    }   


    
            public void infalteUI(Post selectedForEdit) {
            WebEngine webeng = Content.getEngine();
        webeng.loadContent(selectedForEdit.getContent(), "text/html");
             rati = ss.PostRate(selectedForEdit.getId());
             if (rati == 0){
             PageRate.setOpacity(0);
             }else{
             PageRate.setRating(rati);
             }
            }

    @FXML
    private void ReturnTO(ActionEvent event) {
        if (User.connectdUser.isAdmin()){
                loadWindow(getClass().getResource("/com/example/UI/Admin/Home.fxml"), "Main Admin Page", null);
closeStage();
        }else {
                        loadWindow(getClass().getResource("/com/example/UI/User/MainPage.fxml"), "Main User Page", null);
closeStage();
        }
    }
        private void closeStage() {
        ((Stage) Content.getScene().getWindow()).close();
    }
    
}
