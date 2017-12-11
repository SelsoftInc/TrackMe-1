package com.selsoft.trackme.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selsoft.trackme.dto.PasswordDto;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;

import ch.qos.logback.core.net.SyslogOutputStream;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestParam("transaction") MultipartFile json,
			@RequestParam("file") MultipartFile file) {
		Transaction transaction = null;
		String content = "";

		try {
			content = new String(json.getBytes());

			if (content.length() > 2 * 1024 * 1024) // 2MB

			{
				logger.info("File is too big");

			}
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

	@RequestMapping(value = "/getTransaction/{transactionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransaction(@PathVariable("transactionid") Integer transactionid) {

		return transactionService.getTransaction(transactionid);
	}

	@RequestMapping(value = "/getTransactionsForProperty/{transactionid}/{fromdate}/{todate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionsForProperty(@PathVariable("propertyid") int propertyId,
			@PathVariable("fromdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable("todate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

			return transactionService.getTransactionsForProperty(propertyId, fromDate, toDate);
	}
	@RequestMapping(value = "/getTransaction/reporttype/{reporttype}/duration/{duration}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionReport(@PathVariable("reportType") String reportType,
			@PathVariable("duration") String duration ) {

		return transactionService.getTransactionReport(reportType, duration);
	}
		

}
