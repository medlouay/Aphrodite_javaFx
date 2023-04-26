/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import tools.Connexion;

/**
 * FXML Controller class
 *
 * @author User
 */
public class StatsController implements Initializable {

    @FXML
    private PieChart PieChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
    //staatt
public class Stats1Controller implements Initializable {
@FXML
    private PieChart pieChart;

    ObservableList<PieChart.Data> pieChartData;
    ArrayList<String> p = new ArrayList<>();
    ArrayList<Integer> c = new ArrayList<>();
    @FXML
    private Button Next;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
        pieChart.setData(pieChartData);
    }
//staaaat
    
    public void loadData() {

        String query = "select COUNT(*) as count, nom from produit group by nom";

        pieChartData = FXCollections.observableArrayList();

        try (Connection cnx = Connexion.getInstance().getCnx()) {

            ResultSet rs = cnx.createStatement().executeQuery(query);
            int total = 0;
            while (rs.next()) {
                total += rs.getInt("count");
                c.add(rs.getInt("count"));
                p.add(rs.getString("nom"));
            }
            for (int i = 0; i < c.size(); i++) {
                double percentage = (double) c.get(i) / total * 100;
                pieChartData.add(new PieChart.Data(p.get(i) + " " + String.format("%.2f", percentage) + "%", c.get(i)));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

   
    }

    
}
