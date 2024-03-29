package com.dirrtyharry.music.collection.tracker.model;

import com.dirrtyharry.music.collection.tracker.comparator.ByName;
import java.util.SortedSet;
import java.util.TreeSet;

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
  public Integer getCdCount() {
    return albums.stream().map(Album::getCdCount).mapToInt(Integer::intValue).sum();
  }
}
