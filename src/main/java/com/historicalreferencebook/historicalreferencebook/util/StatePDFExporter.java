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
import java.util.Set;

public class StatePDFExporter extends AbstractPDFExporter {
    private final Set<LanguageCount> list;

    public StatePDFExporter(Set<LanguageCount> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Official Language Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Number of States", font));
        table.addCell(cell);
    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getLangName());
            table.addCell(String.valueOf(entity.getTotal()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("List of Languages used in States", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
