/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Question;
import entities.Response;
import utils.Myconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class commentairecrud implements commentaireI{
    Connection cnx2;
    
    public commentairecrud(){
        cnx2 = Myconnection.getInstance().getcnx();
    }
    

    //********************************************************************************Question
    
    
    public List<Question> afficherquestion(){
        List<Question> mylist = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM question";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Question q = new Question();
                q.setId_q(rs.getInt(1));
                q.setContent_q(rs.getString("content_q")); 
                q.setSubject_q(rs.getString("subject_q"));
                q.setUser_q(rs.getString("user_q"));
                q.setNbrj_q(rs.getInt(1));
                mylist.add(q);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist;
    }


    public List<Response> test(int id){
        
        List<Response> mylist = new ArrayList<>();
        try {
            
            //String requete3 = "SELECT * FROM Reponse WHERE id_question = ?";
            String requete3 = "SELECT e.id_r, e.content_r FROM reponse e INNER JOIN question s ON s.id_q = e.id_question WHERE s.id_q = ?";
            PreparedStatement pst = cnx2.prepareStatement(requete3);
            pst.setInt(1,id);
            ResultSet rs = pst.executeQuery(); 
            while (rs.next()) {
                Response r = new Response();
                r.setId_r(rs.getInt(1));
                r.setContent_r(rs.getString("content_r")); 
                
                mylist.add(r);
               
            }
               
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist;
    }
    
    
    

    
    
        public void ajouterquestion(Question q){
        String requete2="INSERT INTO question (content_q,subject_q,user_q) "
                + "VALUES (?,?,?)";
        try {
            PreparedStatement  pst = cnx2.prepareStatement(requete2);
            pst.setString(1, q.getContent_q());
            pst.setString(2, q.getSubject_q());
            pst.setString(3, q.getUser_q());
            
            pst.executeUpdate();
            System.out.println("votre question est ajoutee");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "il faut remplir le reste des champs pour posez une question");
    }
    
    
    
    public void ModifierContent (int id_q , String content_q)
    {
        try {
            String req3 = "UPDATE question SET content_q= ? where id_q=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, content_q);
            pst.setInt(2, id_q);
           
            pst.executeUpdate() ;
             System.out.println("votre contenue de question est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    
    }
    
    public void ModifierSubject (int id_q , String subject_q)
    {
        try {
            String req3 = "UPDATE question SET subject_q= ? where id_q=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req3) ;
         
            pst.setString(1, subject_q);
            pst.setInt(2, id_q);
           
            pst.executeUpdate() ;
             System.out.println("votre sujet de question est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    
    }



     @Override
    public void Supprimerquestion(int id_q) {
        try {
            String req26 = "DELETE FROM question WHERE id_q=? " ;
            
           PreparedStatement pst= cnx2.prepareStatement(req26) ;
           pst.setInt(1,id_q);
           pst.executeUpdate() ; 
            System.out.print("Votre question est suprrimé ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }
    }
    @Override
    public void Update(int id_q, String content_q, String subject_q) {
        try {
            String req34 = "UPDATE question SET content_q=? , subject_q= ? where id_q=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req34) ;
                        
            pst.setString(1, content_q);
            pst.setString(2, subject_q);
            pst.setInt(3, id_q);
           
            pst.executeUpdate() ;
             System.out.println("votre question est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        JOptionPane.showMessageDialog(null, "il faut remplir le reste des champs pour mettre a jour une question");
    }
  


    //********************************************************************************Response
    


    @Override
        public List<Response> afficherreponse(){
        List<Response> mylist = new ArrayList<>();
        try {
            
            String requete3 = "SELECT * FROM reponse";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while(rs.next()){
                Response r = new Response();
                r.setId_r(rs.getInt(1));
                r.setContent_r(rs.getString("content_r")); 
                r.setUser_r(rs.getString("user_r"));
                r.setNbrj_r(rs.getInt(1));
                mylist.add(r);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return mylist;
    }
    

    @Override
    public void ajouterreponse(Response r) {
        
        String requete2="INSERT INTO reponse (content_r,id_question) "
                + "VALUES (?,?)";
        try {
            PreparedStatement  pst = cnx2.prepareStatement(requete2);
            pst.setString(1, r.getContent_r());
            pst.setInt(2, r.getId_question());
            pst.executeUpdate();
            System.out.println("votre reponse est ajoutee");
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
    }

    @Override
    public void Updater(int id_r, String content_r,int id_question) {
       try {
            String req4 = "UPDATE reponse SET content_r= ? where id_r=?" ;
            PreparedStatement pst = cnx2.prepareStatement(req4) ;
         
            pst.setString(1, content_r);
            pst.setInt(2, id_r);
           
            pst.executeUpdate() ;
             System.out.println("votre contenu de reponse est modifié ");
        
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
       
        JOptionPane.showMessageDialog(null, "il faut remplir le reste des champs pour modifier une reponse");
    }

    @Override
    public void Supprimerreponse(int id_r) {
        try {
            String req00 = "DELETE FROM reponse WHERE id_r=? " ;
            
           PreparedStatement pst2= cnx2.prepareStatement(req00) ;
           pst2.setInt(1,id_r);
           pst2.executeUpdate() ; 
            System.out.print("Votre reponse est suprrimé ");
           
                        
        } catch (SQLException ex) {
          System.err.println(ex.getMessage());
         
        }
    }

    

}

      