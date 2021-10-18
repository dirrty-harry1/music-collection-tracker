package com.dirrtyharry.music.collection.tracker.reader;

import com.dirrtyharry.music.collection.tracker.model.Album;
import com.dirrtyharry.music.collection.tracker.model.Artist;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FolderReader {

  private static final String DISK_PATTERN = ", Disc";
  private static final Predicate<File> VALID_FOLDER =
      file -> file.isDirectory() && !file.isHidden();

  private static FolderReader instance;

  private FolderReader() {}

  public static synchronized FolderReader getInstance() {
    if (instance == null) {
      instance = new FolderReader();
    }
    return instance;
  }

  public List<Artist> extractMetaData(File rootFolder) {
    return Arrays.asList(rootFolder.listFiles()).stream()
        .filter(VALID_FOLDER)
        .map(folder -> extractArtist(folder))
        .collect(Collectors.toList());
  }

  private Artist extractArtist(File artistFolder) {
    final Artist artist = new Artist(artistFolder.getName());
    extractAlbums(artistFolder).forEach(artist::addAlbum);
    return artist;
  }

  private Collection<Album> extractAlbums(File artistFolder) {
    final Set<String> albumNames =
        Arrays.asList(artistFolder.listFiles()).stream()
            .filter(VALID_FOLDER)
            .map(File::getName)
            .collect(Collectors.toSet());
    return extractAlbums(albumNames);
  }

  private Collection<Album> extractAlbums(Collection<String> albumNames) {
    final Map<String, Long> nameAndCdCount =
        albumNames.stream()
            .map(
                name -> {
                  if (name.contains(DISK_PATTERN)) {
                    return name.substring(0, name.indexOf(DISK_PATTERN));
                  } else {
                    return name;
                  }
                })
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    final Set<Album> albums = new HashSet<>();
    nameAndCdCount.forEach((name, count) -> albums.add(new Album(name, count.intValue())));

    return albums;
  }
}
