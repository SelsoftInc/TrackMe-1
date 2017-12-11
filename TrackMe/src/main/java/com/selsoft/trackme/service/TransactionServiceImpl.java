package com.selsoft.trackme.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.selsoft.trackme.constants.ErrorConstants;
import com.selsoft.trackme.dao.TransactionDAO;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;

@Service
@Transactional
public class  TransactionServiceImpl implements TransactionService{
	
	@Autowired
	private TransactionDAO  transactionDAO;

	private static final Logger logger = Logger.getLogger(TransactionServiceImpl.class);

	@Override
	public void saveTransaction(Transaction transaction) {
		transactionDAO.saveTransaction(transaction);
	}

	@Override
	public ValidError validateTransactionData(Transaction transaction) {
		String transactionType = transaction.getTransactionType();
		logger.info(transaction.getTransactionType() + " data comes into LeaseController saveTransaction() for processing");

		if (StringUtils.equals("OWN", transactionType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR110, ErrorConstants.ERRROR110_MESSAGE);
			return validError;

		}

		else if (StringUtils.equals("TNT ", transactionType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR111, ErrorConstants.ERRROR111_MESSAGE);
			return validError;

		} else if (StringUtils.equals("MGR", transactionType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR112, ErrorConstants.ERRROR112_MESSAGE);
			return validError;

		}
				return null;
	}

	@Override
	public List<Transaction> getTransaction(int transactionId) {
		
		return transactionDAO.getTransaction(transactionId);
	}

	@Override
	public List<Transaction> getTransactionsForProperty(int propertyId, Date fromDate, Date toDate) {
		return transactionDAO.getTransactionsForProperty(propertyId,fromDate,toDate);
	}

	@Override
	public List<Transaction> getTransactionReport(String reportType, String duration) {
		return transactionDAO.getTransactionReport(reportType,duration);
	}

		
}




