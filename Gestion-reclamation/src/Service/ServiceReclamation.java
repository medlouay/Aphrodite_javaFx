
package Service;

import entities.Reclamation;
import entities.Response;
import utils.DataSource;
import utils.DataSource;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;     
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author oumaima
 */
public class ServiceReclamation implements IService<Reclamation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reclamation r) {
        String req = "INSERT INTO reclamation (id_patient_id, titre, message, description) VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId_patient_id());
            pst.setString(2, r.getTitre());
            pst.setLong(3, r.getMessage());
            pst.setString(4, r.getDescription());
         
            pst.executeUpdate();
            System.out.println("Reclamation ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Reclamation r) {
        String req = "UPDATE reclamation SET id_patient_id=?, titre=?, message=?, description=? WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId_patient_id());
            pst.setString(2, r.getTitre());
            pst.setLong(3, r.getMessage());
            pst.setString(4, r.getDescription());
            
            pst.executeUpdate();
            System.out.println("Reclamation modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reclamation r) {
        String req = "DELETE FROM reclamation WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.out.println("Reclamation supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Reclamation> afficher() {
        List<Reclamation> list = new ArrayList<>();
        String req = "SELECT * FROM reclamation";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Reclamation r = new Reclamation(
//                        rs.getInt("id"),
//                        new Promotion(rs.getInt("promo_id")),
//                        rs.getString("nom"),
//                        rs.getInt("quantite"),
//                        rs.getDouble("prix"),
//                        rs.getString("categorie"),
//                        rs.getString("description"),
//                        rs.getString("image"),
//                        rs.getString("lat"),
//                        rs.getString("lon")
                );
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

   
}

