package com.selsoft.trackme.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.pdftemplate.RentalPdf;

@Repository
public class PdfDAOImpl implements PdfDAO {

	private static final Logger logger = Logger.getLogger(PdfDAOImpl.class);

	private MongoTemplate template;

	@Override
	public Errors createPdf(RentalPdf rentalPdf) {

		template.save(rentalPdf);
		logger.info("RentalPdf " + rentalPdf.getCustomer() + " Saved in Database");
		return null;

	}

}
