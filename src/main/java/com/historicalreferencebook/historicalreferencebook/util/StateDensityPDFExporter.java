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
import java.util.Set;

public class StateDensityPDFExporter extends AbstractPDFExporter {

    private final Set<StatesMoreAvgPopulation> list;

    public StateDensityPDFExporter(Set<StatesMoreAvgPopulation> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Official State Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Population Density", font));
        table.addCell(cell);
    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getName());
            table.addCell(String.valueOf(entity.getResult()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("Total number of states with population density more than Average", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(2);
    }
}
