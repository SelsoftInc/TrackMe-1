package com.selsoft.trackme.controller;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.User;

public class PdfController {
	
	@RequestMapping(value="/pdf",method=RequestMethod.GET,produces="application/pdf")
	public ResponseEntity<InputStreamResource> downloadPdfFile() throws IOException {
		ClassPathResource pdfFile=new ClassPathResource("D:\rental-billing-statement-template.png");
		Document document = new Document();
	    String input = "D:\rental-billing-statement-template.png:"; // .gif and .jpg are ok too!
	    String output = "D:\rental-billing-statement-template.pdf";
	    try {
	      FileOutputStream fos = new FileOutputStream(output);
	      PdfWriter writer = PdfWriter.getInstance(document, fos);
	      writer.open();
	      document.open();
	      document.add(Image.getInstance(input));
	      document.close();
	      writer.close();
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
	  
				
		HttpHeaders headers=new HttpHeaders();
		/*headers.add("cache Control","no-cache,no-store,must-revalidate");
		headers.add("Pragma","no-cache");
		headers.add("Expires","0");*/
		return  (ResponseEntity<InputStreamResource>) ResponseEntity.ok().headers(headers).contentLength(pdfFile.contentLength()).contentType(MediaType.parseMediaType("application-octet-stream")).body(new InputStreamResource(pdfFile.getInputStream()));
		
	}

}
