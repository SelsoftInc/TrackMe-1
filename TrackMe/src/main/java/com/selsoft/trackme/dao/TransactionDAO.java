package com.selsoft.trackme.dao;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.http.ResponseEntity;

import com.selsoft.trackme.model.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);

	List<Transaction> getTransaction(String transactionId);

	public List<Transaction> getTransactionsForProperty(String propertyId, Date fromDate, Date toDate);

	public List<Transaction> getTransactionReport(String reportType, int year, String duration)throws Throwable;
	
	//public File getFileByID(String transactionId) throws IOException;
	
	public String getFileNameById(String transactionId);

	}