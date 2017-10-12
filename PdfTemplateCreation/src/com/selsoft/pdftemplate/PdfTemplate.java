package com.selsoft.pdftemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfTemplate {
	
	/*
	 public static final String RESULT= "D:\rental-billing-statement-template.pdf";
	public static void main(String[] args) throws DocumentException,
	            IOException {
		
		new PdfTemplate().createPdf(RESULT);
	}
		 public void createPdf(String filename)
					throws DocumentException, IOException {
	
	   try{
			// step 1
			Document document = new Document();
			// step 2
			PdfWriter.getInstance(document, new FileOutputStream(filename));
			// step 3
			document.open();
			// step 4
			document.add(new Paragraph("Hello World!"));
			// step 5
			document.close();
		}

		catch (Exception exception) {
			System.out.println("Document Exception!" + exception);
		}
	}

	@SuppressWarnings("unused")
	private static void createTable(Section subCatPart) throws BadElementException {
		PdfPTable table = new PdfPTable(3);

		// t.setBorderColor(BaseColor.GRAY);
		// t.setPadding(4);
		// t.setSpacing(4);
		// t.setBorderWidth(1);

		PdfPCell c1 = new PdfPCell(new Phrase("Rental Company"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("Customer Name"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);

		c1 = new PdfPCell(new Phrase("STATEMENT"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Property"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		c1 = new PdfPCell(new Phrase("Account Activity"));
		c1.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(c1);
		
		
		table.setHeaderRows(1);

		table.addCell("1.0");
		table.addCell("1.1");
		table.addCell("1.2");
		table.addCell("2.1");
		table.addCell("2.2");
		table.addCell("2.3");

		subCatPart.add(table);

	}

	private static void createList(AccountActivity acountActivity) {
		List list = new List(true, false, 10);
		list.add(new ListItem("DATE"));
		list.add(new ListItem("REF"));
		list.add(new ListItem("DESCRIPTION"));
		acountActivity.add(list);
	}

	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}

	}

	}
*/
}
