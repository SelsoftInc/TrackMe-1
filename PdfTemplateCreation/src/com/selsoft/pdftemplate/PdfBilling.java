package com.selsoft.pdftemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
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
	public static final String DEST = "result/biling.pdf";
	public static final String CSS = "table {border-collapse: collapse; width: 100%;}" + "tr { text-align: left; } "
			+ "th { color: white; background-color:  #2b3a76; padding: 2px; } "
			+ "td {font-size:15px; background-color: white;  padding: 2px; border-bottom: 1px solid #d7e8f9; }"
			+ "tr.balance td {background-color:  #d7e8f9;border-top: 1px solid #000;}" + ".nocss {}";

	public static void main(String[] args) throws IOException, DocumentException {
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new PdfBilling().createPdf(DEST);
	}

	/**
	 * Creates a PDF with the words "Hello World"
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
		// step 4
		document.add(getHead(new StringBuilder("<div><b>Account Activity</b></div>")));
		PdfPTable table = getTable();
		PdfPTable table2 = getFootTable();
		document.add(table);
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

	public Element getHead(StringBuilder head) throws IOException {
		Element element = getElements(head);
		return element;
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
			sb.append("--");
			sb.append("</td>");
			sb.append("<td style=\"text-align: center;\">");
			sb.append("--");
			sb.append("</td>");
			sb.append("<td>");
			sb.append("--");
			sb.append("</td>");
			sb.append("<td style=\"text-align: right;\">");
			sb.append("--");
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
				"<th style=\"text-align: right;background-color:white;color:black;\" width=\"30%\">STATEMENT DATE</th>");
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
		sb.append("-");
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
