package com.selsoft.commonutility.utils;

import org.apache.commons.lang3.StringUtils;

public class CommonUtilityException extends Exception{
	
	private static final long serialVersionUID = -828218869553448583L;

	private static final String IRRECOVERABLE_ERROR = "Cannot get the dashboard details";
	
	private String errorType;
	private String error;
	
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

	public CommonUtilityException (){}
	
	public CommonUtilityException (String errorType, String error) {
		this.errorType = errorType;
		this.error = error;
	}
	
	public CommonUtilityException (String errorType, Throwable t) {
		this.errorType = errorType;
		this.error = (t != null ? (StringUtils.isNotBlank(t.getMessage()) ? t.getMessage() : 
									(StringUtils.isNotBlank(t.getLocalizedMessage()) ? t.getLocalizedMessage() : IRRECOVERABLE_ERROR))
								: IRRECOVERABLE_ERROR);
	}

	@Override
	public String toString() {
		return ("Error Type : " + this.getErrorType() + ", Error : " + this.getError());
	}

}


