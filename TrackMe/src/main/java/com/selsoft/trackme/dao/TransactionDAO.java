package com.selsoft.trackme.dao;

import java.util.List;

import com.selsoft.trackme.model.CommonUtility;
import com.selsoft.trackme.model.Transaction;

public interface TransactionDAO {

	public void saveTransaction(Transaction transaction);
	List<Transaction> getTransaction( long transactionId);
	public List<Transaction> getTransactionsForProperty(int propertyId);

}
