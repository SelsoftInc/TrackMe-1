package com.selsoft.transaction.service;

import com.selsoft.transaction.model.Transaction;
import com.selsoft.transaction.model.ValidError;

public interface TransactionService {
	
	public void saveTransaction(Transaction transaction);
	public ValidError validateTransactionData(Transaction transaction);
}