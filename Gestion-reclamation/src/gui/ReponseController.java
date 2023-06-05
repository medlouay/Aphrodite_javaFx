    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Question;
import entities.Response;
import service.commentairecrud;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReponseController implements Initializable {

    @FXML
    private TableView<Response> tabler;
    private TreeTableColumn<Response, Integer> idrep;
    private TreeTableColumn<Response, String> contrep;
    @FXML
    private TableView<Question> tableq;
    @FXML
    private TableColumn<Question, Integer> idques;
    @FXML
    private TableColumn<Question, String> subques;
    @FXML
    private TableColumn<Question, String> contques;
     ObservableList<Response> reponse = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Response, Integer> idques1;
    @FXML
    private TableColumn<Response, String> contques1;
    @FXML
    private TextField txtid;
    @FXML
    private TextField txtcont;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private TextField idquestion;
    @FXML
    private Button blooo;
    @FXML
    private Button staaa;
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

  table1();
    }
    public void table1()
      {
         
          ObservableList<Question> question = FXCollections.observableArrayList();
          
          
          
          
                commentairecrud cc = new commentairecrud(); 
                question.addAll(cc.afficherquestion()) ; 
                tableq.setItems(question);
                
      
              
                idques.setCellValueFactory(new PropertyValueFactory<Question,Integer>("id_q"));
                subques.setCellValueFactory(new PropertyValueFactory<Question,String>("subject_q"));
                contques.setCellValueFactory(new PropertyValueFactory<Question,String>("content_q"));
                
                
                
                
                 tabler.setRowFactory( tv -> {TableRow<Response> myRow = new TableRow<>();
                myRow.setOnMouseClicked (event ->
                {
                 if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
            int myIndex =  tabler.getSelectionModel().getSelectedIndex();
           txtid.setText(String.valueOf(tabler.getItems().get(myIndex).getId_r()));
           txtcont.setText(tabler.getItems().get(myIndex).getContent_r());
                     
                          
        }
     });
        return myRow;
                   });
//    FilteredList<Question> filteredData = new FilteredList<>(question, b -> true);
//		
//		// 2. Set the filter Predicate whenever the filter changes.
//	txtsearch.textProperty().addListener((observable, oldValue, newValue) -> {
//			filteredData.setPredicate(test -> {
//				// If filter text is empty, display all persons.
//								
//				if (newValue == null || newValue.isEmpty()) {
//					return true;
//				}
//				
//				// Compare first name and last name of every person with filter text.
//				String lowerCaseFilter = newValue.toLowerCase();
//				
//				if (String.valueOf(test.getId_q()).contains(lowerCaseFilter)) {
//					return true; // Filter matches first name.
//				} else if (String.valueOf(test.getContent_q()).contains(lowerCaseFilter)) {
//					return true; // Filter matches last name.
//				} else if (String.valueOf(test.getSubject_q()).contains(lowerCaseFilter)) {
//					return true; // Filter matches last name.
//				}
//				 
//                                 
//                               
//                                
//				     else  
//				    	 return false; // Does not match.
//			});
//		});
//		
//		// 3. Wrap the FilteredList in a SortedList. 
////		SortedList<Question> sortedData = new SortedList<>(filteredData);
////		
////		// 4. Bind the SortedList comparator to the TableView comparator.
////		// 	  Otherwise, sorting the TableView would have no effect.
////		sortedData.comparatorProperty().bind(tabler.comparatorProperty());
////		
////		// 5. Add sorted (and filtered) data to the table.
////		tabler.setItems(sortedData);
//        
//        
        
    }
    @FXML
    public void clickItem(MouseEvent event)
{
    if (event.getClickCount() == 2) //Checking double click
    {
        tabler.getItems().clear();


        ArrayList<TableColumn<Response,?>> sortOrder = new ArrayList<>(tabler.getSortOrder());
           
        int s = tableq.getSelectionModel().getSelectedItem().getId_q();
        System.out.println("aaaaaaaa");
             
           
          
          
          
          
                commentairecrud cc = new commentairecrud(); 
                reponse.addAll(cc.test(s));
                tabler.setItems(reponse);
                
      
              
                idques1.setCellValueFactory(new PropertyValueFactory<Response,Integer>("id_r"));
                
                contques1.setCellValueFactory(new PropertyValueFactory<Response,String>("content_r"));
          
                
    }
          
            
    
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table1();
    }    

    @FXML
    private void add(ActionEvent event) {
        String cont = txtcont.getText();
        int id = Integer.parseInt(idquestion.getText()); 
        Response r = new Response(id, cont) ; 
        commentairecrud cc = new commentairecrud(); 
        cc.ajouterreponse(r);
        table1();
    }

    @FXML
    private void update(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText()); 
        String cont = txtcont.getText(); 
        int idq = Integer.parseInt(idquestion.getText());
        commentairecrud cc = new commentairecrud();  
        cc.Updater(id, cont,idq);
        table1();
    }

    @FXML
    private void delete(ActionEvent event) {
        int id = Integer.parseInt(txtid.getText()); 
        String cont = txtcont.getText(); 
        commentairecrud cc = new commentairecrud();  
        cc.Supprimerreponse(id);
        table1();
    }
    private Stage stage;
        private Scene scene;
    private Parent root;
    @FXML
    private void blooo(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("stat.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void staaa(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("commentaire.fxml"));
      
            stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
     private void homee(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("homeadmin.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();}
}
