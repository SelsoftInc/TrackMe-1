package com.selsoft.trackme.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selsoft.trackme.model.CommonUtility;
import com.selsoft.trackme.model.Property;

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

}
