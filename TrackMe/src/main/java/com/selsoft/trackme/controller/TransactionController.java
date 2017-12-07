package com.selsoft.trackme.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestParam("transaction") MultipartFile json, @RequestParam("file") MultipartFile file) {
		Transaction transaction=null;
		String content="";
		
		try {
			 content = new String(json.getBytes());
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	    
		ObjectMapper mapper = new ObjectMapper();
		try {
			transaction = mapper.readValue(content, Transaction.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		logger.info(transaction.getTransactionId()
				+ " data comes into TransactionController saveTransaction() for processing");
		try {
			transaction.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		ValidError validError = transactionService.validateTransactionData(transaction);
		if (validError == null) {
			transactionService.saveTransaction(transaction);
		}

	}

	@RequestMapping(value = "/ getTransaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransaction(long transactionId) {
		return transactionService.getTransaction(transactionId);
	}

	@RequestMapping(value = "/ getTransactionsForProperty", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionsForProperty(int propertyId) {
		return transactionService.getTransactionsForProperty(propertyId);
	}

}
