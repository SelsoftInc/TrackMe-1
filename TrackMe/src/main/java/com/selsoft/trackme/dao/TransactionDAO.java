package com.selsoft.trackme.dao;

import java.util.Date;
import java.util.List;
import com.selsoft.trackme.model.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);

	List<Transaction> getTransaction(String transactionId);

	public List<Transaction> getTransactionsForProperty(String propertyId, Date fromDate, Date toDate);

	public List<Transaction> getTransactionReport(String reportType, int year, String duration)throws Throwable;

	}