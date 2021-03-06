package com.selsoft.trackme.utils;

import org.apache.commons.lang3.StringUtils;

public class TransactionException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -828218869553448583L;

	private static final String IRRECOVERABLE_ERROR = "Irrecoverable error occurred, please contact helpdesk for assistance";
	
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

	public TransactionException(){}
	
	public TransactionException(String errorType, String error) {
		this.errorType = errorType;
		this.error = error;
	}
	
	public TransactionException(String errorType, Throwable t) {
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