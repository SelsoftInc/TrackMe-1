package com.selsoft.trackme.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selsoft.trackme.model.CommonUtility;
import com.selsoft.trackme.service.CommonUtilityService;

@SuppressWarnings({ "unused" })
@RestController
@RequestMapping(value = "/common")
public class CommonUtilityController {
	private static final Logger logger = Logger.getLogger(CommonUtilityController.class);
	@Autowired
	private CommonUtilityService commonUtilityService;

	@RequestMapping(value = "getCommonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommonUtility> getCommonData() {
		return commonUtilityService.getCommonData();
	}

	@RequestMapping(value = "getAllCombinationData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CommonUtility> getAllCombinationData(@PathVariable("module") String[]  module,@PathVariable("submodule ") String[] submodule,@PathVariable("code") String[] code) {
		return commonUtilityService.getAllCombinationData(module,submodule,code);
	}

	
}
