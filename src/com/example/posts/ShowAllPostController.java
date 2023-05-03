package com.example.posts;


import com.aphrodite.utils.DataSource;
import com.example.Services.ServicePost;
import static com.example.appointment.util.Utils.loadWindow;
import com.example.entities.Post;
import com.example.entities.SinglePost;
import com.example.utils.Util;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ShowAllPostController {
    ServicePost sp = new ServicePost();
    private Connection cnx = (Connection) DataSource.getInstance().getCnx();
    ObservableList<Post> list = FXCollections.observableArrayList();
    
    private Pane rootAnchorPane;


    @FXML
    private TableView<Post> tableView;





    @FXML
    private TableColumn<Post, String> titleColumn;

    @FXML
    private TableColumn<Post, String> slugColumn;

    @FXML
    private TableColumn<Post, String> summaryColumn;

    @FXML
    private TableColumn<Post, String> contentColumn;

    @FXML
    private TableColumn<Post, String> author_idCol;
    @FXML
    private JFXTextField Search;
    @FXML
    private Pane contentPane;
    @FXML
    private StackPane rootPane;



    public void initialize() {
initCol();
        loadData();

    }

    private void initCol(){

        author_idCol.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        slugColumn.setCellValueFactory(new PropertyValueFactory<>("slug"));
        summaryColumn.setCellValueFactory(new PropertyValueFactory<>("summary"));
        contentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));


    }

private void loadData() {
    list.clear();
    String req = "SELECT post.*, user.full_name FROM post INNER JOIN user ON post.author_id = user.id";
    
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet result = pst.executeQuery();
        
     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        while (result.next()) {
           // LocalDateTime publishedAt = LocalDateTime.parse(result.getString("published_at"), formatter);
            Post p = new Post(result.getInt("id"), result.getInt("author_id"), result.getString("title"), result.getString("slug"), result.getString("summary"), result.getString("content"),  result.getString("full_name"));
            list.add(p);
        }
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }


        tableView.setItems(list);
                      // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Post> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		Search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Place -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Place.getTitle().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (Place.getSlug().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} else if (Place.getAuthor_name().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Post> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    }

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }
        @FXML
    void goToAddPostScreen(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posts/add_post.fxml"));
                Parent parent = loader.load();


                Stage stage = new Stage(StageStyle.DECORATED);
                stage.setTitle("Edit Member");
                stage.setScene(new Scene(parent));
                stage.show();
                closeStage();
            } catch (IOException ex) {
                System.out.println(  ex.getMessage());
            }
    }

    @FXML
    void handleComment(ActionEvent event) {
               Post selectedFor = tableView.getSelectionModel().getSelectedItem();
        if (selectedFor == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Post selected");
            alert.setContentText("Please select a Post for Delete.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
            return;
        }
        try {
            SinglePost holder = SinglePost.getInstance();
                holder.setPlace(selectedFor);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posts/add_comment.fxml"));
            Parent parent = loader.load();


            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Member");
            stage.setScene(new Scene(parent));
            stage.show();
            closeStage();
            

        } catch (IOException ex) {
            System.out.println(  ex.getMessage());
        }
    }

    @FXML
    void handleDelete(ActionEvent event) {
        Post selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Post selected");
            alert.setContentText("Please select a Post for Delete.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Place");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getTitle() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (((Optional<?>) answer).get() == ButtonType.OK) {
            sp.supprimer(selectedForDeletion);

            list.remove(selectedForDeletion);

        }
    }

    @FXML
    void handleEdit(ActionEvent event) {
        Post selectedForEdit = tableView.getSelectionModel().getSelectedItem();
        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Post selected");
            alert.setContentText("Please select a Post for edit.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posts/add_post.fxml"));
            Parent parent = loader.load();

            AddPostController controller = (AddPostController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Edit Post");
            stage.setScene(new Scene(parent));
            stage.show();
            closeStage();

            stage.setOnHiding((e) -> {
                handleRefresh(new ActionEvent());
            });

        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    @FXML
    void handleViewmore(ActionEvent event) {
        Post selectedForEdit = tableView.getSelectionModel().getSelectedItem();

        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Post selected");
            alert.setContentText("Please select the Post you want to see.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            return;
        }
        try {
            SinglePost holder = SinglePost.getInstance();
                holder.setPlace(selectedForEdit);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/posts/ViewMoreUser.fxml"));
            Parent parent = loader.load();

            ViewMoreUserController controller = (ViewMoreUserController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View Post");
            stage.setScene(new Scene(parent, 811,833 ));
            stage.show();
            closeStage();

        } catch (IOException ex) {
            System.out.println(  ex.getMessage());
        }
    }
      private void closeStage() {
        getStage().close();
    }

    @FXML
    private void handleViewComment(ActionEvent event) {
                Post selectedForEdit = tableView.getSelectionModel().getSelectedItem();

        if (selectedForEdit == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Post selected");
            alert.setContentText("Please select the Post you want to see.");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            return;
        }
                SinglePost holder = SinglePost.getInstance();
                holder.setPlace(selectedForEdit);
                closeStage();
                loadWindow(getClass().getResource("/com/example/posts/Show_Comment.fxml"), "Show Post Comments", null);

    }
    private Stage getStage() {
        return (Stage) tableView.getScene().getWindow();
    }
    @FXML
    private void PrintTable(ActionEvent event) throws SQLException, FileNotFoundException, DocumentException, Exception {
                        List<List> printData = new ArrayList<>();
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("C:\\pdf\\Post.pdf"));

    document.open();
    Paragraph ph1 = new Paragraph("Bienvenue à Aphrodite!");

    Paragraph ph2 = new Paragraph(".");
    PdfPTable table;
    table = new PdfPTable(6);
    table.addCell("title");
    table.addCell("slug");
    table.addCell("summary");
    table.addCell("content");
    table.addCell("published_at");


   // Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/aphrodite1", "root", "");
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT post.*, user.full_name FROM post INNER JOIN user ON post.author_id = user.id");
    while (rs.next()) {
        table.addCell(rs.getString("title"));
        table.addCell(rs.getString("full_name"));
        table.addCell(rs.getString("slug"));
        table.addCell(rs.getString("summary"));
        table.addCell(rs.getString("content"));
        table.addCell(rs.getString("published_at"));

    }
    document.add(table);
    document.close();
    
       System.out.println("pfd done ");
                System.out.println("mailing done");
}


}

