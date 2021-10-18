package com.dirrtyharry.music.collection.tracker.writer;

import com.dirrtyharry.music.collection.tracker.model.Album;
import com.dirrtyharry.music.collection.tracker.model.Artist;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;

public class PdfWriter implements Writer {
  private static PdfWriter instance;

  private PdfWriter() {}

  public static synchronized PdfWriter getInstance() {
    if (instance == null) {
      instance = new PdfWriter();
    }
    return instance;
  }

  @Override
  public void write(List<Artist> artists, File file) {
    if (file.exists()) {
      file.delete();
    }

    final Document document = new Document();
    try {
      com.itextpdf.text.pdf.PdfWriter.getInstance(document, new FileOutputStream(file));

      document.open();

      final PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(95);
      table.setWidths(new int[] {45, 45, 10});
      addTableHeader(table);

      for (Artist artist : artists) {
        for (Album album : artist.getAlbums()) {
          addRows(
              table,
              Arrays.asList(
                  artist.getName().toLowerCase(),
                  album.getName().toLowerCase(),
                  album.getCdCount().toString()));
        }
      }

      document.add(table);
      document.close();
    } catch (DocumentException de) {
      throw new RuntimeException("Could not create PDF document", de);
    } catch (FileNotFoundException fnfe) {
      throw new RuntimeException("Could not create PDF file", fnfe);
    }
  }

  private void addTableHeader(PdfPTable table) {
    Arrays.asList("Artist", "Album", "CDs")
        .forEach(
            columnTitle -> {
              final PdfPCell header = new PdfPCell();
              header.setBackgroundColor(BaseColor.LIGHT_GRAY);
              header.setBorderWidth(2);
              header.setPhrase(new Phrase(columnTitle));
              table.addCell(header);
            });
  }

  private void addRows(PdfPTable table, List<String> cells) {
    cells.forEach(table::addCell);
  }
}
