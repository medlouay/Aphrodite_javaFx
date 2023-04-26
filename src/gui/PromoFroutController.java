/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;
import java.util.Comparator;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
//import net.glxn.qrgen.javase.QRCode;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import com.esprit.entities.Promotion;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import tools.Connexion;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import com.esprit.entities.Promotion;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PromoFroutController implements Initializable {

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
    private Button btn_qrcode;
    
 public void showPromotion() {
    try {
        Connection instance = Connexion.getInstance().getCnx();
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

    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         // TODO
        showPromotion();
         btn_qrcode.setOnAction(event -> generateQRCode());
        // btn_tri.setOnAction(this::trierPromotions);
      

         
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

    /*private void trierPromotions(ActionEvent event) {
    // Récupérer la liste des promotions du TableView
    ObservableList<Promotion> promotions = tabpromo.getItems();

    // Trier la liste en utilisant un comparateur pour le pourcentage de réduction
    promotions.sort(new Comparator<Promotion>() {
        @Override
        public int compare(Promotion p1, Promotion p2) {
            // Utiliser compareTo pour comparer les pourcentages de réduction
            // en ordre décroissant (p2 - p1)
            return p2.getPercentage().compareTo(p1.getPercentage());
        }
    });

    // Rafraîchir le TableView pour refléter les modifications
    tabpromo.refresh();
}*/




  


    
}
