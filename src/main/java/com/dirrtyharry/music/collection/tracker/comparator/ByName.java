package com.dirrtyharry.music.collection.tracker.comparator;

import com.dirrtyharry.music.collection.tracker.model.HasName;
import java.util.Comparator;

public class ByName implements Comparator<HasName> {
  private static ByName instance;

  private ByName() {}

  public static synchronized ByName getInstance() {
    if (instance == null) {
      instance = new ByName();
    }
    return instance;
  }

  @Override
  public int compare(HasName arg0, HasName arg1) {
    return arg0.getName().compareTo(arg1.getName());
  }
}
