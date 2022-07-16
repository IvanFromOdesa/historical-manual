package com.historicalreferencebook.historicalreferencebook.util;

import com.historicalreferencebook.historicalreferencebook.jpql.Person;
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

public class PersonPDFExporter extends AbstractPDFExporter {

    private final Set<Person> list;

    public PersonPDFExporter(Set<Person> list) {
        this.list = list;
    }

    @Override
    protected void setPhrasesAndAddCells() {
        cell.setPhrase(new Phrase("Person Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Position", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Role", font));
        table.addCell(cell);
    }

    @Override
    protected void writeTableData(PdfPTable table){
        list.forEach(entity-> {
            table.addCell(entity.getName());
            table.addCell(entity.getPosition());
            table.addCell(String.valueOf(entity.getRole()));
        });
    }

    @Override
    protected Paragraph setTitle() {
        return new Paragraph("All existing Persons (listed below)", font);
    }

    @Override
    protected PdfPTable setTable() {
        return new PdfPTable(3);
    }
}
