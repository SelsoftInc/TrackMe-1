package com.selsoft.tenant.model;

public enum TENANT_STATUS {
	
	
	NEW("NEW"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
	
	private final String value;
	 private TENANT_STATUS(final String value) {
		 this.value = value;
		 }
	 public String getValue() {
		 return value; 
		 }
}
