package com.historicalreferencebook.historicalreferencebook.util;

import com.historicalreferencebook.historicalreferencebook.jpql.LanguageCount;
import com.historicalreferencebook.historicalreferencebook.jpql.StatesMoreAvgPopulation;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class StateDensityPDFExporter {

    private List<StatesMoreAvgPopulation> listStates;

    public StateDensityPDFExporter(List<StatesMoreAvgPopulation> listStates) {
        this.listStates = listStates;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Official Language Name", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Population Density", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(StatesMoreAvgPopulation count: listStates){
            table.addCell(count.getName());
            table.addCell(String.valueOf(count.getResult()));
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        Paragraph title = new Paragraph("Total number of states with population density more than Average", font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        //table.setWidths(new float[] {25.0f, 2.0f, 2.0f} );

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
