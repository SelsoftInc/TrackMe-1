package com.selsoft.trackme.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.PdfDAO;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.ValidError;

@Service("leaseService")
public class LeaseServiceImpl implements LeaseService{
	
	@Autowired
	PdfDAO pdfDAO;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PdfService.class);

	@Override
	public Errors priorDataValidation(Lease lease) {
		
		 if ("NEW".equals(lease.getPropertyStatus())) {
			// logger.info("Property needs to be activated before assigning to a tenan:");
			 //need to put error
				ValidError validError = new ValidError("ERROR100", "user type new");
				List<ValidError> errorList = new ArrayList<>();
				errorList.add(validError);
				return new Errors(errorList);

		  }
		
		 if ("OCCUPIED".equals(lease.getPropertyStatus())) {
			// logger.info("Property needs to be activated before assigning to a tenan:");
			 ValidError validError = new ValidError("ERROR101", "user type occupid");
				List<ValidError> errorList = new ArrayList<>();
				errorList.add(validError);
				return new Errors(errorList);


		  }
		 if ("NEW".equals(lease.getPropertyStatus())) {
			 logger.info("Property needs to be activated before assigning to a tenan:");

		  }
		 if ("NEW".equals(lease.getPropertyStatus())) {
			 logger.info("Property needs to be activated before assigning to a tenan:");

		  }
		return null;
		 
	}
	
	

}
