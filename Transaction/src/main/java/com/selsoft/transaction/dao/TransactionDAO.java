package com.selsoft.transaction.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.selsoft.transaction.model.Transaction;


public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);
	List<Transaction> getTransaction( int transactionId);
	public List<Transaction> getTransactionsForProperty(int propertyId, Date fromDate, Date toDate);
	public List<Transaction> getTransactionReport(String reportType, String duration);
	List<Transaction> getTransactionReport(List<String> dates) throws ParseException;

}