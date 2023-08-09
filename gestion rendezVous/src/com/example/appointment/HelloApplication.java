package com.example.appointment;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    private static Scene mainScene;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("appointment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
//@Override
//public void start(Stage primaryStage) throws IOException {
//    FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("appointment.fxml"));
//    Scene scene = new Scene(loader.load());
//        ScrollPane scrollpane = loader.load();
//
//        scrollpane.setFitToHeight(true);
//        scrollpane.setFitToWidth(true);
//
//        mainScene = new Scene(scrollpane);
//        primaryStage.setScene(mainScene);
//    primaryStage.setTitle("Sample JavaFX application");
//    primaryStage.setScene(scene);
//    primaryStage.show();
//}
    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) {
        User u = new User("tgesty", "tggesgty", "testddygg@test.test", "tgest", false);
        UserService service = new  UserService();
          System.out.println(service.afficher());
        launch();
//        AppointmentDao appointmentDao = DaoFactory.createAppointmentDao();
//
//
//
//        Print the list of all Appointments to verify the insertion
//        List<Appointment> appointments = appointmentDao.findAll();
//        for (Appointment a : appointments) {
//            System.out.println(a);
//        }
    }

    }
