package com.selsoft.commonutility.model;

import com.selsoft.commonutility.utils.CommonUtilityException;

public class Error {
	private String errorType;
	private String error;
	
	public Error(String errorType, String error) {
		this.errorType = errorType;
		this.error = error;
	}
	
	public Error(CommonUtilityException e) {
		this.errorType = e.getErrorType();
		this.error = e.getError();
	}
	
	public String getErrorType() {
		return errorType;
	}
	
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}
	
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	

}
