package com.selsoft.tenant.model;

import com.selsoft.tenant.utils.TenantException;

public class Error {
	
	private String errorType;
	private String error;
	
	public Error(String errorType, String error) {
		this.errorType = errorType;
		this.error = error;
	}
		public Error(TenantException e) {
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