package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Owner;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.Transaction;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
	
	private static final Logger logger = Logger.getLogger(TransactionDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public void saveTransaction(Transaction transaction) {
		
			template.save(transaction);
		
		}

	@Override
	public List<Transaction> getTransaction(long transactionId) {
      List<Transaction> transactionList = null;
		
		//if (transactionId != null) {
			
		Query query = new Query(Criteria.where("transactionId").is(transactionId));
		transactionList = template.find(query, Transaction.class);
		//}
	//else{
			
			transactionList = template.findAll(Transaction.class);
		//}
		
		return transactionList;
	}
		
	
	}

