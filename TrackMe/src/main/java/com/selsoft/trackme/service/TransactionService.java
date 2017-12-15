package com.selsoft.trackme.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

public interface TransactionService {

	public void saveTransaction(Transaction transaction);

	public List<Transaction> getTransaction(String transactionId);

	public ValidError validateTransactionData(Transaction transaction);

	public List<Transaction> getTransactionsForProperty(String propertyId, Date fromDate, Date toDate);

	public List<Transaction> getTransactionReport(String reportType, int year, String duration) throws Throwable;

	public Response downloadFilebyID(String transactionId) throws IOException;
	
	
}
