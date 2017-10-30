package com.selsoft.trackme.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.constants.ErrorConstants;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.LeaseService;

@RestController
@RequestMapping(value = "/lease")
public class LeaseController {

	private static final Logger logger = Logger.getLogger(LeaseController.class);

	//private static LeaseType leaseType = new LeaseType();

	@Autowired
	private LeaseService leaseService;

	@RequestMapping(value = "/createLease", method = RequestMethod.POST)
	public ValidError createLease(@RequestBody Lease lease) {

		ValidError error = leaseService.validateNewLeaseData(lease);

		logger.info(lease.getPropertyId() + " data comes into LeaseControllercreateLease() for processing");
		if (error == null) {
			leaseService.createLease(lease);
			error=new ValidError(ErrorConstants.ERROR109, ErrorConstants.ERRROR109_MESSAGE);
		}
		return error;

	}
	
	
	 //------------------- save RentalDetail --------------------------------------------------------
		@RequestMapping(value = "/saveRentalDetail", method = RequestMethod.PUT)
		public void  saveRentalDetail(@RequestBody RentalDetail rentalDetail,@RequestParam("pId") String propertyId) {
			
			ValidError validError = leaseService.validateNewRentalData(rentalDetail);
			
			
		}
		/*	
		@RequestMapping(value = "/ getAllRentalDetails ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<RentalDetail>  getAllRentalDetails (String propertyId) {
			return leaseService. getAllRentalDetails(propertyId);
		}
		*/
		
		
		@RequestMapping(value = "/getAllRentalDetails/property/{property} ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		  public List<Property>  getAllRentalDetails (@PathVariable("property") String propertyId){
			return leaseService. getAllRentalDetails(propertyId);
		}
			
		
		@RequestMapping(value = "/ getRentalDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public List<Property>  getRentalDetail(String propertyId,Date effectiveDate) {
			return leaseService. getRentalDetail(propertyId,effectiveDate);
		}
		
		
}
