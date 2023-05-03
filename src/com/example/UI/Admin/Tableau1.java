package com.example.UI.Admin;

import com.example.appointment.DB.DatabaseHandler;
import com.example.appointment.api.MailSender;
import com.example.appointment.listeners.DataChangeListener;
import com.example.appointment.model.entities.Appointment;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.AppointmentServices;
import com.example.appointment.util.Utils;
import com.example.login.SingleUser;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;





public class Tableau1 implements Initializable {
    private AppointmentServices service;
    ObservableList<Appointment> list = FXCollections.observableArrayList();

        ObservableList<String> gender = FXCollections.observableArrayList();
        ObservableList<String> type = FXCollections.observableArrayList();

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
    @FXML
    private AnchorPane labelDOB;
    @FXML
    private JFXTextField labelName;
    @FXML
    private JFXTextField labelLastName;
    @FXML
    private JFXTimePicker labelTime;
    @FXML
    private JFXDatePicker labelDate;
    @FXML
    private JFXTextField labelPhone;
    @FXML
    private JFXTextField labelEmail;
    @FXML
    private JFXTextField labelNewOld;
    @FXML
    private JFXTextField labelLien;
    @FXML
    private JFXButton SubmitBtn;
    @FXML
    private JFXComboBox<String> Gendercombo;
    @FXML
    private JFXComboBox<String> Typecombo;
    @FXML
    private JFXDatePicker birthdayDate;
    @FXML
    private JFXTextArea procedureLabel;
    
     SingleUser hold = SingleUser.getInstance();
          User u = hold.getUser();

    public void setAppointmentService(AppointmentServices service){
        this.service = service;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        type.add("cabinet");
type.add("online");
Typecombo.setItems(type);
gender.add("Male");
gender.add("Female");
Gendercombo.setItems(gender);
        tableView.setEditable(true);
        service = new AppointmentServices();
       initCol();
        try {
            loadData();
        } catch (MessagingException ex) {
            Logger.getLogger(Tableau1.class.getName()).log(Level.SEVERE, null, ex);
        }
                System.out.println(" Gender Value Is "+Gendercombo.getValue());

    }

        private void initCol() {
            
    tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    tableColumnlastname.setCellValueFactory(new PropertyValueFactory<>("lastname"));
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

    
    private void loadData() throws MessagingException {
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
                obj.setId_user(rs.getInt("id_patient_id"));
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
    private void handleRefresh(ActionEvent event) throws MessagingException {
        loadData();
    }


    @FXML
    private void handlePlaceDelete(ActionEvent event) {
                //Fetch the selected row
        Appointment selectedForDeletion = tableView.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            showErrorMessage("No Appointment selected", "Please select a Appointment for deletion.");
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

    @FXML
    private void handleAdd(ActionEvent event) throws MessagingException {
          
        int tp ;
        String  name =  labelName.getText() ;
        String last =  labelLastName.getText() ;
        LocalTime time =  labelTime.getValue() ;
        LocalDate date =  labelDate.getValue() ;
        String phone =  labelPhone.getText() ;
        String  email =  labelEmail.getText() ;
        String newold =  labelNewOld.getText() ;
        String lien =  labelLien.getText() ;
        LocalDate dob =  birthdayDate.getValue() ;
        String procedure =  procedureLabel.getText() ;
        String gend = Gendercombo.getValue();
        String typed = Typecombo.getValue();
        String status = "pending" ;
        service.ajouter(new Appointment(name,last,dob,gend,phone,email,newold,procedure,date,time,typed,lien,status,u.getId()));
        

    }
    
    
    
    @FXML
private void extPDF() throws Exception {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream("E:\\pdf\\appointment.pdf"));

    document.open();
    Paragraph ph1 = new Paragraph("Bienvenue à Aphrodite!");

    Paragraph ph2 = new Paragraph(".");
    PdfPTable table;
    table = new PdfPTable(6);
    table.addCell("Name");
    table.addCell("Last Name");
    table.addCell("Birthday");
    table.addCell("Gender");
    table.addCell("Phone Number");
    table.addCell("Email");
    table.addCell("New/Old");
    table.addCell("Appointment Procedure");
    table.addCell("Appointment Date");
    table.addCell("Appointment Time");
    table.addCell("Status");

   // Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/aphrodite2", "root", "");
   
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM appointment_request");
    while (rs.next()) {
        table.addCell(rs.getString("name"));
        table.addCell(rs.getString("lastname"));
        table.addCell(rs.getString("birthday"));
        table.addCell(rs.getString("gender"));
        table.addCell(rs.getString("phonenumber"));
        table.addCell(rs.getString("email"));
        table.addCell(rs.getString("new_old"));
        table.addCell(rs.getString("appointmentprocedure"));
        table.addCell(rs.getString("appointmentdate"));
        table.addCell(rs.getString("appointmentime"));
        
        table.addCell(rs.getString("status"));
    }
    document.add(table);
    document.close();
    
       System.out.println("pfd done ");
       MailB();
                System.out.println("mailing done");
}


private void MailB() throws Exception {
        try {
            String to = "louay.est68@gmail.com";
            String from = "mohamedjmai811s@gmail.com";
            String subject = "Your appointment has been approveed ";
            String body = "BONJOUR MR/MME ";
                    
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("mohamedlouay.benbelgacem@esprit.tn", "28060770_Louay");
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(to));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("louay.est68@gmail.com"));
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
   

}
