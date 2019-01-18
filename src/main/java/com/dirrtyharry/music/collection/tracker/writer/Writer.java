package com.dirrtyharry.music.collection.tracker.writer;

import java.io.File;
import java.util.List;

import com.dirrtyharry.music.collection.tracker.model.Artist;

public interface Writer {
  void write(List<Artist> artists, File file);
}
