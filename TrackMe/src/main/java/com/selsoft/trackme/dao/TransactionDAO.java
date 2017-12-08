package com.selsoft.trackme.dao;

import java.util.Date;
import java.util.List;

import com.selsoft.trackme.model.CommonUtility;
import com.selsoft.trackme.model.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);
	List<Transaction> getTransaction( int transactionId);
	public List<Transaction> getTransactionsForProperty(int propertyId, Date fromDate, Date toDate);

}
