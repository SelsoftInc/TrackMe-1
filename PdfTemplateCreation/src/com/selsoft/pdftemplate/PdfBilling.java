package com.selsoft.pdftemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;

public class PdfBilling {
	public static final String DEST = "C:/temp/biling.pdf";
	public static final String CSS = "table {border-collapse: collapse; width: 100%;}" + "tr { text-align: left; } "
			+ "th { color: white; background-color:  #2b3a76; padding: 2px; } "
			+ "td {font-size:15px; background-color: white;  padding: 2px; border-bottom: 1px solid #d7e8f9; }"
			+ "tr.balance td {background-color:  #d7e8f9;border-top: 1px solid #000;}" + ".nocss {}";
	
	
	
	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new PdfBilling().createPdf(DEST);
	}

	private Object header;

	/**
	 * Creates a PDF with the given png  file"
	 * 
	 * @param file
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPdf(String file) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
		// step 3
		document.open();
		
		StringBuilder sb=new StringBuilder();
		sb.append("<div align=\"center\"><b>Rental Company</b></div>");
		PdfPTable table3 = getHeadTable();
		document.add(Chunk.NEWLINE);
		document.add(table3);
		
		// step 4
		document.add(getHead(new StringBuilder("<div><b>Account Activity</b></div>")));
		PdfPTable table = getTable();
		PdfPTable table2 = getFootTable();
		
		document.add(table);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		Paragraph p = new Paragraph("Please detach the remittance slip below and return it with your payment.");
		DottedLineSeparator dottedline = new DottedLineSeparator();
		dottedline.setOffset(-2);
		dottedline.setGap(2f);
		p.add(dottedline);
		document.add(p);
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		
		
		
		
		StringBuilder builder=new StringBuilder();
		builder.append("<div align=\"center\"><b>REMITANCE</b></div>");
		document.add(getHead(builder));
		document.add(table2);
		

		// step 5
		document.close();
	}

	
	
	 /*public void createPdf(String dest) throws IOException, DocumentException {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(dest));
	        document.open();
	        PdfPTable table = new PdfPTable(4);
	        table.setWidths(new int[]{1, 2, 1, 1});
	        table.addCell(createCell("DATE", 2, 1, Element.ALIGN_LEFT));
	        table.addCell(createCell("REF", 2, 1, Element.ALIGN_LEFT));
	        table.addCell(createCell("DESCRIPTION", 2, 1, Element.ALIGN_LEFT));
	        table.addCell(createCell("AMOUNT", 2, 1, Element.ALIGN_LEFT));
	       
	        String[][] data = {
	            {"1/15/14", " ", "Balance Forward",  "50.00"},
	            {"1/15/14", "INV 3005 ", "Rent for February 14 Due 2/1/201",  "1,000.00"},
	            {"2/2/14", " ", "$20 Late Fee",  "50.00"},
	            {"2/10/14", " CHK 1228", "Payment Received- Thank you",  "-1,070.00"},
	            {"2/15/14", "INV 3008 ", "Rent for March 14 Due 3/1/2014",  "1,000.00"},
	            {"3/2/14", " ", "$20 late Fee",  "20.00"},
	            {"3/5/14", " CHK 1234", "Payment Received- Thank you",  "-1,000.00"},
	            {"3/15/14", " INV 3011", "Rent for April 14 Due 4/1/2014",  "1,000.00"},
	            {"3/29/14", "CHK 1242 ", "Payment Received- Thank you",  "-1.000.00"},
	            {"4/15/14", " INV 3015", "Rent for May 14 Due 5/1/2014",  "1,000.00"}
	        };
	        for (String[] row : data) {
	            table.addCell(createCell(row[0], 1, 1, Element.ALIGN_LEFT));
	            table.addCell(createCell(row[1], 1, 1, Element.ALIGN_LEFT));
	            table.addCell(createCell(row[2], 1, 1, Element.ALIGN_RIGHT));
	            table.addCell(createCell(row[3], 1, 1, Element.ALIGN_RIGHT));
	            //table.addCell(createCell(row[4], 1, 1, Element.ALIGN_RIGHT));
	        }
	        table.addCell(createCell("BALANCE DUE", 2, 4, Element.ALIGN_RIGHT));
	        table.addCell(createCell("$1,020.00", 2, 1, Element.ALIGN_RIGHT));
	        document.add(table);
	        document.close();
	    }
	    
	    public PdfPCell createCell(String content, float borderWidth, int colspan, int alignment) {
	        PdfPCell cell = new PdfPCell(new Phrase(content));
	        cell.setBorderWidth(borderWidth);
	        cell.setColspan(colspan);
	        cell.setHorizontalAlignment(alignment);
	        return cell;
	    }

	*/
	
	
	
	public Element getHead(StringBuilder head) throws IOException {
		Element element = getElements(head);
		return element;
	}

	
	public PdfPTable getHeadTable() throws IOException {
		
		
		    
		   
		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append(
				"<th style=\"text-align: left;background-color:white;color:black;\" width=\"50%\">[Rental Company]:</th>");
		sb.append(
				"<th style=\"text-align: right;background-color:white;color:blue;\" width=\"90%\"><b>STATEMENT</b> </th>");
		sb.append("<th style=\"text-align: center;background-color:white;color:black;\" width=\"20%\"></th>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[Street Address]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("");
		sb.append("</td>");
		sb.append("</tr>");

		
		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[City, ST ZIP]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("Statement Date");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb.append("04/30/2014");
		sb.append("</td>");
		sb.append("</tr>");
		
				
		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("phone:000 000-0000");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("CUSTOMER ID");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb.append("[ABC 123]");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("Bill To:[Customer Name]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("property  [Street Address]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append(" ");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[Street Address]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("City, [ST ZIP");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("  ");
		sb.append("</td>");
		sb.append("</tr>");
		
		
		
		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\" >");
		sb.append("City, [ST ZIP]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("Contract From     1-Feb-2014");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("  ");
		sb.append("</td>");
		sb.append("</tr>");
		
		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[phone]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append(" To       31-Jan-2015");
		sb.append("</td>");
		
				
		//sb.append("<td >");
		sb.append("   ");
		//sb.append("</td>");
		sb.append("</tr>");

		sb.append("</table>");

		return (PdfPTable) getElements(sb);

	}
	
	
	public PdfPTable getTable() throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th style=\"text-align: center;\" width=\"20%\">DATE</th>");
		sb.append("<th style=\"text-align: center;\" width=\"15%\">REF</th>");
		sb.append("<th width=\"45%\">DESCRIPTION</th>");
		sb.append("<th width=\"20%\">AMOUNT</th>");
		sb.append("</tr>");

		for (int i = 0; i < 10;) {
			i++;
			sb.append("<tr>");
			sb.append("<td style=\"text-align: center;\">");
			sb.append("1/15/14");
			sb.append("</td>");
			sb.append("<td style=\"text-align: center;\">");
			sb.append("CHK 1228");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("Balance Forward");
			sb.append("</td>");
			sb.append("<td style=\"text-align: right;\">");
			sb.append("100.11");
			sb.append("</td>");
			sb.append("</tr>");
			
			
			
			
		}
		
		
		
		
             

		for (int i = 0; i < 10;) {
			i++;
			sb.append("<tr>");
			sb.append("<td style=\"text-align: center;\">");
			sb.append(" -- ");
			sb.append("</td>");
			sb.append("<td style=\"text-align: center;\">");
			sb.append(" -- ");
			sb.append("</td>");
			sb.append("<td>");
			sb.append(" -- ");
			sb.append("</td>");
			sb.append("<td style=\"text-align: right;\">");
			sb.append(" -- ");
			sb.append("</td>");
			sb.append("</tr>");
		}

		sb.append("<tr class=\"balance\">");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("<td>");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;\">");
		sb.append("<b>BALANCE DUE</b>");
		sb.append("</td>");
		sb.append("<td border=\"1\" style=\"background-color: #a4cef7;text-align: right;\">");
		sb.append("$ 102020.993");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("</table>");

		return (PdfPTable) getElements(sb);

	}

	public PdfPTable getFootTable() throws IOException {

		StringBuilder sb = new StringBuilder();
		sb.append("<table>");
		sb.append("<tr>");
		sb.append(
				"<th style=\"text-align: left;background-color:white;color:black;\" width=\"60%\">Please make Check Payable to [Name] and mail to:</th>");
		sb.append(
				"<th style=\"text-align: right;background-color:white;color:black;\" width=\"30%\"> STATEMENT DATE</th>");
		sb.append("<th style=\"text-align: center;background-color:white;color:black;\" width=\"20%\">04/30/2014</th>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[Company Name]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("CUSTOMER ID");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("[ABC123]");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[Street Address]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("[City, ST, ZIP]");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("DUE DATE");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb.append("05/30/2014");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("BALANCE DUE");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb.append("$ 1,020.00");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("-");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("-");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append(" ");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append("Please write Customer ID on your check.");
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append("AMMOUNT ENCLOSED");
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb.append("  ");
		sb.append("</td>");
		sb.append("</tr>");
		
		

		sb.append("</table>");
		
		

		return (PdfPTable) getElements(sb);

	}

	public Element getElements(StringBuilder sb) throws IOException {
		CSSResolver cssResolver = new StyleAttrCSSResolver();
		CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(CSS.getBytes()));
		cssResolver.addCss(cssFile);

		// HTML
		HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);
		htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

		// Pipelines
		ElementList elements = new ElementList();
		ElementHandlerPipeline pdf = new ElementHandlerPipeline(elements, null);
		HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
		CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);

		// XML Worker
		XMLWorker worker = new XMLWorker(css, true);
		XMLParser p = new XMLParser(worker);
		p.parse(new ByteArrayInputStream(sb.toString().getBytes()));
		
		
					
		

		
		
		return elements.get(0);

	}
	
	
	
	
}
