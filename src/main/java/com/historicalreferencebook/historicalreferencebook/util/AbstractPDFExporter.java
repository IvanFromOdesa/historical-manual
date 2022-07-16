package com.historicalreferencebook.historicalreferencebook.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public abstract class AbstractPDFExporter {

    protected Document document;
    protected Font font;
    protected PdfPTable table;
    protected PdfPCell cell;
    protected Paragraph title;

    protected void writeTableHeader(){
        cell = new PdfPCell();
        cell.setBackgroundColor(Color.ORANGE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        setPhrasesAndAddCells();
    }

    public void export(HttpServletResponse response) throws IOException {
        document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);

        title = setTitle();
        title.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);

        table = setTable();
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader();
        writeTableData(table);

        document.add(table);

        document.close();
    }

    protected abstract void setPhrasesAndAddCells();

    protected abstract void writeTableData(PdfPTable table);

    protected abstract Paragraph setTitle();

    protected abstract PdfPTable setTable();
}
