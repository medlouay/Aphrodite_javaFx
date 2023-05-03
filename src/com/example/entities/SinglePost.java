/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entities;

/**
 *
 * @author Nasr
 */
public class SinglePost {
          private Post place;
  private final static SinglePost INSTANCE = new SinglePost();
  
  private SinglePost() {}
  
  public static SinglePost getInstance() {
    return INSTANCE;
  }
  
  public void setPlace(Post p) {
    this.place = p;
  }
  
  public Post getPlace() {
    return this.place;
  }
}
