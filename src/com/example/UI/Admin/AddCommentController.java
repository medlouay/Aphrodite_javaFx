package com.example.UI.Admin;

import com.example.Api.TextFilter;
import static com.example.UI.Admin.AddPostController.showMaterialDialog;
import com.example.appointment.model.entities.Comment;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.SinglePost;
import com.example.appointment.model.services.ServiceComment;

import static com.example.appointment.util.Utils.loadWindow;
import com.jfoenix.controls.JFXTextArea;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;


public class AddCommentController {
    ServiceComment sc = new ServiceComment();
    @FXML
    private JFXTextArea Comment;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

        public void initialize() {

    }
    @FXML
    private void HandleAddComment(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, ParseException {

 java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
Date date=new Date(ts.getTime());

        if (Comment.getText().isEmpty()  ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Comment.");
            return;
        }

 //   Date publishedAt = dateTime.format(formatter);
          SinglePost holder = SinglePost.getInstance();
          Post p = holder.getPlace();
       /*    SingleUser hold = SingleUser.getInstance();
          User u = hold.getUser();
          if (u == null){
          Alert("No User Has Been Found", "Please Login to Rate"); 
           closeStage();
          return;
         
          
          }*/
        String cmnts = Comment.getText() ;
                TextFilter txtf = new TextFilter();

                String filtredcmnts = txtf.GetTwi(cmnts) ;

        System.out.println( "C id" + p.getId() + "the author id " + p.getauthor_id()+ "the content" + filtredcmnts + "the date" + date);
        sc.ajouter(new Comment(p.getId(),p.getauthor_id(), filtredcmnts, ts));
        closeStage();
                                loadWindow(getClass().getResource("Home.fxml"), "Show Posts", null);

    }
        private void closeStage() {
        ((Stage) Comment.getScene().getWindow()).close();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
            closeStage();
        loadWindow(getClass().getResource("Home.fxml"), "Show Posts", null);
    }
 
}
