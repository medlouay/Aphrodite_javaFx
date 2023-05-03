/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.UI.Admin;
//triiiiiiiiiiiiiiiiiii
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


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
import javafx.scene.control.ComboBox;

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
import javafx.scene.control.ButtonType;
import java.time.format.DateTimeFormatter;


import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Promotion;
import com.example.appointment.model.services.ServicePromotion;
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

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        private ComboBox<?> promotion_comboBox;

    @FXML
    private DatePicker datedebutTF;
    @FXML
    private DatePicker datefinTF;
    @FXML
    private TextField pourcentageTF;
    
    @FXML
    private TableView<Promotion> tabpromo;
    @FXML
    private TableColumn<Promotion, Date> nomcell;
    @FXML
    private TableColumn<Promotion, Date> prixcell;

    @FXML
    private TableColumn<Promotion, Integer> desccell;
    ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
            public int seletId;
@FXML
    private Button trie;
    @FXML
    private TableColumn<Promotion, Integer> idpromo;


    public void showPromotion() {

        try {
            //     Connexion cnx = Connexion.getInstance().getCnx();
            Connection instance = DatabaseHandler.getInstance().getCnx();

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
/*
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
    */
    
    
    @FXML
private void btn_SupprP(ActionEvent event) throws SQLException {
    promotionList = tabpromo.getSelectionModel().getSelectedItems();
    Connection instance = DatabaseHandler.getInstance().getCnx();
    int id = promotionList.get(0).getId();
    System.out.println(id);

    // Supprimer les enregistrements dans la table `produit` qui ont une référence à la ligne dans la table `promotion`
    String deleteProduitQuery = "DELETE FROM produit WHERE promo_id = ?";
    PreparedStatement deleteProduitStmt = instance.prepareStatement(deleteProduitQuery);
    deleteProduitStmt.setInt(1, id);
    deleteProduitStmt.executeUpdate();

    // Supprimer la ligne dans la table `promotion`
    String deletePromotionQuery = "DELETE FROM promotion WHERE id = ?";
    PreparedStatement deletePromotionStmt = instance.prepareStatement(deletePromotionQuery);
    deletePromotionStmt.setInt(1, id);
    deletePromotionStmt.executeUpdate();

    showPromotion();
}

    public void addPromo(){
        System.out.println(datedebutTF.getValue());
    }
    
    /*
    @FXML
private void btn_SupprP(ActionEvent event) throws SQLException {
// Vérifier si un élément est sélectionné dans la table
if (tabpromo.getSelectionModel().isEmpty()) {
Alert alert = new Alert(Alert.AlertType.ERROR);
alert.setTitle("Erreur");
alert.setHeaderText("Aucune promotion sélectionnée");
alert.setContentText("Veuillez sélectionner une promotion dans la table");
alert.showAndWait();
return;
}

// Récupérer l'ID de la promotion sélectionnée
Promotion promotion = tabpromo.getSelectionModel().getSelectedItem();
int id = promotion.getId();
System.out.println(id);

// Afficher une confirmation avant de supprimer la promotion
Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText("Supprimer la promotion");
alert.setContentText("Êtes-vous sûr de vouloir supprimer la promotion sélectionnée ?");
Optional<ButtonType> result = alert.showAndWait();
if (result.isPresent() && result.get() == ButtonType.OK) {
    // Supprimer la promotion de la base de données
    Connection instance = Connexion.getInstance().getCnx();
    String query = "DELETE FROM promotion WHERE id = ?";
    PreparedStatement preparedStmt = instance.prepareStatement(query);
    preparedStmt.setInt(1, id);
    preparedStmt.executeUpdate();
    
    // Afficher un message de succès
    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
    alert2.setTitle("Succès");
    alert2.setHeaderText("Promotion supprimée");
    alert2.setContentText("La promotion a été supprimée avec succès");
    alert2.showAndWait();

    // Mettre à jour la table
    showPromotion();
}

}*/
   
/*
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
   */
    
      public void PromoList() {
        List<String> promotionlist = new ArrayList<>();
        ServicePromotion servicePromotion = new ServicePromotion();
        for (Promotion data : servicePromotion.afficher()) {
            promotionlist.add(data.getId() + "-" + data.getPercentage() + "%");
            ObservableList listData = FXCollections.observableArrayList(promotionlist);
            promotion_comboBox.setItems(listData);
        }
    }
    
    
    @FXML
private void AjoutPromotion(ActionEvent event) throws ParseException {
Promotion t;
ServicePromotion ps = new ServicePromotion();
// Vérifier que la date de début est supérieure ou égale à aujourd'hui
LocalDate today = LocalDate.now();
if (datedebutTF.getValue() == null || datedebutTF.getValue().isBefore(today)) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Erreur de saisie !");
    alert.setContentText("La date de début doit être supérieure ou égale à aujourd'hui");
    alert.show();
    return;
}

// Vérifier que la date de fin est supérieure à la date de début
if (datefinTF.getValue() == null || datefinTF.getValue().isBefore(datedebutTF.getValue())) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Erreur de saisie !");
    alert.setContentText("La date de fin doit être supérieure à la date de début");
    alert.show();
    return;
}

// Vérifier que le pourcentage est un entier
if (!pourcentageTF.getText().matches("\\d+")) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Erreur de saisie !");
    alert.setContentText("Le pourcentage doit être un entier");
    alert.show();
    return;
}

int pourcentage = Integer.parseInt(pourcentageTF.getText());
t = new Promotion(Date.from(datedebutTF.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(datefinTF.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), pourcentage);
ps.ajouter(t);

Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Succes");
alert.setContentText("Promotion ajoutée");
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
//
     @FXML
    private void btn_modifier(ActionEvent event) {
         String value=(String) promotion_comboBox.getValue();
        int idPromo=Integer.parseInt((String) value.subSequence(0,value.indexOf("-")));
        
        Promotion promotion=new Promotion(seletId,filterWords( datefinTF.getText()), Integer.parseInt(datefinTF.getText()), Double.parseDouble(pourcentageTF.getText()),"test", DescTF.getText(), imageName, "test", "test", idPromo);
        System.out.println(promotion);
        ServicePromotion serviceProd=new ServicePromotion();
        serviceProd.modifier(promotion);
        try {
            refreshP( event);
        } catch (IOException ex) {
            Logger.getLogger(ProduitadminController.class.getName()).log(Level.SEVERE, null, ex);
        }

   
}*/

/*
@FXML
private void btn_modifier(ActionEvent event) {
String value = (String) promotion_comboBox.getValue();
int idPromo = Integer.parseInt((String) value.subSequence(0, value.indexOf("-")));
LocalDate dateDebut = datedebutTF.getValue();
LocalDate dateFin = datefinTF.getValue();
Promotion promotion = new Promotion(
seletId,
java.sql.Date.valueOf(dateDebut),
java.sql.Date.valueOf(dateFin), (int) Double.parseDouble(pourcentageTF.getText()));
System.out.println(promotion);
ServicePromotion serviceProd = new ServicePromotion();
serviceProd.modifier(promotion);
try {
refreshP(event);
} catch (IOException ex) {
Logger.getLogger(ProduitadminController.class.getName()).log(Level.SEVERE, null, ex);
}
}*/
@FXML
private void btn_modifier(ActionEvent event) {
  int id_promo = tabpromo.getSelectionModel().getSelectedItem().getId();

int pourcentage = Integer.parseInt(pourcentageTF.getText());

Promotion promotion = new Promotion(id_promo,Date.from(datedebutTF.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(datefinTF.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()), pourcentage);

System.out.println(promotion);
ServicePromotion servicePromo = new ServicePromotion();
servicePromo.modifier(promotion);
try {
refreshP(event);
} catch (IOException ex) {
Logger.getLogger(ProduitadminController.class.getName()).log(Level.SEVERE, null, ex);
}
}

    public void selectionData(){
        Promotion promotion= tabpromo.getSelectionModel().getSelectedItem();
        int num=tabpromo.getSelectionModel().getSelectedIndex();
        seletId=promotion.getId();
        System.out.println(seletId);

        Instant debutInstant = promotion.getStartDate().toInstant();
        LocalDate debut = debutInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        Instant finInstant = promotion.getEndDate().toInstant();
        LocalDate fin = finInstant.atZone(ZoneId.systemDefault()).toLocalDate();

        datedebutTF.setValue(debut);
        datefinTF.setValue(fin);
        pourcentageTF.setText(String.valueOf(promotion.getPercentage()));
    }
    // Autres méthodes...


    
    

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
