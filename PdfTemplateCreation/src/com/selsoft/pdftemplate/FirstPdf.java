package com.selsoft.pdftemplate;

import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class FirstPdf {
	
	private static String FILE = "c:/temp/FirstPdf.pdf";
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public static void main(String[] args) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            //addMetaData(document);
            //addTitlePage(document);
            addContent(document);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    /*private static void addMetaData(Document document) {
        document.addTitle("My first PDF");
        document.addSubject("Using iText");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("Lars Vogel");
        document.addCreator("Lars Vogel");
    }
*/
  

    private static void addContent(Document document) throws DocumentException {
        Anchor anchor = new Anchor("Rental Company", catFont);
        anchor.setName("Rental Comopany");

        // Second parameter is the number of the chapter
        Chapter catPart = new Chapter(new Paragraph(anchor), 1);

       Paragraph subPara = new Paragraph("Rental Company", subFont);
        Section subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("Street Address"));
        subCatPart.add(new Paragraph("City"));
        subCatPart.add(new Paragraph("ST ZIP"));
        subCatPart.add(new Paragraph("Phone"));
        
       
        
        

        // add a list
       // createList(subCatPart);
        Paragraph paragraph = new Paragraph();
        addEmptyLine(paragraph, 5);
        subCatPart.add(paragraph);
        
        // add a table
        createTable(subCatPart);
       
        // now add all this to the document
        document.add(catPart);

        // Next section
        /*anchor = new Anchor("Second Chapter", catFont);
        anchor.setName("Second Chapter");

        // Second parameter is the number of the chapter
        catPart = new Chapter(new Paragraph(anchor), 1);

        subPara = new Paragraph("Subcategory", subFont);
        subCatPart = catPart.addSection(subPara);
        subCatPart.add(new Paragraph("This is a very important message"));
*/
        // now add all this to the document
        document.add(catPart);

    }

    private static void createTable(Section subCatPart)
            throws BadElementException {
        PdfPTable table = new PdfPTable(4);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("DATE"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("REF"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("DESCRIPTION"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("AMOUNT"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        table.addCell("1/15/14");
        table.addCell("");
        table.addCell("Balance forward");
        table.addCell("50.00");
        
        table.addCell("1/15/14");
        table.addCell("INV 3005");
        table.addCell("rent for february 14 Due 2/1/2014");
        
        table.addCell("2/2/14");
        table.addCell("");
        table.addCell("$20 Late Fee");
        table.addCell("20.00");
        
        table.addCell("1000.00");
        
        table.addCell("2/10/14");
        table.addCell("CHK 1228");
        table.addCell("Payment Received-Thank you");
        table.addCell("-1,0,70.00");
        
        table.addCell("2/15/14");
        table.addCell("INV 3018");
        table.addCell("Rent for March 14 Due 3/1/2014");
        table.addCell("1,000.00");
        
        table.addCell("3/2/14");
        table.addCell("");
        table.addCell("$20 Late Fee");
        table.addCell("20.00");
        
        table.addCell("3/15/14");
        table.addCell("CHK 1234");
        table.addCell("Payment received-Thank you");
        table.addCell("-1,000.00");
        
        table.addCell("3/15/14");
        table.addCell("INV 3011");
        table.addCell("Rent for April '14 Due 4/1/2014");
        table.addCell("1,000.00");
        
        table.addCell("3/29/14");
        table.addCell("CHK 1242");
        table.addCell("Payment received-Thank you.");
        table.addCell("-1,000.00");
        
        table.addCell("4/15/14");
        table.addCell("INV 3015");
        table.addCell("Rent for May 14 Due 5/01/14");
        table.addCell("1,000.00");
        
        
        subCatPart.add(table);

    }

   /* private static void createList(Section subCatPart) {
        List list = new List(true, false, 10);
        list.add(new ListItem("CustomerName"));
        list.add(new ListItem("Street Address"));
        list.add(new ListItem("City"));
        list.add(new ListItem("ST ZIP"));
        list.add(new ListItem("Phone"));
        
        
        subCatPart.add(list);
    }*/

    
    
    private void addFooter(PdfWriter writer){
    	
    	
        Image total = null;
        PdfPTable footer = new PdfPTable(3);
        try {
            // set defaults
            footer.setWidths(new int[]{24, 2, 1});
            footer.setTotalWidth(527);
            footer.setLockedWidth(true);
            footer.getDefaultCell().setFixedHeight(40);
            footer.getDefaultCell().setBorder(Rectangle.TOP);
            footer.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

            // add copyright
            footer.addCell(new Phrase("\u00A9 Memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));

            // add current page count
            footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), new Font(Font.FontFamily.HELVETICA, 8)));

            // add placeholder for total page count
            PdfPCell totalPageCount = new PdfPCell(total);
            totalPageCount.setBorder(Rectangle.TOP);
            totalPageCount.setBorderColor(BaseColor.LIGHT_GRAY);
            footer.addCell(totalPageCount);

            // write page
            PdfContentByte canvas = writer.getDirectContent();
            canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
            footer.writeSelectedRows(0, -1, 34, 50, canvas);
            canvas.endMarkedContentSequence();
        } catch(DocumentException de) {
            throw new ExceptionConverter(de);
        }
    }
    
    
    
    
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}


