/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.entities;

/**
 *
 * @author Nasr
 */
public class Tags {
    int postid ; 
    int tagid ;
    String tag ; 

    public Tags(String tag) {
        this.tag = tag;
    }

    public Tags(int postid, int tagid) {
        this.postid = postid;
        this.tagid = tagid;
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    
}
