package com.dirrtyharry.music.collection.tracker.comparator;

import java.util.Comparator;

import com.dirrtyharry.music.collection.tracker.model.HasAlbumCountAndName;

public class ByCdCount implements Comparator<HasAlbumCountAndName> { 
  
  private static ByCdCount instance;

  private ByCdCount() {
  }

  public synchronized static ByCdCount getInstance() {
    if (instance == null) {
      instance = new ByCdCount();
    }
    return instance;
  }

  @Override
  public int compare(HasAlbumCountAndName arg0, HasAlbumCountAndName arg1) {
    final int compareValue = arg0.getCdCount().compareTo(arg1.getCdCount());
    return compareValue == 0 ? ByName.getInstance().compare(arg0, arg1) : compareValue;
  }
}
