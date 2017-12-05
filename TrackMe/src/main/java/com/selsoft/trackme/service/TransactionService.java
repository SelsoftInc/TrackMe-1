package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

public interface TransactionService {

	public void saveTransaction(Transaction transaction);

	public List<Transaction> getTransaction(long transactionId);

	public ValidError validateTransactionData(Transaction transaction);

}
