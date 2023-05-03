package com.example.UI.User;

import com.example.UI.Item.ItemController;
import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.Post;
import com.example.appointment.model.entities.Produit;
import com.example.appointment.model.entities.Promotion;
import com.example.appointment.model.entities.SinglePost;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.AppointmentServices;
import com.example.appointment.model.services.Question;
import com.example.appointment.model.services.ServicePost;
import com.example.appointment.model.services.commentairecrud;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
import java.util.Comparator;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
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
    ObservableList<Question> question = FXCollections.observableArrayList();

    
     AppointmentServices service = new AppointmentServices();
    List <Appointment> list;
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
    private TextField txtid;
    @FXML
    private TextField txtsub;
    @FXML
    private TextField txtcont;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField txtsearch;
    @FXML
    private TableView<Question> table;
    @FXML
    private TableColumn<Question, Integer> colid;
    @FXML
    private TableColumn<Question, String> colsub;
    @FXML
    private TableColumn<Question, String> colcont;
    @FXML
    private Button btnreponse;
    @FXML
    private Button btnstat1;
    @FXML
    private Button pdf1;
    @FXML
    private Button trie;
    @FXML
    private Button trie1;
    @FXML
    private GridPane PostGrid;
    
    List<Post> postlist ;
    ServicePost sp = new ServicePost();
    
    
    

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
         
ObservableList<Question> question = FXCollections.observableArrayList();
ObservableList<Question> questionsSupprimees = FXCollections.observableArrayList();


 postlist = sp.afficher();
        System.out.println( User.connectdUser) ;
         loadPostData(postlist);


// Ajouter des questions à la liste


// Appeler la fonction de tri



        // TODO
       table();
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
    private void add(ActionEvent event) {
        String cont = txtcont.getText();
        int id = Integer.parseInt(txtid.getText()); 
        String sub = txtsub.getText();
        Question q = new Question(id, cont, sub) ; 
        commentairecrud cc = new commentairecrud(); 
        cc.ajouterquestion(q);
        table();
    }

    @FXML
    private void update(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText()); 
        String cont = txtcont.getText();
        String sub = txtsub.getText();
        commentairecrud cc = new commentairecrud();  
        cc.Update(id, cont, sub);
        table();
    }
    private ObservableList<Question> questionsSupprimees = FXCollections.observableArrayList();

    @FXML
    private void delete(ActionEvent event) throws SQLException {
       question=table.getSelectionModel().getSelectedItems();
          Connection instance = DatabaseHandler.getInstance().getCnx();
            int id;
            id=question.get(0).getId_q();
            System.out.println(id);
             
        
            
           String query = "delete from question WHERE id_q = ?";
      PreparedStatement preparedStmt = instance.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
      preparedStmt.execute();
      questionsSupprimees.addAll(question);
       
             table();      
    }

    @FXML
    private void update4(MouseEvent event) {
        
        
        Object selectedItem = table.getSelectionModel().getSelectedItem();
         commentairecrud cc = new commentairecrud();   
         
         int i =0;
         List Commentaire = cc.afficherquestion();
    
        
 
        System.out.println(Commentaire);
        while (i!=Commentaire.size()){
     
        
          
         if(Commentaire.get(i).toString().equals(selectedItem.toString())) {
            
             
             System.out.println(selectedItem.toString());          
             System.out.println(Commentaire.get(i).toString());            
             System.out.println(selectedItem.getClass());


              Question Ncour = (Question) Commentaire.get(i);
              txtid.setText(String.valueOf(Ncour.getId_q()));
              txtsub.setText(String.valueOf(Ncour.getSubject_q()));
              txtcont.setText(String.valueOf(Ncour.getContent_q())); 
            break;
             
            } else {
             System.out.println("eyyyyyyyyyyyyyyyyyyyyyyyyyyykh");
             
             i++;

            }
          }
    }
    private Stage stage;
        private Scene scene;
    private Parent root;
    
    @FXML
    private void stat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Stat2.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void reponseint(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("reponse.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

     @FXML
    private void extPDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, ClassNotFoundException {
        Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("C:\\3eme annee esprit\\Appointment 2\\test\\test6.pdf"));
    document.open();
    Paragraph ph1 = new Paragraph("Bienvenue à Aphrodite!");

    Paragraph ph2 = new Paragraph(".");
    PdfPTable table;
    table = new PdfPTable(6);
    table.addCell("ID PATIENT");
    table.addCell("TITRE");
    table.addCell("MESSAGE");
    table.addCell("DESCRIPTION");
   

    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aphrodite2", "root", "");
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM reclamation ORDER BY id_patient_id");
    while (rs.next()) {
        table.addCell(rs.getString("id_patient_id"));
        table.addCell(rs.getString("titre"));
        table.addCell(rs.getString("message"));
        table.addCell(rs.getString("description"));
      
    }
    document.add(table);
    document.close();
    }

    @FXML
   private void trierQuestionsParIdCroissant(ActionEvent event) {
      // Création d'un comparateur basé sur l'ID de la question
    Comparator<Question> comparator = Comparator.comparingInt(Question::getId_q);

    // Tri de la liste de questions en utilisant le comparateur
    FXCollections.sort(question, comparator);
}

    @FXML
    private void Historique(ActionEvent event) {
        table.setItems(questionsSupprimees);

    // Afficher un message ou effectuer d'autres actions pour indiquer que l'historique des questions supprimées est affiché
    System.out.println("Historique des questions supprimées affiché");
    }
    
    
    
    private void afficheQuestion(ActionEvent event) {
              
//        int id = Integer.parseInt(cid.getText()); 
//        String titre = ctitre.getText() ; 
//        ClassCrud cc = new ClassCrud(); 
//        cc.afficherCours(); 
//       // table.setItems(liste);
//        
//        
//    

  table();
    }
    public void table()
      {
         
          ObservableList<Question> question = FXCollections.observableArrayList();
          ObservableList<Question> questionsSupprimees = FXCollections.observableArrayList();

    commentairecrud cc = new commentairecrud();
    List<Question> questions = cc.afficherquestion();

    // Parcourir la liste de questions et remplacer les mots interdits par des étoiles
    for (Question q : questions) {
        String content = q.getContent_q();
        String[] badWords = {"putain", "merde", "fuck"}; // Remplacez les mots interdits par les mots que vous souhaitez
        for (String badWord : badWords) {
            content = content.replaceAll("(?i)" + badWord, "*"); // Remplacer les mots interdits par des étoiles (*)
        }
        q.setContent_q(content);
    }

    ObservableList<Question> questionList = FXCollections.observableArrayList(questions);
    table.setItems(questionList);

    question.addAll(cc.afficherquestion());
    table.setItems(question);

    colid.setCellValueFactory(new PropertyValueFactory<Question, Integer>("id_q"));
    colsub.setCellValueFactory(new PropertyValueFactory<Question, String>("subject_q"));
    colcont.setCellValueFactory(new PropertyValueFactory<Question, String>("content_q"));

    FilteredList<Question> filteredData = new FilteredList<>(question, b -> true);

    // 2. Set the filter Predicate whenever the filter changes.
    txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(q -> {
            // If filter text is empty, display all persons.
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            // Compare first name and last name of every person with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            if (String.valueOf(q.getId_q()).contains(lowerCaseFilter)) {
                return true; // Filter matches first name.
            } else if (String.valueOf(q.getContent_q()).contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else if (String.valueOf(q.getSubject_q()).contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
            } else
                return false; // Does not match.
        });
    });

    // 3. Wrap the FilteredList in a SortedList.
    SortedList<Question> sortedData = new SortedList<>(filteredData);

    // 4. Bind the SortedList comparator to the TableView comparator.
    // Otherwise, sorting the TableView would have no effect.
    sortedData.comparatorProperty().bind(table.comparatorProperty());

    // 5. Add sorted (and filtered) data to the table.
    table.setItems(sortedData);
        
        
        
    }

    @FXML
    private void statistique(ActionEvent event) {
    }

    @FXML
    private void refreshP(ActionEvent event) {
    }

    @FXML
    private void Quitterpanier(ActionEvent event) {
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
                loader.setLocation(getClass().getResource("Post.fxml"));
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

}
