/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appointment.model.entities;

/**
 *
 * @author HP
 */
public class Response {
    private int id_r;
    private String content_r;
    private String user_r;
    private int nbrj_r;
    private int id_question;

    public Response(String content_r, String user_r, int id_question) {
        this.content_r = content_r;
        this.user_r = user_r;
        this.id_question = id_question;
    }

    public Response(int id_r, String content_r, String user_r, int nbrj_r) {
        this.id_r = id_r;
        this.content_r = content_r;
        this.user_r = user_r;
        this.nbrj_r = nbrj_r;
    }

    public Response() {
    }

    public Response(int id_question, String content_r) {
        this.content_r = content_r;
        this.id_question = id_question;
    }

    public int getId_question() {
        return id_question;
    }


    public int getId_r() {
        return id_r;
    }

    public String getContent_r() {
        return content_r;
    }

    public String getUser_r() {
        return user_r;
    }

    public int getNbrj_r() {
        return nbrj_r;
    }

    public void setId_r(int id_r) {
        this.id_r = id_r;
    }

    public void setContent_r(String content_r) {
        this.content_r = content_r;
    }

    public void setUser_r(String user_r) {
        this.user_r = user_r;
    }

    public void setNbrj_r(int nbrj_r) {
        this.nbrj_r = nbrj_r;
    }

    @Override
    public String toString() {
        return "Response{" + "id_r=" + id_r + ", content_r=" + content_r + ", user_r=" + user_r + ", nbrj_r=" + nbrj_r + '}';
    }
    
    
}
