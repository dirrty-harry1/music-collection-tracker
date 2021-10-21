package com.dirrtyharry.music.collection.tracker;

import static java.io.File.separator;
import static java.lang.String.format;

import com.dirrtyharry.music.collection.tracker.comparator.ByCdCount;
import com.dirrtyharry.music.collection.tracker.model.Artist;
import com.dirrtyharry.music.collection.tracker.reader.FolderReader;
import com.dirrtyharry.music.collection.tracker.writer.CsvWriter;
import com.dirrtyharry.music.collection.tracker.writer.PdfWriter;
import com.dirrtyharry.music.collection.tracker.writer.Writer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App {
  private static Properties config;

  public static void main(String[] args) {
    config = getConfig();
    final ResourceBundle text =
        ResourceBundle.getBundle(
            "com.dirrtyharry.music.collection.tracker.text",
            Locale.forLanguageTag(config.getProperty("locale")));

    final Scanner scanner = new Scanner(System.in);
    System.out.println(text.getString("output.format.choose"));

    final String outputFormat = scanner.nextLine();
    switch (outputFormat.toLowerCase()) {
      case "c":
        System.out.println(text.getString("csv.progress"));
        generateFile(CsvWriter.getInstance());
        break;
      case "p":
        System.out.println(text.getString("pdf.progress"));
        generateFile(PdfWriter.getInstance());
        break;
      case "q":
        System.out.println(text.getString("closing.progress"));
        break;
      default:
        System.out.println(text.getString("output.format.error"));
        break;
    }

    scanner.close();

    System.out.println(text.getString("closing.done"));
  }

  private static Properties getConfig() {
    final Properties props = new Properties();
    try {
      props.load(new FileInputStream(format("..%sconfig.properties", separator)));
      return props;
    } catch (IOException e) {
      throw new RuntimeException("Could not load config file", e);
    }
  }

  private static void generateFile(Writer writer) {
    final File file =
        new File(
            format(
                "%s%s%s.%s",
                config.getProperty("output.directory"),
                separator,
                config.getProperty("output.name"),
                writer.getFileSuffix()));
    final List<Artist> artists =
        FolderReader.getInstance().extractMetaData(new File(config.getProperty("input.directory")));
    artists.sort(ByCdCount.getInstance());
    writer.write(artists, file);
  }
}
