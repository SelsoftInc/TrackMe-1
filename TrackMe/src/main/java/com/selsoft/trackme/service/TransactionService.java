package com.selsoft.trackme.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

public interface TransactionService {

	public void saveTransaction(Transaction transaction);

	public List<Transaction> getTransaction(String transactionId);

	public ValidError validateTransactionData(Transaction transaction);

	public List<Transaction> getTransactionsForProperty(String propertyId, Date fromDate, Date toDate);

	public List<Transaction> getTransactionReport(String reportType, int year, String duration) throws Throwable;

	//public String getFilePathByID(String transactionId) throws IOException;

	//public File getFileByID(String transactionId);

	public String getFileNameById(String transactionId);
}
