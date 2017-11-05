package com.selsoft.trackme.service;

import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

public interface TransactionService {
	
	public void saveTransaction(Transaction transaction);
	public ValidError validateTransactionData(Transaction transaction);
}

