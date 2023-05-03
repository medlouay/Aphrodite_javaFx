package com.example.UI.User;

import com.example.Services.ServicePost;
import com.example.UI.Item.ItemController;
import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.Produit;
import com.example.appointment.model.entities.Promotion;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.AppointmentServices;
import com.example.entities.Post;
import com.example.entities.SinglePost;
import com.example.posts.Pane.PostController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javax.imageio.ImageIO;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
public class MainPageController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private BorderPane rootAnchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private ImageView imagePI;


    @FXML
    private Circle UserLogo;

    @FXML
    private JFXButton logout;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab renewTab;

    @FXML
    private GridPane PlacesGrid;


    @FXML
    private Tab bookIssueTab1;

    @FXML
    private Tab bookIssueTab2;

    @FXML
    private Tab bookIssueTab3;

    @FXML
    private Tab bookIssueTab31;

    @FXML
    private Tab bookIssueTab311;
    @FXML
    private GridPane ListAppGrid1;
    
     AppointmentServices service = new AppointmentServices();
    List <Appointment> list;
    List<Post> postlist ;
    ServicePost sp = new ServicePost();
    @FXML
    private TableView<Promotion> tabpromo;
    @FXML
    private TableColumn<Promotion, Date> nomcell;
    @FXML
    private TableColumn<Promotion, Date> prixcell;
    @FXML
    private TableColumn<Promotion, Integer> desccell;
      ObservableList<Promotion> promotionList = FXCollections.observableArrayList();
    @FXML
    private Button btn_qrcode;
    
    @FXML
    private Tab prod;
    @FXML
    private Tab promo;
    @FXML
    private TableView<Produit> tabProd;
    @FXML
    private TableColumn<?, ?> col_nomp;
    @FXML
    private TableColumn<?, ?> col_phop;
    @FXML
    private TableColumn<?, ?> col_des;
    @FXML
    private TableColumn<?, ?> col_dispop;
    @FXML
    private TableColumn<?, ?> col_prixp;
    @FXML
    private JFXButton btnstat;
    @FXML
    private JFXButton pdf;
    @FXML
    private JFXButton actualiseP;
    @FXML
    private JFXButton btnquitterpn;
    ObservableList<Produit> produitList = FXCollections.observableArrayList();
    @FXML
    private TextField serch;
    @FXML
    private GridPane PostGrid;
    
    
    

    @FXML
    void HandleLogOut(ActionEvent event) {

    }
    
    
    

    void HandleLoginOperation(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      list = service.afficher();
        loadData(list);
        showPromotion();
         btn_qrcode.setOnAction(event -> generateQRCode());
         showProduit();
         postlist = sp.afficher();
        System.out.println( User.connectdUser) ;
         loadPostData(postlist);
    }
    
    
    
    
    public void loadData( List<Appointment> list) {
      
        int colmn = 0;
        int row = 1;
       
        try {
            for (Appointment place : list) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/UI/Item/item.fxml"));
                Pane pane = loader.load();
                ItemController plc = loader.getController();
                plc.setData(place);
                System.out.println("colmn  " + colmn + " row  " + row);
                if (colmn == 1) {
                    colmn = 0;
                    row++;
                }
                ListAppGrid1.add(pane, colmn++, row);
                GridPane.setMargin(pane, new Insets(120));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
        public void loadPostData( List<Post> list) {
      
        int colmn = 0;
        int row = 1;
        try {
            for (Post place : list) {
                SinglePost holder = SinglePost.getInstance();
                holder.setPlace(place);
                System.out.println(place.toString());
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/com/example/posts/Pane/Post.fxml"));
                Pane pane = loader.load();
                PostController plc = loader.getController();
                plc.setData(place);
                System.out.println("colmn  " + colmn + " row  " + row);
                if (colmn == 1) {
                    colmn = 0;
                    row++;
                }
                PostGrid.add(pane, colmn++, row);
                GridPane.setMargin(pane, new Insets(150));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void modifChamps(MouseEvent event) {
    }

    @FXML
    private void homee(ActionEvent event) {
    }

    @FXML
   private void generateQRCode() {
    // Récupérer la promotion sélectionnée dans la table
    Promotion selectedPromotion = tabpromo.getSelectionModel().getSelectedItem();

    // Vérifier si une promotion est sélectionnée
    if (selectedPromotion == null) {
        // Afficher une alerte si aucune promotion n'est sélectionnée
        Alert alert = new Alert(Alert.AlertType.WARNING, "Veuillez sélectionner une promotion dans la table.", ButtonType.OK);
        alert.showAndWait();
        return;
    }

    // Générer le contenu du QR code
    String qrCodeContent = "Date de début: " + selectedPromotion.getStartDate() + "\n"
            + "Date de fin: " + selectedPromotion.getEndDate() + "\n"
            + "Pourcentage de réduction: " + selectedPromotion.getPercentage();

    try {
        // Générer le QR code en utilisant la bibliothèque QRGen
        ByteArrayOutputStream baos = QRCode.from(qrCodeContent).stream();
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        BufferedImage bufferedImage = ImageIO.read(bais);

        // Créer un fichier temporaire pour enregistrer l'image du QR code
        File tempFile = File.createTempFile("qrcode", ".png");
        ImageIO.write(bufferedImage, "png", tempFile);

        // Ouvrir l'image du QR code dans une fenêtre de l'explorateur de fichiers
        Desktop.getDesktop().open(tempFile);

    } catch (IOException ex) {
        // Afficher une alerte en cas d'erreur lors de la génération du QR code
        Alert alert = new Alert(Alert.AlertType.ERROR, "Erreur lors de la génération du QR code.", ButtonType.OK);
        alert.showAndWait();
        ex.printStackTrace();
    }
}
    public void showPromotion() {
    try {
        Connection instance = DatabaseHandler.getInstance().getCnx();
        String query = "SELECT * FROM promotion";
        Statement st;
        ResultSet rst;
        st = instance.createStatement();
        rst = st.executeQuery(query);
        Promotion promotions;

        while (rst.next()) {
            promotions = new Promotion(
                rst.getInt(1),  // ID de la promotion
                rst.getDate(2), // Date de début
                rst.getDate(3), // Date de fin
                rst.getInt(4)   // Pourcentage de réduction
            );
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
    
     public void showProduit() {

        try {
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

    @FXML
    private void statistique(ActionEvent event) {
    }

    @FXML
    private void extPDF(ActionEvent event) {
    }

    @FXML
    private void refreshP(ActionEvent event) {
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
    }

}
