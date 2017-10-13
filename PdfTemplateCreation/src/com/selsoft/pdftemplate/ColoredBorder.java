package com.selsoft.pdftemplate;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import java.io.File;
 

public class ColoredBorder  {
    public static final String DEST = "c:/temp/FirstPdf.pdf";
 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ColoredBorder().manipulatePdf(DEST);
    }
 
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);
 
        Table table;
        table = new Table(2);
        Cell cell;
        cell = new Cell().add("Cell 1");
        cell.setBorderTop(new SolidBorder(Color.RED, 1));
        cell.setBorderBottom(new SolidBorder(Color.BLUE, 1));
        table.addCell(cell);
        cell = new Cell().add("Cell 2");
        cell.setBorderLeft(new SolidBorder(Color.GREEN, 5));
        cell.setBorderTop(new SolidBorder(Color.YELLOW, 8));
        table.addCell(cell);
        cell = new Cell().add("Cell 3");
        cell.setBorderLeft(new SolidBorder(Color.RED, 1));
        cell.setBorderBottom(new SolidBorder(Color.BLUE, 1));
        table.addCell(cell);
        cell = new Cell().add("Cell 4");
        cell.setBorderLeft(new SolidBorder(Color.GREEN, 5));
        cell.setBorderTop(new SolidBorder(Color.YELLOW, 8));
        table.addCell(cell);
 
        doc.add(table);
 
        doc.close();
    }

}
