package com.selsoft.commonutility.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.model.Owner;
import com.selsoft.commonutility.model.Property;
import com.selsoft.commonutility.model.Transaction;
import com.selsoft.commonutility.utils.CommonUtilityException;

@Repository
public class CommonUtilityDAOImpl implements CommonUtilityDAO {

	private static final Logger logger = Logger.getLogger(CommonUtilityDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public List<CommonUtility> getCommonData(CommonUtility commonUtility) {

		String module = commonUtility.getModule();
		String subModule = commonUtility.getSubmodule();
		String code = commonUtility.getCode();
		List<CommonUtility> commonUtilityList = null;
		Query query = null;
		if (module != null && subModule != null && code != null) {
			query = new Query(Criteria.where("module").is(commonUtility.getModule()).and("submodule")
					.is(commonUtility.getSubmodule()).and("code").is(commonUtility.getCode()));

		} else if (module != null && subModule != null && code == null) {
			query = new Query(Criteria.where("module").is(commonUtility.getModule()).and("submodule")
					.is(commonUtility.getSubmodule()));

		} else if (module != null && subModule == null && code == null) {
			query = new Query(Criteria.where("module").is(commonUtility.getCode()));

		} else if (module == null && subModule != null && code == null) {
			query = new Query(Criteria.where("submodule").is(commonUtility.getSubmodule()));

		} else if (module == null && subModule == null && code != null) {
			query = new Query(Criteria.where("code").is(commonUtility.getModule()));

		}

		commonUtilityList = template.find(query, CommonUtility.class);
		return commonUtilityList;
	}

	@Override
	public long activeOwners(String managerId) throws CommonUtilityException {
		long activeOwnerCount = 0;
		try{
		Query query = new Query(Criteria.where("managerId").is(managerId).and("ownerStatus").in("New"));
		activeOwnerCount = template.count(query, Owner.class);
		}catch(Throwable t){
			throw new CommonUtilityException("Fatal", t);
		}
		return activeOwnerCount;
	}

	@Override
	public long totalNoOfProperties(String managerId) throws CommonUtilityException {
		long totalNoOfProperties = 0;
		try{
		Query query1 = new Query(
				Criteria.where("managerId").is(managerId).and("propertyStatus").in("Active", "Occupied"));
		totalNoOfProperties = template.count(query1, Property.class);
		}catch(Throwable t){
			throw new CommonUtilityException("Fatal", t);
		}
		return totalNoOfProperties;
	}

	@Override
	public long totalNoOfOccupiedProperties(String managerId) throws CommonUtilityException {
		long totalNoOfActiveProperties = 0;
		try{
		Query query2 = new Query(Criteria.where("managerId").is(managerId).and("propertyStatus").is("Occupied"));
		totalNoOfActiveProperties = template.count(query2, Property.class);
		}catch(Throwable t){
			throw new CommonUtilityException("Fatal", t);
		}
		
		return totalNoOfActiveProperties;
	}

	@Override
	public long totalNoOfVacantProperties(String managerId) throws CommonUtilityException {
		long totalNoOfVacantProperties = 0;
		try{
		Query query3 = new Query(Criteria.where("managerId").is(managerId).and("propertyStatus").is("Active"));
		totalNoOfVacantProperties = template.count(query3, Property.class);
		}catch(Throwable t){
			throw new CommonUtilityException("Fatal", t);
		}
		return totalNoOfVacantProperties;
	}

	@Override
	public double totalRentCollected(String managerId, String year) throws CommonUtilityException {
		Query query4 = new Query();
		Calendar calendar = Calendar.getInstance();
		int curYear = calendar.get(Calendar.YEAR);
		String fromDate = curYear + "-01-01";
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String toDate = dateFormat.format(date);
           try{         
		query4.addCriteria(Criteria.where("managerId").is(managerId).and("paidOn").gte(fromDate).lte(toDate)
				.and("transactionType").is("Income").and("transactionCode").is("Rent"));
		List<Transaction> transactionList = template.find(query4, Transaction.class);
           
		double totalAmount = 0;

		for (Transaction transaction : transactionList) {
			double amount = Double.parseDouble(transaction.getAmount());
			totalAmount += amount;
		}
           
		return totalAmount;
           }catch(Throwable t){
        	   throw new CommonUtilityException("Fatal", t);
           }
	}

	@Override
	public double totalExpense(String managerId, String year) throws CommonUtilityException {
		Query query5 = new Query();
		Calendar calendar = Calendar.getInstance();
		int curYear = calendar.get(Calendar.YEAR);
		String fromDate = curYear + "-01-01";
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String toDate = dateFormat.format(date);
         try{
		query5.addCriteria(Criteria.where("managerId").is(managerId).and("paidOn").gte(fromDate).lte(toDate)
				.and("transactionType").is("Expense"));
		List<Transaction> transList = template.find(query5, Transaction.class);
		double totalAmount = 0;
		for (Transaction transaction : transList) {
			double amount = Double.parseDouble(transaction.getAmount());
			totalAmount += amount;
		}
		return totalAmount;
         }catch(Throwable t){
        	 throw new CommonUtilityException("Fatal", t);
         }
	}

}
