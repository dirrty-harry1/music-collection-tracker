package com.dirrtyharry.music.collection.tracker.model;

public class Album extends Entry {  
  private Integer cdCount;  
  
  public Album(String name, Integer cdCount) {    
    super(name);    
    this.cdCount = cdCount;
  }  
  
  public Integer getCdCount() {
    return cdCount;
  } 
}
