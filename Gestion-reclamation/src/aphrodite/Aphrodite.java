/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aphrodite;

import entities.Reclamation;
import entities.Response;
import java.text.ParseException;
import Service.IService;
import Service.ServiceReclamation;
//import Service.ServiceResponse;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author abdel
 */
public class Aphrodite {
    
    public static void main(String[] args) {
        // Create instances of services
        IService<Reclamation> serviceReclamation = new ServiceReclamation();
        //IService<Reponse> serviceReponse = new ServiceReponse();
        

        // Test adding a product with a promotion
       // Reponse reponse = null;
       // reponse = new Reponse(1, sdf.parse("2023-04-01"), sdf.parse("2023-04-01"), 10);
     
        //serviceReponse.ajouter(reponse);
   

       
        List<Reclamation> reclamations = serviceReclamation.afficher();
        for (Reclamation r : reclamations) {
            System.out.println(r);
        }

        //Reponse newReponse = null;
       // newReponse = new Reponse(2, sdf.parse("2023-04-01"), sdf.parse("2023-04-01"), 20);
        //serviceReponse.ajouter(newReponse);
      

        
        reclamations = serviceReclamation.afficher();
        for (Reclamation p : reclamations) {
            System.out.println(p);
        }

       
       // Reclamation r = new Reclamation(17);
        
       //serviceReclamation.supprimer(r);
        // Test displaying all products with their promotions after deletion
        reclamations = serviceReclamation.afficher();
        for (Reclamation p : reclamations) {
            System.out.println(p);
        }
    }
}
