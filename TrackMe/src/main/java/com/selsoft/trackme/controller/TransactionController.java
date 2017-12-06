package com.selsoft.trackme.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	// private static TransactionType transactionType = new TransactionType();

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestBody Transaction transaction, @RequestParam("file") MultipartFile file) {
		/*BufferedInputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(file.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.info(transaction.getTransactionId()
				+ " data comes into TransactionController saveTransaction() for processing");
		try {
			transaction.setFile(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	public List<Transaction> getTransactionsForProperty(int  propertyId) {
		return transactionService.getTransactionsForProperty(propertyId);
	}

}
