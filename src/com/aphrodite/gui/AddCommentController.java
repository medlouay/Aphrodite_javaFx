package com.aphrodite.gui;

import com.aphrodite.entities.Comment;
import com.aphrodite.entities.Post;
import com.aphrodite.entities.SinglePost;
import static com.aphrodite.gui.AddPostController.showMaterialDialog;
import static com.aphrodite.gui.ViewMoreController.loadWindow;
import com.aphrodite.services.ServiceComment;
import com.jfoenix.controls.JFXTextArea;
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


public class AddCommentController {
    ServiceComment sc = new ServiceComment();
    @FXML
    private JFXTextArea Comment;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;

        public void initialize() {
 SinglePost holder = SinglePost.getInstance();
          Post p = holder.getPlace();
            System.out.println(p.toString());
             java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
Date date=new Date(ts.getTime());
            System.out.println("date  is "+ date+"  time stamp is  "  + ts  );
    }
    @FXML
    private void HandleAddComment(ActionEvent event) {

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
        System.out.println( "C id" + p.getId() + "the author id " + p.getauthor_id()+ "the content" + cmnts + "the date" + date);
        sc.ajouter(new Comment(p.getId(),p.getauthor_id(), cmnts, ts));
        closeStage();
                                loadWindow(getClass().getResource("/com/aphrodite/gui/Show_post.fxml"), "Show Posts", null);

    }
        private void closeStage() {
        ((Stage) Comment.getScene().getWindow()).close();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
            closeStage();
        loadWindow(getClass().getResource("/com/aphrodite/gui/Show_post.fxml"), "Show Posts", null);
    }
 
}
