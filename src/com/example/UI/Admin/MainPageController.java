package com.example.UI.Admin;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MainPageController {

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
    private TextField searchField;

    @FXML
    private JFXTabPane mainTabPane;

    @FXML
    private Tab renewTab;

    @FXML
    private GridPane PlacesGrid;

    @FXML
    private Tab bookIssueTab;

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
    void HandleLoginOperation(ActionEvent event) {

    }

}
