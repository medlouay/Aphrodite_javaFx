package com.example.posts;
import com.example.appointment.model.entities.User;
import com.example.appointment.model.services.UserService;
import com.example.entities.Post;
import com.example.entities.SinglePost;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Parent;


public class HelloApplication extends Application {
    private static Scene mainScene;
    @Override
    public void start(Stage stage) throws IOException {
      
        Post p = new Post(3,2,"oergneorngenr","oaefiazhfzehf","sdoivnsdovnsdon","sdibusdbvsdbviusdbvs","qsdnsdvosdinvnsdinvosn");
       SinglePost holder = SinglePost.getInstance();
                holder.setPlace(p);
                System.out.println( " l id est  "+ p.getId());
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ViewMoreUser.fxml"));
                    Parent parent = fxmlLoader.load();

        //ViewMoreUserController controller = (ViewMoreUserController) fxmlLoader.getController();
           // controller.infalteUI(p);
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
