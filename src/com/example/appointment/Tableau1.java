package com.example.appointment;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.listeners.DataChangeListener;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.services.AppointmentServices;
import com.example.appointment.util.Utils;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTableCell;





public class Tableau1 implements Initializable {
    private AppointmentServices service;
    ObservableList<Appointment> list = FXCollections.observableArrayList();

    @FXML
    private TableView<Appointment> tableView;

    @FXML
    private TableColumn<Appointment, String> tableColumnName;
    @FXML
    private TableColumn<Appointment, String> tableColumnlastname;
    @FXML
    private TableColumn<Appointment, LocalDate> tableColumnDOB;
    @FXML
    private TableColumn<Appointment, String> tableColumnGender;
    @FXML
    private TableColumn<Appointment, String> tableColumnPhone;
    @FXML
    private TableColumn<Appointment, String> tableColumnEmail;
    @FXML
    private TableColumn<Appointment, String> tableColumnNewOld;
    @FXML
    private TableColumn<Appointment, String> tableColumnProced;
    @FXML
    private TableColumn<Appointment, LocalDate> tableColumnDate;
    @FXML
    private TableColumn<Appointment, LocalTime> tableColumnTime;
    @FXML
    private TableColumn<Appointment, String> tableColumnStatus;
    

 private Connection cnx = DatabaseHandler.getInstance().getCnx();

    public void setAppointmentService(AppointmentServices service){
        this.service = service;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        service = new AppointmentServices();
       initCol();
        loadData();
    }

        private void initCol() {
            
    tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableColumnlastname.setCellValueFactory(new PropertyValueFactory<>("last name"));
    tableColumnDOB.setCellValueFactory(new PropertyValueFactory<>("birthday"));
    tableColumnGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    tableColumnPhone.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
    tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    tableColumnNewOld.setCellValueFactory(new PropertyValueFactory<>("new_old"));
    tableColumnProced.setCellValueFactory(new PropertyValueFactory<>("appointmentprocedure"));
    tableColumnDate.setCellValueFactory(new PropertyValueFactory<>("appointmentdate"));
    tableColumnTime.setCellValueFactory(new PropertyValueFactory<>("appointmentime"));
    tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

    // Set up cell factories
    tableColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnlastname.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnGender.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnPhone.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnEmail.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnNewOld.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnProced.setCellFactory(TextFieldTableCell.forTableColumn());
    tableColumnStatus.setCellFactory(TextFieldTableCell.forTableColumn());

    // Set up listeners to handle cell edits
    tableColumnName.setOnEditCommit(event -> {
      System.out.println(event.getOldValue());
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setName(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnlastname.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setLastname(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnGender.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setGender(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnPhone.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setPhonenumber(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnEmail.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setEmail(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnNewOld.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setNew_old(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnProced.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setAppointmentprocedure(event.getNewValue());
        service.modifier(appointment);
    });

    tableColumnStatus.setOnEditCommit(event -> {
        Appointment appointment = event.getTableView().getItems().get(event.getTablePosition().getRow());
        appointment.setStatus(event.getNewValue());
        service.modifier(appointment);
    });

            
tableView.setEditable(true);
         
        
                        

        }

    
    private void loadData() {
        list.clear();
        String req = "SELECT * FROM appointment_request";
  
                    PreparedStatement pst;
        try {
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
               Appointment obj = new Appointment();
                obj.setId(rs.getInt("id"));
                System.out.println(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setLastname(rs.getString("lastname"));
                obj.setBirthday(rs.getObject("birthday", LocalDate.class));
                obj.setGender(rs.getString("gender"));
                obj.setPhonenumber(rs.getString("phonenumber"));
                obj.setEmail(rs.getString("email"));
                obj.setNew_old(rs.getString("new_old"));
                obj.setAppointmentprocedure(rs.getString("appointmentprocedure"));
                obj.setAppointmentdate(rs.getObject("appointmentdate", LocalDate.class));
                obj.setAppointmentime(rs.getObject("appointmentime", LocalTime.class));
                obj.setType(rs.getString("type"));
                obj.setLien(rs.getString("lien"));
                obj.setStatus(rs.getString("status"));


                list.add(obj);
                System.out.println(obj.toString());
            }
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
            
        tableView.setItems(list);
    }
/*
    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Appointment> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewAppointment.setItems(obsList);

    }

//    private void createDialogForm(Appointment obj, String absoluteName, Stage parentStage) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
//            Pane pane = loader.load();
//
//            // INJEÇÃO DE DEPENDÊNCIA
//            DepartmentFormController controller = loader.getController();
//            controller.setDepartment(obj);
//            controller.setDepartmentServices(new DepartmentServices());
//            controller.subscribDataChengeListener(this);
//            controller.updateFormData();
//
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Department data");
//            dialogStage.setScene(new Scene(pane));
//            dialogStage.setResizable(false);
//            dialogStage.initOwner(parentStage);
//            // TRAVA A JANELA EM FORMA DE MODAL
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.showAndWait();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
//        }
//    }

    @Override
    public void onDataChanged() {
        updateTableView();

    }*/

    @FXML
    private void handleRefresh(ActionEvent event) {
        loadData();
    }

    @FXML
    private void handlePlaceEdit(ActionEvent event) {
    }

    @FXML
    private void handlePlaceDelete(ActionEvent event) {
                //Fetch the selected row
        Appointment selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            showErrorMessage("No Place selected", "Please select a Place for deletion.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting Place");
        alert.setContentText("Are you sure want to delete " + selectedForDeletion.getName() + " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            System.out.println(selectedForDeletion.toString());
            service.supprimer(selectedForDeletion);

                list.remove(selectedForDeletion);

    }
    }
        public static void showErrorMessage(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
