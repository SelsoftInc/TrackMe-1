package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.CommonUtility;


@Repository
public class CommonUtilityDAOImpl implements CommonUtilityDAO {

	private static final Logger logger = Logger.getLogger(CommonUtilityDAOImpl.class);

	@Autowired
	private MongoTemplate template;
	
	 @Autowired
	 MongoOperations mongoOperations;
	@Override
	public List<CommonUtility> getCommonData() {

		List<CommonUtility> commonUtilityList = null;
			
		commonUtilityList = template.findAll(CommonUtility.class);
		
		return commonUtilityList;
	}

	@Override
	public List<CommonUtility> getAllCombinationData(String[] module, String[] submodule, String[] code) {
		
		List<CommonUtility> commonUtilityList = null;
		
		
		
		
		//List<CommonUtility> employees = mongoOperations.getCollection("commontransactiondata", CommonUtility.class);	
		return commonUtilityList;
	}

}
