package com.selsoft.transaction.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.selsoft.transaction.model.Transaction;
import com.selsoft.transaction.model.ValidError;
import com.selsoft.transaction.service.TransactionService;


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

		ValidError validError=  transactionService.validateTransactionData(transaction);
		if(validError==null) {
			transactionService.saveTransaction(transaction);
		}

	}

}
