/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author HP
 */

public class Question {
    private int id_q;
    private String content_q;
    private String subject_q;
    private String user_q;
    private int nbrj_q;

    public Question(int id_q, String content_q, String subject_q) {
        this.id_q = id_q;
        this.content_q = content_q;
        this.subject_q = subject_q;
    }

    
    public Question(int id_q, String content_q, String subject_q, String user_q, int nbrj_q) {
        this.id_q = id_q;
        this.content_q = content_q;
        this.subject_q = subject_q;
        this.user_q = user_q;
        this.nbrj_q = nbrj_q;
    }

    public Question(String content_q, String subject_q, String user_q, int nbrj_q) {
        this.content_q = content_q;
        this.subject_q = subject_q;
        this.user_q = user_q;
        this.nbrj_q = nbrj_q;
    }

    public Question() {
    }
    
    
    
    public int getId_q() {
        return id_q;
    }

    public void setId_q(int id_q) {
        this.id_q = id_q;
    }

    public String getContent_q() {
        return content_q;
    }

    public void setContent_q(String content_q) {
        this.content_q = content_q;
    }

    public String getSubject_q() {
        return subject_q;
    }

    public void setSubject_q(String subject_q) {
        this.subject_q = subject_q;
    }

    public String getUser_q() {
        return user_q;
    }

    public void setUser_q(String user_q) {
        this.user_q = user_q;
    }

    public int getNbrj_q() {
        return nbrj_q;
    }

    public void setNbrj_q(int nbrj_q) {
        this.nbrj_q = nbrj_q;
    }

    @Override
    public String toString() {
        return "Question{" + "id_q=" + id_q + ", content_q=" + content_q + ", subject_q=" + subject_q + ", user_q=" + user_q + ", nbrj_q=" + nbrj_q + '}';
    }

    
}
