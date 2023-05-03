package com.example.UI.Admin;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.Tags;
import com.example.appointment.model.services.ServicePost;
import com.example.appointment.model.services.ServiceTags;
import static com.example.appointment.util.Utils.loadWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;

public class AddPostController {
    ServicePost sp = new ServicePost();
    private Connection cnx = DatabaseHandler.getInstance().getCnx();
ServiceTags st = new ServiceTags();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private TextField titleTextField;

    @FXML
    private TextField slugTextField;

    @FXML
    private JFXTextField summaryTextArea;

    private TextArea contentTextArea;

    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Button addButton;
    private Boolean isInEditMode = Boolean.FALSE;
    int id , author_id ;
        LocalDateTime publishedAt;

    private ServicePost servicePost;
    @FXML
    private WebView WebRes;
    @FXML
    private HTMLEditor htmlEdit;
    @FXML
    private ComboBox<String> PostTags;

    public void initialize() {

     loadTags();   
    

    }
  private void  loadTags(){
                list.clear();
        String req = "SELECT name FROM tag";

        PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while (result.next()) {
                list.add(result.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        PostTags.setItems(list);
    
    }
    @FXML
    private void handleAddOperation(ActionEvent event) {
        int ids ;
   java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
Date date=new Date(ts.getTime());
        String  pn =  titleTextField.getText() ;
        String cn =  slugTextField.getText() ;
        String pt =  summaryTextArea.getText() ;
        String pd =  htmlEdit.getHtmlText() ;
        String tg = PostTags.getValue();

        if (pn.isEmpty()  ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter Post title.");
            return;
        }
        if (cn.isEmpty()  ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Slug.");
            return;
        }               if ( pt.isEmpty()  ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Summary.");
            return;
        }               if (pd.isEmpty() ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Content.");
            return;
        }
                     if (tg == "tags" ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Content.");
            return;
        }


        if (isInEditMode) {
            handleEditOperation();
            

            return;
            
        }
        
        Post pv = new Post(1,pn, cn, pt, pd,ts) ;
        sp.ajouter(pv);
        ids = sp.getPostId(pv);
        Tags tagi = new Tags(ids , 1);
        st.ajouter(tagi);
                        loadWindow(getClass().getResource("Show_post.fxml"), "Show Posts", null);
                showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Post Added");
                closeStage();

    }

    private void clearForm() {
        titleTextField.setText("");
        slugTextField.setText("");
        summaryTextArea.setText("");
        contentTextArea.setText("");

    }

    public void infalteUI(Post selectedForEdit) {
        titleTextField.setText(selectedForEdit.getTitle());
        slugTextField.setText(selectedForEdit.getSlug());
        summaryTextArea.setText(selectedForEdit.getSummary());
        htmlEdit.setHtmlText(selectedForEdit.getContent());
        id = selectedForEdit.getId();
        author_id = selectedForEdit.getauthor_id();
        isInEditMode = Boolean.TRUE;
    }
    private void handleEditOperation() {
  java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
Date date=new Date(ts.getTime());
        Post post = new Post(id , 1,titleTextField.getText(), slugTextField.getText(), summaryTextArea.getText(), contentTextArea.getText(), ts);
        System.out.println(id );
        sp.modifier(post) ;
        showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Success", "Post Updated");
        closeStage();
        loadWindow(getClass().getResource("Show_post.fxml"), "Show Posts", null);


    }
    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<Button> controls, String header, String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }

       private void closeStage() {
        ((Stage) summaryTextArea.getScene().getWindow()).close();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
                closeStage();
        loadWindow(getClass().getResource("Home.fxml"), "Show Posts", null);
    }

 

    @FXML
    private void testit(ActionEvent event) {
                System.out.println(htmlEdit.getHtmlText());

              WebEngine webeng = WebRes.getEngine();
        webeng.loadContent(htmlEdit.getHtmlText(), "text/html");
    }
}
