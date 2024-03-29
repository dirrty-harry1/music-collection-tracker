package com.dirrtyharry.music.collection.tracker.writer;

import com.dirrtyharry.music.collection.tracker.model.Album;
import com.dirrtyharry.music.collection.tracker.model.Artist;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvWriter implements Writer {
  private static final String FILE_SUFFIX = "csv";
  private static CsvWriter instance;

  private CsvWriter() {}

  public static synchronized CsvWriter getInstance() {
    if (instance == null) {
      instance = new CsvWriter();
    }
    return instance;
  }

  @Override
  public String getFileSuffix() {
    return FILE_SUFFIX;
  }

  @Override
  public void write(List<Artist> artists, File file) {
    if (file.exists()) {
      file.delete();
    }

    try {
      final PrintWriter printWriter = new PrintWriter(file);
      final StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("\"artist\",\"album\",\"cds\"\n");
      for (Artist artist : artists) {
        for (Album album : artist.getAlbums()) {
          stringBuilder.append(
              "\""
                  + artist.getName().toLowerCase()
                  + "\",\""
                  + album.getName().toLowerCase()
                  + "\", "
                  + album.getCdCount()
                  + "\n");
        }
      }
      printWriter.write(stringBuilder.toString());
      printWriter.close();
    } catch (IOException e) {
      throw new RuntimeException("Could not write CSV file", e);
    }
  }
}
