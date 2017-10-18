package com.selsoft.pdftemplate;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	public static final String DEST = "C:/temp/biling.pdf";
	public static final String CSS = "table {border-collapse: collapse; width: 100%;}" + "tr { text-align: left; } "
			+ "th { color: white; background-color:  #2b3a76; padding: 2px; } "
			+ "td {font-size:15px; background-color: white;  padding: 2px; border-bottom: 1px solid #d7e8f9; }"
			+ "tr.balance td {background-color:  #d7e8f9;border-top: 1px solid #000;}" + ".nocss {}";

	/**
	 * Creates a PDF with the given png file"
	 * 
	 * @param company
	 * 
	 * @param file
	 * @throws IOException
	 * @throws DocumentException
	 */
	public void createPdf(RentalPdf rentalPdf) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
		// step 3
		document.open();

		StringBuilder sb = new StringBuilder();
		sb.append("<div align=\"center\"><b>Rental Company</b></div>");
		Customer customer = rentalPdf.getCustomer();
		Properties prop = rentalPdf.getProperties();
		Company company = rentalPdf.getCompany();
		Statement statement = rentalPdf.getStatement();

		PdfPTable table3 = getHeadTable(company, customer, statement, prop);
		document.add(Chunk.NEWLINE);
		document.add(table3);

		// step 4
		document.add(getHead(new StringBuilder("<div><b>Account Activity</b></div>")));
		PdfPTable table = getTable(rentalPdf.getActivityList());
		PdfPTable table2 = getFootTable(company, statement, customer);

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

		StringBuilder builder = new StringBuilder();
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

	public PdfPTable getHeadTable(Company company, Customer cust, Statement statement, Properties prop)
			throws IOException {

		StringBuilder sb2 = new StringBuilder();
		sb2.append("<table>");
		sb2.append("<tr>");
		sb2.append("<th style=\"text-align: left;background-color:white;color:black;\" width=\"50%\">");
		sb2.append("[Rental Company]:");
		sb2.append("</th>");
		sb2.append(
				"<th style=\"text-align: right;background-color:white;color:blue;\" width=\"90%\"><b>STATEMENT</b> </th>");
		sb2.append("<th style=\"text-align: center;background-color:white;color:black;\" width=\"20%\"></th>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append(company.getStreetAddress());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("");
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb2.append("");
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append(company.getCity() + "," + company.getState() + " " + company.getZip());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("Statement Date");
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb2.append(statement.getStatementDate());
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append(company.getPhone());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("CUSTOMER ID");
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;border: 1px solid #000;\">");
		sb2.append(cust.getId());
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td>");
		sb2.append("--");
		sb2.append("</td>");
		sb2.append("<td>");
		sb2.append("--");
		sb2.append("</td>");
		sb2.append("<td>");
		sb2.append("--");
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append("Bill To: " + cust.getName());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("Property: " + prop.getAddress());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb2.append(" ");
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append(cust.getAddress());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append(prop.getCity() + "," + prop.getState() + " " + prop.getZip());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb2.append("  ");
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\" >");
		sb2.append(cust.getCity() + "," + cust.getState() + " " + cust.getZip());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("Contract From: " + prop.getContractFrom());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb2.append("  ");
		sb2.append("</td>");
		sb2.append("</tr>");

		sb2.append("<tr>");
		sb2.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb2.append(cust.getPhone());
		sb2.append("</td>");
		sb2.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb2.append("To: " + prop.getContractTo());
		sb2.append("</td>");
		sb2.append("   ");
		sb2.append("</tr>");

		sb2.append("</table>");

		return (PdfPTable) getElements(sb2);

	}

	public PdfPTable getTable(List<Activity> activity) throws IOException {

		StringBuilder sb3 = new StringBuilder();
		sb3.append("<table>");
		sb3.append("<tr>");
		sb3.append("<th style=\"text-align: center;\" width=\"20%\">DATE</th>");
		sb3.append("<th style=\"text-align: center;\" width=\"15%\">REF</th>");
		sb3.append("<th width=\"45%\">DESCRIPTION</th>");
		sb3.append("<th width=\"20%\">AMOUNT</th>");
		sb3.append("</tr>");

		for (Activity act : activity) {
			sb3.append("<tr>");
			sb3.append("<td style=\"text-align: center;\">");
			sb3.append(act.getDate());
			sb3.append("</td>");
			sb3.append("<td style=\"text-align: center;\">");
			sb3.append(act.getRef());
			sb3.append("</td>");
			sb3.append("<td>");
			sb3.append(act.getDesc());
			sb3.append("</td>");
			sb3.append("<td style=\"text-align: right;\">");
			sb3.append(act.getAmount());
			sb3.append("</td>");
			sb3.append("</tr>");

		}

		for (int i = 0; i < 10;) {
			i++;
			sb3.append("<tr>");
			sb3.append("<td style=\"text-align: center;\">");
			sb3.append(" -- ");
			sb3.append("</td>");
			sb3.append("<td style=\"text-align: center;\">");
			sb3.append(" -- ");
			sb3.append("</td>");
			sb3.append("<td>");
			sb3.append(" -- ");
			sb3.append("</td>");
			sb3.append("<td style=\"text-align: right;\">");
			sb3.append(" -- ");
			sb3.append("</td>");
			sb3.append("</tr>");
		}

		sb3.append("<tr class=\"balance\">");
		sb3.append("<td>");
		sb3.append("</td>");
		sb3.append("<td>");
		sb3.append("</td>");
		sb3.append("<td style=\"text-align: right;\">");
		sb3.append("<b>BALANCE DUE</b>");
		sb3.append("</td>");
		sb3.append("<td border=\"1\" style=\"background-color: #a4cef7;text-align: right;\">");
		sb3.append("$ 1,020.993");
		sb3.append("</td>");
		sb3.append("</tr>");

		sb3.append("</table>");

		return (PdfPTable) getElements(sb3);

	}

	public PdfPTable getFootTable(Company company, Statement statement, Customer customer) throws IOException {

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
		sb.append(company.getCompanyName());
		sb.append("</td>");
		sb.append("<td style=\"text-align: right;border-bottom: 0px;\">");
		sb.append(customer.getId());
		sb.append("</td>");
		sb.append("<td style=\"text-align: center;border-bottom: 0px;\">");
		sb.append("[ABC123]");
		sb.append("</td>");
		sb.append("</tr>");

		sb.append("<tr>");
		sb.append("<td style=\"text-align: left;border-bottom: 0px;\">");
		sb.append(company.getStreetAddress());
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
		sb.append(customer.getCity() + "," + customer.getState() + " " + customer.getZip());
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
