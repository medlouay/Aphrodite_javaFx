/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aphrodite.entities;

/**
 *
 * @author Nasr
 */
public class Stars {
    int id ; 
    int idPost ; 
    int userId ;
    Double rateIndex ;

    public Stars(int id, int idPost, int userId, Double rateIndex) {
        this.id = id;
        this.idPost = idPost;
        this.userId = userId;
        this.rateIndex = rateIndex;
    }

    public Stars(int idPost, int userId, Double rateIndex) {
        this.idPost = idPost;
        this.userId = userId;
        this.rateIndex = rateIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Double getRateIndex() {
        return rateIndex;
    }

    public void setRateIndex(Double rateIndex) {
        this.rateIndex = rateIndex;
    }
    
    
}
