package com.selsoft.trackme.dao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.Owner;
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
		
	
	}

