package com.selsoft.trackme.model;

/**
 * 
 * @author Sudhanshu Barik
 *
 */
public enum PropertyStatus {
	
	/*
	 * NEW - New property in market. Tenant cannot be assigned to this property until it is made active
     *ACTIVE - Rental Manager will update the property as ACTIVE once it is ready to be occupied. Only ACTIVE properties can be assigned to tenants.
     *OCCUPIED - Currently occupied by a tenant.
     *MAINTENANCE - Currently no one resides in this house due to some maintenance activity
     *INACTIVE - Currently inactive in market due to some reason   
	  
	 */
	
	
	
	NEW, ACTIVE, OCCUPIED, MAINTENANCE, INACTIVE;//

}
