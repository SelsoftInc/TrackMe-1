package com.selsoft.commonutility.dao;
import java.util.ArrayList;
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
	public List<CommonUtility>getDashboardDataForManager(String managerId) {
       
        List<Object> addDetails=null;
        
        
        try{
        	addDetails=new ArrayList<Object>();
        	Query	query = new Query();
			/*The number of Active owners (Owner table - mangerId = ? and ownerStatus 
			 * in ('New', 'Active'),*/
        	
			
				query.addCriteria(Criteria.where("managerId").is(managerId).and("ownerStatus").is("New").and("ownerStatus").is("Active"));
				List<Owner> ownerList= template.find(query,Owner.class);
				addDetails.add(ownerList);
				 
				 Query query1 = new Query();
				query1.addCriteria(Criteria.where("managerId").is(managerId).and("propertyStatus").is("Active").and("propertyStatus").is("Occupied"));
				List<Property> propertyList= template.find(query,Property.class);
				addDetails.add(propertyList);
				
				Query query2 = new Query();
				query2.addCriteria(Criteria.where("managerId").is(managerId).and("propertyStatus").is("Occupied"));
				List<Property> propList= template.find(query2, Property.class);
				addDetails.add(propList);
				
				Query query3 = new Query();
				query3.addCriteria(Criteria.where("managerId").is(managerId).and("propertyStatus").is("Active"));
				List<Property> propsList= template.find(query3, Property.class);
				addDetails.add(propsList);
				
				Query query4 = new Query();
				query4.addCriteria(Criteria.where("managerId").is(managerId).and("paidOn").gte(fromDate).lte(toDate).and("transactionType").is("Income").and("transactionCode ").is("'Rent"));
				List<Transaction> transactionList = template.find(query4, Transaction.class);
				addDetails.add(transactionList);
				
				Query query5 = new Query();
				query5.addCriteria(Criteria.where("managerId").is(managerId).and("paidOn").gte(fromDate).lte(toDate).and("transactionType").is("Expense"));
               List<Transaction> transList= template.find(query5, Transaction.class);
               addDetails.add(transList);
			}
	
		} catch(Throwable t) {
			logger.error("Error while getting the transaction report between " + paidOn + " and " + enteredOn, t);
			throw new CommonUtilityException("Error", t);
		}
	
		return addDetails;
	}
}
