package com.selsoft.trackme.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.pdftemplate.RentalPdf;

public interface PdfService {
	
	
	public Errors createPdf() throws IOException, DocumentException;


}
