package com.selsoft.tenant.constants;

public class TenantConstants {
	
	public static final String SUCCESS = "success";
	public static final String IRRECOVERABLE_ERROR = "Irrecoverable error occurred, please contact helpdesk for assistance";
	public static final int TIMEOUT = 20;
	
	public enum TENANT_STATUS {
		

       NEW("NEW"), ACTIVE("ACTIVE"), INACTIVE("INACTIVE");
		private final String tenantStatusValue;
		
		private TENANT_STATUS(String value) {
			tenantStatusValue = value;
		}
		
		public boolean equalsName(String value) {
	        return tenantStatusValue.equals(value);
	    }

	    public String toString() {
	       return this.tenantStatusValue;
	    }
	    
	    public String getValue() {
	    	return this.tenantStatusValue;
	    }
	}
	
}
