/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.appointment.model.services;
import com.example.appointment.model.entities.Response;
import java.util.List;


/**
 *
 * @author HP
 */
public interface commentaireI<T> {
   public List<Question> afficherquestion();
   public void ajouterquestion(Question q);
   public void ModifierContent (int id_q , String content_q);
   public void ModifierSubject (int id_q , String subject_q);
   public void Supprimerquestion (int id_q );
   public void Update (int id_q , String content_q ,String subject_q);
   
   //*******************************************
   public List<Response> afficherreponse();
   public void ajouterreponse(Response r);
   public void Updater(int id_r, String content_r,int id_question);
   public void Supprimerreponse (int id_r );
   
}
 
   

 

