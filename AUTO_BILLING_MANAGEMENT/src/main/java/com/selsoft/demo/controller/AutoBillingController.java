package com.selsoft.demo.controller;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.selsoft.demo.model.Lease;
import com.selsoft.demo.service.AutoBillingService;

@RestController
@RequestMapping(value = "/auto")
public class AutoBillingController {
	
	private static final Logger logger = Logger.getLogger(AutoBillingController.class);

	@Autowired
	private AutoBillingService autoBillingService ;
	@RequestMapping(value = "getAllRecords/{endDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Lease> getAllRecords(@PathVariable("endDate") String endDate) {
		return autoBillingService.getAllRecords(endDate);
	}

}

