package com.selsoft.trackme.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	// private static TransactionType transactionType = new TransactionType();

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestBody Transaction transaction) {

		logger.info(transaction.getTransactionId()
				+ " data comes into TransactionController saveTransaction() for processing");

		//ValidError validError = transactionService.validateNewTransactionData(transaction);

	}

}
