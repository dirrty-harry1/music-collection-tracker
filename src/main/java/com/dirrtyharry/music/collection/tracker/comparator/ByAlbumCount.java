package com.dirrtyharry.music.collection.tracker.comparator;

import java.util.Comparator;

import com.dirrtyharry.music.collection.tracker.model.HasAlbumCountAndName;

public class ByAlbumCount implements Comparator<HasAlbumCountAndName> { 
  
  private static ByAlbumCount instance;

  private ByAlbumCount() {
  }

  public synchronized static ByAlbumCount getInstance() {
    if (instance == null) {
      instance = new ByAlbumCount();
    }
    return instance;
  }

  @Override
  public int compare(HasAlbumCountAndName arg0, HasAlbumCountAndName arg1) {
    final int compareValue = arg0.getAlbumCount().compareTo(arg1.getAlbumCount());
    return compareValue == 0 ? ByName.getInstance().compare(arg0, arg1) : compareValue;
  }
}
