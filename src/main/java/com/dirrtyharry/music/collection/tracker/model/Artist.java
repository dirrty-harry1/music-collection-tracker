package com.dirrtyharry.music.collection.tracker.model;

import java.util.SortedSet;
import java.util.TreeSet;

import com.dirrtyharry.music.collection.tracker.comparator.ByName;

public class Artist extends Entry implements HasAlbumCountAndName {
  private SortedSet<Album> albums; 

  public Artist(String name) {    
    super(name);
    albums = new TreeSet<>(ByName.getInstance());
  }  

  public SortedSet<Album> getAlbums() {
    return albums;
  }
  
  public void addAlbum(Album album) {
    albums.add(album);
  }
  
  @Override
  public Integer getAlbumCount() {
    return albums.size();
  }
}
