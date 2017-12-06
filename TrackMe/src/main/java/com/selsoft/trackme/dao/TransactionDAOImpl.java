package com.selsoft.trackme.dao;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = Logger.getLogger(TransactionDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public void saveTransaction(Transaction transaction) {
		try{
		template.save(transaction);
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

	@Override
	public List<Transaction> getTransaction(long transactionId) {
		List<Transaction> transactionList = null;

		 if (transactionId != 0) {

		Query query = new Query(Criteria.where("transactionId").is(transactionId));
		transactionList = template.find(query, Transaction.class);
		 }
		 else{

		transactionList = template.findAll(Transaction.class);
		 }

		return transactionList;
	}

	@Override
	public List<Transaction> getTransactionsForProperty(int propertyId) {
		List<Transaction> transactionList = null;

		 if (propertyId != 0) {

		Query query = new Query(Criteria.where("propertyId").is(propertyId));
		transactionList = template.find(query, Transaction.class);
		 }
		 else{

		transactionList = template.findAll(Transaction.class);
		 }

		return transactionList;
	}

}
