package com.selsoft.trackme.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.selsoft.trackme.dao.CommonUtilityDAO;
import com.selsoft.trackme.model.CommonUtility;

public class CommonUtilityServiceImpl implements CommonUtilityService {

	@Autowired
	private CommonUtilityDAO commonUtilityDAO;

	private static final Logger logger = Logger.getLogger(CommonUtilityServiceImpl.class);
	@Override
	public List<CommonUtility> getCommonData() {
		
		return commonUtilityDAO.getCommonData();
		
	}
	@Override
	public List<CommonUtility> getAllCombinationData(String[] module, String[] submodule, String[] code) {
		
		/*
				public static Set<String> permutationFinder(String str) {
	        Set<String> perm = new HashSet<String>();
	        //Handling error scenarios
	        if (str == null) {
	            return null;
	        } else if (str.length() == 0) {
	            perm.add("");
	            return perm;
	        }
	        char initial = str.charAt(0); // first character
	        String rem = str.substring(1); // Full string without first character
	        Set<String> words = permutationFinder(rem);
	        for (String strNew : words) {
	            for (int i = 0;i<=strNew.length();i++){
	                perm.add(charInsert(strNew, initial, i));
	            }
	        }
	        return perm;
				}
	

	    public static String charInsert(String str, char c, int j) {
	        String begin = str.substring(0, j);
	        String end = str.substring(j);
	        return begin + c + end;
	                                                               }
	}



	

	    
	    logger.info("\nPermutations for " + module + " are: \n" + permutationFinder(module));
	    logger.info("\nPermutations for " + submodule + " are: \n" + permutationFinder(submodule));
	    logger.info("\nPermutations for " + code + " are: \n" + permutationFinder(code));
	    */
		return commonUtilityDAO.getAllCombinationData(module,submodule,code);

		
	}

}

