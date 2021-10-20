package com.dirrtyharry.music.collection.tracker;

import com.dirrtyharry.music.collection.tracker.comparator.ByCdCount;
import com.dirrtyharry.music.collection.tracker.model.Artist;
import com.dirrtyharry.music.collection.tracker.reader.FolderReader;
import com.dirrtyharry.music.collection.tracker.writer.CsvWriter;
import com.dirrtyharry.music.collection.tracker.writer.PdfWriter;
import com.dirrtyharry.music.collection.tracker.writer.Writer;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    final ResourceBundle text =
        ResourceBundle.getBundle("com.dirrtyharry.music.collection.tracker.text", Locale.GERMAN);

    final Scanner scanner = new Scanner(System.in);
    System.out.println(text.getString("output.format.choose"));

    final String outputFormat = scanner.nextLine();
    switch (outputFormat.toLowerCase()) {
      case "c":
        System.out.println(text.getString("csv.progress"));
        generateFile(
            CsvWriter.getInstance(),
            new File("/home/harry/Dokumente/Workspaces/Musiksammlung/Musiksammlung.csv"));
        break;
      case "p":
        System.out.println(text.getString("pdf.progress"));
        generateFile(
            PdfWriter.getInstance(),
            new File("/home/harry/Dokumente/Workspaces/Musiksammlung/Musiksammlung.pdf"));
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

  private static void generateFile(Writer writer, File file) {
    final List<Artist> artists =
        FolderReader.getInstance()
            .extractMetaData(new File("/home/harry/Dokumente/Workspaces/Musiksammlung/"));
    artists.sort(ByCdCount.getInstance());
    writer.write(artists, file);
  }
}
