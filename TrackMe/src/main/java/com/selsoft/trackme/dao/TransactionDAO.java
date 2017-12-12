package com.selsoft.trackme.dao;

import java.util.Date;
import java.util.List;
import com.selsoft.trackme.model.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);

	List<Transaction> getTransaction(int transactionId);

	public List<Transaction> getTransactionsForProperty(int propertyId, Date fromDate, Date toDate);

	public List<Transaction> getTransactionReport(String reportType, String year, String duration);

	public List<Transaction> getTransactionReportYearly(String year);
	
	public List<Transaction> getTransactionReport(String reportType, int year, String duration)throws Throwable;


}