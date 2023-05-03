
package com.example.UI.User;

import com.example.Api.TextFilter;
import static com.example.UI.Admin.AddPostController.showMaterialDialog;
import com.example.appointment.model.entities.Comment;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.SinglePost;
import com.example.appointment.model.entities.Stars;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.ServiceComment;
import com.example.appointment.model.services.ServiceStars;

import com.jfoenix.controls.JFXTextArea;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimeZone;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.controlsfx.control.Rating;
import org.json.simple.parser.ParseException;


public class Add_commentUserController {
    ServiceComment sc = new ServiceComment();
    ServiceStars ss = new ServiceStars() ;
    
    @FXML
    private JFXTextArea Comment;
    @FXML
    private StackPane rootPane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private Rating rateMe;

        public void initialize() {

    }
    @FXML
    private void HandleAddComment(ActionEvent event) throws MalformedURLException, UnsupportedEncodingException, ParseException, Exception {

 java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
Date date=new Date(ts.getTime());
double art = rateMe.getRating();

        if (Comment.getText().isEmpty()  ) {
            showMaterialDialog(rootPane, mainContainer, new ArrayList<>(), "Insufficient Data", "Please enter the Comment.");
            return;
        }

 //   Date publishedAt = dateTime.format(formatter);
          SinglePost holder = SinglePost.getInstance();
          Post p = holder.getPlace();
          ss.ajouter(new Stars(p.getId(),1,art));
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
        Comment comma = new Comment(p.getId(),p.getauthor_id(), filtredcmnts, ts);
        sc.ajouter(comma);
MailB(p , comma);
    }
    

private void MailB(Post p , Comment c) throws Exception {
        try {
            String to = "abbes.achraf@esprit.tn";
            String subject = " here is the list of the posts ";
            System.out.println(User.connectdUser.getFullName());
            String body = "BONJOUR MR/MME " + User.connectdUser.getFullName() + " votre Commentaire : " + c.getContent() +" a ete publier avec success  sur la post "+p.getTitle()  ;
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("abbes.achraf@esprit.tn", "47188577e");
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(to));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(User.connectdUser.getEmail()));
                message.setSubject(subject);
                message.setText(body);
                System.out.println(body);
                Transport.send(message);

                System.out.println("registration sucedffly done ");
            } catch (MessagingException e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvocationTargetException) {
                Throwable targetException = e.getCause().getCause();
                targetException.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }
    }
        private void closeStage() {
        ((Stage) Comment.getScene().getWindow()).close();
    }

 
}
