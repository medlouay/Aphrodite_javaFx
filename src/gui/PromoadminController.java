/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;
//triiiiiiiiiiiiiiiiiii
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.esprit.entities.Promotion;
import tools.Connexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
//
import com.esprit.entities.Promotion;
import com.esprit.services.ServicePromotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tools.Connexion;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PromoadminController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private Button modif;
    @FXML
    private Button suppP;
    @FXML
    private Button btnajoutP;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
   
    @FXML
    private DatePicker datedebutTF;
    @FXML
    private DatePicker datefinTF;
    @FXML
    private TextField pourcentageTF;
    
    @FXML
    private TextField txtNumUser;
    @FXML
    private TableView<Promotion> tabpromo;
    @FXML
    private TableColumn<Promotion, Integer> idcell;
    @FXML
    private TableColumn<Promotion, Date> nomcell;
    @FXML
    private TableColumn<Promotion, Date> prixcell;

    @FXML
    private TableColumn<Promotion, Integer> desccell;
    ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
    @FXML
            public int seletId;
@FXML
    private Button trie;


    public void showPromotion() {

        try {
            //     Connexion cnx = Connexion.getInstance().getCnx();
            Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM promotion";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Promotion promotions;

            while (rst.next()) {

//rst.getInt(1),
                promotions = new Promotion(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getInt(4));
                promotionList.add(promotions);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }

        //idcell.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomcell.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        prixcell.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        desccell.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        tabpromo.setItems(promotionList);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showPromotion();
    }

    @FXML
    private void btn_SupprP(ActionEvent event) throws SQLException {
          promotionList=tabpromo.getSelectionModel().getSelectedItems();
          Connection instance = Connexion.getInstance().getCnx();
            int id;
            id=promotionList.get(0).getId();
            System.out.println(id);
             
        
            
           String query = "delete from promotion WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
       
             showPromotion();      
    }
    public void addPromo(){
        System.out.println(datedebutTF.getValue());
    }

   @FXML
   private void AjoutPromotion(ActionEvent event) throws ParseException{
              Promotion t;
      ServicePromotion ps = new ServicePromotion();
        if (datedebutTF.getValue() == null) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setTitle("Erreur");
          alert.setHeaderText("Erreur de saisie !");
           alert.setContentText("Vous navez pas saisie la date de debut");
           alert.show();
       }
         else if (datefinTF.getValue() == null) {
             Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText("Erreur de saisie !");
           alert.setContentText("Vous navez pas saisie la date de fin");
           alert.show();
        }
      else if  (pourcentageTF.getText().length() == 0) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Erreur");
           alert.setHeaderText("Erreur de saisie !");
          alert.setContentText("Vous navez pas saisie le pourcentage");
          alert.show();
        }
           else {
          Date dateD=null;
          try{
              LocalDate localDate = datedebutTF.getValue();
              if(localDate != null){
                  Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                  dateD= Date.from(instant);
              }
          }catch(Exception e){
              e.printStackTrace();
              
          }
          Date dateF=null;
          try{
              LocalDate localDate = datefinTF.getValue();
              if(localDate != null){
                  Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                  dateF= Date.from(instant);
              }
          }catch(Exception e){
              e.printStackTrace();
              
          }
          
          int pourcentage = Integer.parseInt(pourcentageTF.getText());
          
   t = new Promotion(dateD, dateF,pourcentage);
   ps.ajouter(t);} 

  
         
   Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Succes");
   alert.setContentText("Promotion ajoutee");        //Smsapi.sendSMS("Commande effectuée avec succes");
  alert.show();
   
  
   }
   
   
    /*
    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "AC099ccaf4a3fa33e014b1c3f5364e8b24";
        String AUTH_TOKEN = "7afcb76f52069325c0a89a312a17e6c3";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+216" + txtNumUser.getText();
            String message = "Bonjour Mr ,\n"
                    + "Nous sommes ravis de vous informer qu'une promotion a été ajouté.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt chez Aphrodite.\n"
                    + "Cordialement,\n"
                    + "Aphrodite";
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+16073604456"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
            
     }
    /*
    public void selectionData(){
    Promotion Promotion= tabpromo.getSelectionModel().getSelectedItem();
    int num=tabpromo.getSelectionModel().getSelectedIndex();
    seletId=Promotion.getId();
                System.out.println(seletId);
//                nomTF.getText()), Integer.parseInt(quantite.getText()), Double.parseDouble(PrixTF.getText()),"test", DescTF.getText(), imageName, "test", "test", idPromo
                        datedebutTF.setValue(Promotion.getStartDate());
                        PrixTF.setText(String.valueOf(Promotion.getPrix()));
                        quantite.setText(String.valueOf(Promotionget.getQuantite()));
                        DescTF.setText(prod.getDescription());

}
*/
    
    
    

    private void refreshP(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/promoadmin.fxml"));
        filsP.getChildren().setAll(pane);
    }

    private void Quitterpanier(ActionEvent event) {
        System.exit(0);
    }

   

    @FXML
    private void modifChamps(MouseEvent event) {
    }

    @FXML
   private void trierQuestionsParIdCroissant(ActionEvent event) {
    Comparator<Promotion> comparator = Comparator.comparingInt(Promotion::getPercentage);

    FXCollections.sort(promotionList, comparator);
}

}
