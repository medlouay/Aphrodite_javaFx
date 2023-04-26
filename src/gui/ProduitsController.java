
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
//import static GUI.AccueilController.fc;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter; 

//import com.itextpdf.text.DocumentException;
import java.io.File;
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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
//import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;
//import Tools.Smsapi;
//import entities.commande;
//import gui.PanierController;
import com.esprit.entities.Produit;

import com.esprit.entities.Promotion;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.esprit.services.ServiceProduit;
import com.esprit.services.ServicePromotion;
import tools.Connexion;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter; 
//import entities.panier;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class ProduitsController implements Initializable {

    @FXML
    private AnchorPane filsP;
    @FXML
    private TableView<Produit> tabProd;
    private TableColumn<Produit, Integer> col_idp;
    @FXML
    private TableColumn<Produit, String> col_nomp;
    @FXML
    private TableColumn<Produit, Double> col_prixp;
    @FXML
    private Button actualiseP;
    @FXML
    private Button btnquitterpn;
    private TableColumn<Produit,Integer> col_promo_id;
    private TableColumn<Produit, Integer> col_qt;
    private TableColumn<Produit, String> col_categorie;
    private TableColumn<Produit, String> col_descrip;
    private TableColumn<Produit, String> col_img;
    ObservableList<Produit> produitList = FXCollections.observableArrayList();
    @FXML
    private Button pdf;
    @FXML
    private TableColumn<?, ?> col_phop;
    @FXML
    private TableColumn<?, ?> col_des;
    @FXML
    private TableColumn<?, ?> col_dispop;
    @FXML
    private TableColumn<?, ?> col_promo;
    @FXML
    private TextField serch;
    @FXML
    private ImageView imgProduitInput;
    @FXML
    private Button btnstat;
  public void showProduit() {

        try {
            //     Connexion cnx = Connexion.getInstance().getCnx();
            Connection instance = Connexion.getInstance().getCnx();

            String query = "SELECT * FROM produit";
            Statement st;
            ResultSet rst;
            st = instance.createStatement();
            rst = st.executeQuery(query);
            Produit produits;

            while (rst.next()) {

//rst.getInt(1),
//(String nom, int quantite, double prix, String categorie, String description, String image, String lat, String lon, int promoId) 
                produits = new Produit(rst.getString("nom") , rst.getInt("quantite"), rst.getDouble("prix"), rst.getString("categorie"), rst.getString("description"),rst.getString("image"),rst.getString("lat"),rst.getString("lon"),rst.getInt("promo_id"));
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
       // col_promo.setCellValueFactory(new PropertyValueFactory<>("promoId"));
        col_prixp.setCellValueFactory(new PropertyValueFactory<>("prix"));
        tabProd.setItems(produitList);
    }

    //public void produiPromoList() {
      //  List<String> promolist = new ArrayList<>();
        //ServicePromotion servicePromotion = new ServicePromotion();
        //for (Promotion data : servicePromotion.afficher()) {
          //  promolist.add(data.getId() + "-" + data.getPercentage() + "%");
            //ObservableList listData = FXCollections.observableArrayList(promolist);
            //promo_comboBox.setItems(listData);
        //}
    //}
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
        // TODO
         showProduit();
         
    }    

    @FXML
    private void refreshP(ActionEvent event) throws IOException{
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/produits.fxml"));
                     filsP.getChildren().setAll(pane);
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
         System.exit(0);
    }


   // @FXML
   // private void constpn(ActionEvent event) {
   // }

    @FXML
    private void homee(ActionEvent event) throws IOException{
         Parent root = FXMLLoader.load(getClass().getResource("produits.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

    @FXML
    private void extPDF(ActionEvent event) {
    }

    @FXML
    private void statistique(ActionEvent event) {
     
    }

    // Vos autres méthodes existantes
}

    
        
    
    

