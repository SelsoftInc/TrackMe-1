package com.selsoft.trackme.service;

import java.util.List;

import com.selsoft.trackme.model.Transaction;

public interface TransactionService {

	public void saveTransaction(Transaction transaction);

	public List<Transaction> getTransaction(long transactionId);

}
