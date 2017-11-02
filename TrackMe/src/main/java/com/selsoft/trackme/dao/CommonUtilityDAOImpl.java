package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.CommonUtility;

@Repository
public class CommonUtilityDAOImpl implements CommonUtilityDAO {

	private static final Logger logger = Logger.getLogger(CommonUtilityDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Override
	public List<CommonUtility> getCommonData(Integer code) {

		List<CommonUtility> commonUtilityList = null;

		if (code != null) {

			Query query = new Query(Criteria.where("code").is(code));
			commonUtilityList = template.find(query, CommonUtility.class);
		} else {

			commonUtilityList = template.findAll(CommonUtility.class);
		}

		return commonUtilityList;
	}

	@Override
	public List<CommonUtility> getAllCombinationData(Integer module, Integer submodule, Integer code) {
		
		List<CommonUtility> commonUtilityList = null;
		return commonUtilityList;
	}

}
