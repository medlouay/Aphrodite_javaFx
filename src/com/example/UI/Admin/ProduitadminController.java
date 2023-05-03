/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.example.UI.Admin;
//staaaaaaaaaatt lekhraaaa 

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.util.ResourceBundle;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Document;

import java.io.FileOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.appointment.DB.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;









import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.example.appointment.model.entities.Produit;
import com.example.appointment.model.entities.Promotion;
import com.example.appointment.model.services.ServiceProduit;
import com.example.appointment.model.services.ServicePromotion;





/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitadminController implements Initializable {

    private AnchorPane filsP;
    @FXML
    private TextField PrixTF;
    @FXML
    private Button pdf;
    @FXML
    private Button btnquitterpn;
    @FXML
    private TableView<Produit> tabProd;
    @FXML
    private TableColumn<Produit, String> col_nomp;
    @FXML
    private TableColumn<Produit, String> col_phop;
    @FXML
    private TableColumn<Produit, String> col_des;
    @FXML
    private TableColumn<Produit, String> col_dispop;
    @FXML
    private TableColumn<Produit, String> col_prixp;
    @FXML
    private TextField nomTF;
    @FXML
    private TextField DescTF;
    @FXML
    private TextField serch;    
    @FXML
    private ComboBox<?> promo_comboBox;
    @FXML
    private TextField quantite;
    ObservableList<Produit> produitList = FXCollections.observableArrayList();
    
     @FXML
    private TextField txtNumUser;
    
    
    @FXML
    private TableColumn<Produit, String> col_promo;

    @FXML
    private ImageView imgProduitInput;
    private File selectedImageFile;
    private String imageName = null ;
    @FXML
    private Button modif;
    @FXML
    private Button btnajoutP;
    @FXML
    private Button btn_SupprP;
    public int seletId;
    @FXML
    private Button mail;
    
    @FXML
        private Button btnstat;
@FXML
    private Button triee;
    @FXML
    private AnchorPane side_ankerpane;
    @FXML
    private Pane inner_pane;
    @FXML
    private Pane most_inner_pane;
    @FXML
    private JFXButton btn_workbench;
    @FXML
    private JFXButton btnrefresh;
    @FXML
    private JFXTextField txt_serach;
    @FXML
    private StackPane contentArea;
    @FXML
    private Pane inner_pane1;


   @FXML
    void ajouterImage() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imgProduitInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imgProduitInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "uploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

        }
    }
    
    public void showProduit() {

        try {
            produitList = FXCollections.observableArrayList();
            //     Connexion cnx = Connexion.getInstance().getCnx();
            Connection instance = DatabaseHandler.getInstance().getCnx();

            String query = "SELECT * FROM produit";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Produit produits;

            while (rst.next()) {

//rst.getInt(1),
//(String nom, int quantite, double prix, String categorie, String description, String image, String lat, String lon, int promoId) 
                produits = new Produit(rst.getInt("id"),rst.getString("nom") , rst.getInt("quantite"), rst.getDouble("prix"), rst.getString("categorie"), rst.getString("description"),rst.getString("image"),rst.getString("lat"),rst.getString("lon"),rst.getInt("promo_id"));
                produitList.add(produits);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error on Building Data");
        }

        
        col_nomp.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_phop.setCellValueFactory(new PropertyValueFactory<>("image"));
        col_des.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_dispop.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        col_promo.setCellValueFactory(new PropertyValueFactory<>("promoId"));
        col_prixp.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tabProd.setItems(produitList);
    }

    public void produiPromoList() {
        List<String> promolist = new ArrayList<>();
        ServicePromotion servicePromotion = new ServicePromotion();
        for (Promotion data : servicePromotion.afficher()) {
            promolist.add(data.getId() + "-" + data.getPercentage() + "%");
            ObservableList listData = FXCollections.observableArrayList(promolist);
            promo_comboBox.setItems(listData);
        }
    }
    @FXML
    public void search() {
        FilteredList<Produit> filteredList;
        filteredList = new FilteredList<>(produitList, (e -> true));
        serch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(predicateUser -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (String.valueOf(predicateUser.getId()).contains(searchKey)) {
                    return true;
                } else if (predicateUser.getNom().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUser.getImage().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateUser.getDescription().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (String.valueOf(predicateUser.getPromoId()).contains(searchKey)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Produit> sortList = new SortedList<>(filteredList);
        sortList.comparatorProperty().bind(tabProd.comparatorProperty());
        tabProd.setItems(sortList);
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        produiPromoList();    
        showProduit();
    }

    @FXML
    public void selectionData(){
    Produit prod= tabProd.getSelectionModel().getSelectedItem();
    int num=tabProd.getSelectionModel().getSelectedIndex();
    seletId=prod.getId();
                System.out.println(seletId);
//                nomTF.getText()), Integer.parseInt(quantite.getText()), Double.parseDouble(PrixTF.getText()),"test", DescTF.getText(), imageName, "test", "test", idPromo
                        nomTF.setText(prod.getNom());
                        PrixTF.setText(String.valueOf(prod.getPrix()));
                        quantite.setText(String.valueOf(prod.getQuantite()));
                        DescTF.setText(prod.getDescription());

}
    @FXML
    private void SuppProd(ActionEvent event) throws SQLException {
       produitList=tabProd.getSelectionModel().getSelectedItems();
          Connection instance = DatabaseHandler.getInstance().getCnx();
            int id;
            id=produitList.get(0).getId();
            System.out.println(seletId);
             
        
            
           String query = "delete from produit WHERE id = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, seletId);

      // execute the preparedstatement
      preparedStmt.execute();
      showProduit();
       
      /*  try {     
            refreshP(event);
        } catch (IOException ex) {
            Logger.getLogger(ProduitadminController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
      
         }
         
    

   /*
    @FXML
    public void AjoutProduit() {
        String value=(String) promo_comboBox.getValue();
        int idPromo=Integer.parseInt((String) value.subSequence(0,value.indexOf("-")));
        System.out.println(Integer.parseInt(quantite.getText()));
        Produit produit=new Produit(filterWords( nomTF.getText()), Integer.parseInt(quantite.getText()), Double.parseDouble(PrixTF.getText()),"test", DescTF.getText(), imageName, "test", "test", idPromo);
        System.out.println(produit);
        ServiceProduit serviceProd=new ServiceProduit();
        serviceProd.ajouter(produit);
        
  send_SMS();

    }
    
    */
    
    //chatt
    
    @FXML
public void AjoutProduit() {
String value = (String) promo_comboBox.getValue();
int idPromo = Integer.parseInt((String) value.subSequence(0, value.indexOf("-")));
// Vérification de la quantité
if (!quantite.getText().matches("\\d+")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("La quantité doit être un nombre entier.");
    alert.showAndWait();
    return;
}

// Vérification du nom
if (!nomTF.getText().matches("[a-zA-Z]+")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le nom doit être composé de lettres uniquement.");
    alert.showAndWait();
    return;
}
//cerif description
if (!DescTF.getText().matches("[a-zA-Z]+")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("La description doit être composé de lettres uniquement.");
    alert.showAndWait();
    return;
}

// Vérification du prix
if (!PrixTF.getText().matches("\\d+(\\.\\d+)?")) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Le prix doit être un nombre décimal.");
    alert.showAndWait();
    return;
}

Produit produit = new Produit(filterWords(nomTF.getText()), Integer.parseInt(quantite.getText()),
        Double.parseDouble(PrixTF.getText()), "test", DescTF.getText(), imageName, "test", "test", idPromo);
System.out.println(produit);
ServiceProduit serviceProd = new ServiceProduit();
serviceProd.ajouter(produit);
showProduit();
send_SMS();
}
    
     
    void send_SMS (){
        // Initialisation de la bibliothèque Twilio avec les informations de votre compte
        String ACCOUNT_SID = "AC099ccaf4a3fa33e014b1c3f5364e8b24";
        String AUTH_TOKEN = "b974ab8620d8f62d2f2061fafa299494";
             
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            String recipientNumber = "+216" + txtNumUser.getText();
            String message = "Bonjour Mr ,\n"
                    + "Nous sommes ravis de vous informer qu'une promotion a été ajouté sur un produit.\n "
                    + "Veuillez contactez l'administration pour plus de details.\n "
                    + "Merci de votre fidélité et à bientôt chez Aphrodite.\n"
                    + "Cordialement,\n"
                    + "Aphrodite";
                
            Message twilioMessage = Message.creator(
                new PhoneNumber(recipientNumber),
                new PhoneNumber("+16073604456"),message).create();
                
            System.out.println("SMS envoyé : " + twilioMessage.getSid());
            
     }
    

@FXML
private void extPDF() throws Exception {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("c:\\3eme annee esprit\\Appointment 2\\pdf\\liste_produits20.pdf"));

    document.open();
    Paragraph ph1 = new Paragraph("Bienvenue à Aphrodite!");

    Paragraph ph2 = new Paragraph(".");
    PdfPTable table;
    table = new PdfPTable(6);
    table.addCell("Nom");
    table.addCell("Photo");
    table.addCell("Description");
    table.addCell("Quantite");
    table.addCell("Promo");
    table.addCell("Prix");

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aphrodite2", "root", "");
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM produit ORDER BY prix");
    while (rs.next()) {
        table.addCell(rs.getString("nom"));
        table.addCell(rs.getString("image"));
        table.addCell(rs.getString("description"));
        table.addCell(rs.getString("quantite"));
        table.addCell(rs.getString("promo_id"));
        table.addCell(rs.getString("prix"));
    }
    document.add(table);
    document.close();
}
/*
@FXML
private void extPDF() throws Exception {
    MailB();
    
}*/
    
       private void Recuperer(){
        tabProd.setOnMouseClicked(e->{
       
         Produit prod = tabProd.getItems().get(tabProd.getSelectionModel().getSelectedIndex());
           
            PrixTF.setText(Double.toString(prod.getPrix()));
         
          
               
                
    }
  );
 }

    private void refreshP(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/produitadmin.fxml"));
                     filsP.getChildren().setAll(pane);
    }



    @FXML
    private void btn_modifier(ActionEvent event) {
         String value=(String) promo_comboBox.getValue();
        int idPromo=Integer.parseInt((String) value.subSequence(0,value.indexOf("-")));
        System.out.println(Integer.parseInt(quantite.getText()));
        Produit produit=new Produit(seletId,filterWords( nomTF.getText()), Integer.parseInt(quantite.getText()), Double.parseDouble(PrixTF.getText()),"test", DescTF.getText(), imageName, "test", "test", idPromo);
        System.out.println(produit);
        ServiceProduit serviceProd=new ServiceProduit();
        serviceProd.modifier(produit);
        showProduit();
      /*  try {
            refreshP( event);
        } catch (IOException ex) {
            Logger.getLogger(ProduitadminController.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    @FXML
    private void Quitter(ActionEvent event) {
    }
    //mailing
    @FXML
    private void MailB() throws Exception {
        try {
            String to = "louay.est68@gmail.com";
            String from = "oumaima.chabbar@esprit.tn";
            String subject = "Notre Promotions by Aphrodite ";
            String body = "Bonjour,\n" +
"\n" +
"Nous sommes ravis de vous informer de nos nouvelles promotions sur notre produit.\n" +
"\n" +
"En effet, nous avons récemment lancé de nouvelles offres qui pourraient vous intéresser. Que vous soyez un client régulier ou un nouveau venu, nous avons quelque chose de spécial pour vous.\n" +
"\n" +
"Nos promotions comprennent des réductions sur le prix d'achat.\n" +
"\n" +
"Nous vous invitons à consulter notre application aphrodite pour plus d'informations sur ces offres et pour profiter de ces avantages.\n" +
"\n" +
"Nous sommes convaincus que vous trouverez quelque chose qui répondra à vos besoins.\n" +
"\n" +
"Nous vous remercions pour votre confiance envers notre marque et espérons avoir l'opportunité de vous servir bientôt.\n" +
"\n" +
"Cordialement, ";
                    
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("oumaima.chabbar@esprit.tn", "201JFT2487OUMA");
                }
            });

            try {
                javax.mail.Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(to));
                message.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("louay.est68@gmail.com"));
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
    
    
    //filtre
    public static String filterWords(String text) {
    String[] filterWords = {"bbb", "java", "word3"};
    String[] data = text.split("\\s+");
    String str = "";
    for (String s : data) {
        boolean g = false;
        for (String lib : filterWords) {
            if (s.equals(lib)) {
                String t = "";
                for (int i = 0; i < s.length(); i++) t += "*";
                str += t + " ";
                g = true;
                break;
            }
        }
        if (!g) str += s + " ";
    }
    return str.trim();
}

    
    
    
    private Stage stage;
        private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private void stat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     @FXML
   private void trierQuestionsParIdCroissant(ActionEvent event) {
    Comparator<Produit> comparator = Comparator.comparingInt(Produit::getQuantite);

    FXCollections.sort(produitList, comparator);
}

    @FXML
    private void refresh(ActionEvent event) {
    }

}
