package com.selsoft.commonutility.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.selsoft.commonutility.model.CommonUtility;
import com.selsoft.commonutility.service.CommonUtilityService;
import com.selsoft.commonutility.utils.CommonUtilityException;


@SuppressWarnings({ "unused" })
@RestController
@RequestMapping(value = "/common")
public class CommonUtilityController {
	private static final Logger logger = Logger.getLogger(CommonUtilityController.class);
	@Autowired
	private CommonUtilityService commonUtilityService;

	@RequestMapping(value = "getCommonData", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommonUtility> getCommonData(@RequestBody CommonUtility commonUtility) {
		return commonUtilityService.getCommonData(commonUtility);
	}

	@RequestMapping(value = "/getDashboardDataForManager/{managerId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String getDashboardDataForManager(@PathVariable("managerId") String managerId) throws JSONException {
		
		JSONObject jsonObject = commonUtilityService.getDashboardDataForManager(managerId);

		return jsonObject.toString();
	}
}
