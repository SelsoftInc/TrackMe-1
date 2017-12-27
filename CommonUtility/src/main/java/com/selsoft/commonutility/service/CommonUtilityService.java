package com.selsoft.commonutility.service;
import java.util.List;
import org.json.JSONObject;
import com.selsoft.commonutility.model.CommonUtility;

public interface CommonUtilityService {

	List<CommonUtility> getCommonData(CommonUtility commonUtility);

	JSONObject getDashboardDataForManager(String managerId);

}

