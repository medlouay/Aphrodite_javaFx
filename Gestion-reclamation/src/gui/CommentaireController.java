/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Question;
import tools.Connexion;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import service.commentairecrud;
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

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CommentaireController implements Initializable {

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
    private Button btnstat;
    @FXML
    private Button btnreponse;
    @FXML
    private Button pdf;
    ObservableList<Question> question = FXCollections.observableArrayList();
    @FXML
    private Button trie;
    @FXML
    private Button trie1;
    @FXML
    private Button btnstat1;

    /**
     * Initializes the controller class.
     */
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    

ObservableList<Question> question = FXCollections.observableArrayList();
ObservableList<Question> questionsSupprimees = FXCollections.observableArrayList();


// Ajouter des questions à la liste


// Appeler la fonction de tri



        // TODO
       table();
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
          Connection instance = Connexion.getInstance().getCnx();
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
        Parent root = FXMLLoader.load(getClass().getResource("Stat.fxml"));
      
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
   private void homee(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}

     @FXML
    private void extPDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, ClassNotFoundException {
        Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\dell\\Downloads\\aphrodite\\impression pdf\\test4.pdf"));
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
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aphrodite", "root", "");
   
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

    @FXML
    private void Retour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("commentaire.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    }
    

