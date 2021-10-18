package com.dirrtyharry.music.collection.tracker.writer;

import com.dirrtyharry.music.collection.tracker.model.Artist;
import java.io.File;
import java.util.List;

public interface Writer {
  void write(List<Artist> artists, File file);
}
