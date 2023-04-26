package com.aphrodite.gui;

import com.aphrodite.entities.Post;
import com.aphrodite.entities.SinglePost;
import static com.aphrodite.gui.ViewMoreController.loadWindow;
import com.aphrodite.services.ServicePost;
import com.aphrodite.utils.DataSource;
import com.jfoenix.controls.JFXTextField;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.Pane;

public class ShowAllPostController {
    ServicePost sp = new ServicePost();
    private Connection cnx = (Connection) DataSource.getInstance().getCnx();
    ObservableList<Post> list = FXCollections.observableArrayList();
    
    @FXML
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
            
        PreparedStatement pst;
        try {
            pst = (PreparedStatement) cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
LocalDateTime publishedAt = LocalDateTime.parse(result.getString("published_at"), formatter);
                Post p = new Post(result.getInt("id"), result.getInt("author_id"), result.getString("title"), result.getString("slug"), result.getString("summary"), result.getString("content"),result.getTimestamp("published_at"),result.getString("full_name"));
               list.add(p);

            }
        }  catch (SQLException e) {
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aphrodite/gui/add_post.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aphrodite/gui/add_comment.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aphrodite/gui/add_post.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/aphrodite/gui/ViewMore.fxml"));
            Parent parent = loader.load();

            ViewMoreController controller = (ViewMoreController) loader.getController();
            controller.infalteUI(selectedForEdit);

            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View Post");
            stage.setScene(new Scene(parent));
            stage.show();
            closeStage();

        } catch (IOException ex) {
            System.out.println(  ex.getMessage());
        }
    }
      private void closeStage() {
        ((Stage) rootAnchorPane.getScene().getWindow()).close();
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
                loadWindow(getClass().getResource("/com/aphrodite/gui/Show_Comment.fxml"), "Show Post Comments", null);

    }

}

