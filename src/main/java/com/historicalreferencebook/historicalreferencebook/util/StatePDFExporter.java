package com.historicalreferencebook.historicalreferencebook.util;

import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StatePDFExporter {
    private List<LanguageCount> listStates;

    public StatePDFExporter(List<LanguageCount> listStates) {
        this.listStates = listStates;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Official Language Name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of States", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(LanguageCount count: listStates){
            table.addCell(count.getLangName());
            table.addCell(String.valueOf(count.getTotal()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        Paragraph title = new Paragraph("List of Languages used in States", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        //table.setWidths(new float[] {1.5f, 2.0f} );

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
