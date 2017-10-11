package com.selsoft.trackme.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "TENANT")
public class Tenant {

	// @JsonProperty(required = true)
	private String tenantFirstName;
	// @JsonProperty(required = true)
	private String tenantLastName;
	// @JsonProperty(required = true)
	private String tenantEmailId;
	// @JsonProperty(required = true)
	private String tenantPhoneNumber;
	private String tenantStatus;

	public Tenant(String tenantStatus) {

		this.tenantStatus = TenantStatus.NEW.toString();
		;
	}

	public String getTenantFirstName() {
		return tenantFirstName;
	}

	public void setTenantFirstName(String tenantFirstName) {
		this.tenantFirstName = tenantFirstName;
	}

	public String getTenantLastName() {
		return tenantLastName;
	}

	public void setTenantLastName(String tenantLastName) {
		this.tenantLastName = tenantLastName;
	}

	public String getTenantEmailId() {
		return tenantEmailId;
	}

	public void setTenantEmailId(String tenantEmailId) {
		this.tenantEmailId = tenantEmailId;
	}

	public String getTenantPhoneNumber() {
		return tenantPhoneNumber;
	}

	public void setTenantPhoneNumber(String tenantPhoneNumber) {
		this.tenantPhoneNumber = tenantPhoneNumber;
	}

	public String getTenantStatus() {
		return tenantStatus;
	}

	public void setTenantStatus(String tenantStatus) {
		this.tenantStatus = tenantStatus;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [tenantFirstName=");
		builder.append(tenantFirstName);
		builder.append(", tenantLastName=");
		builder.append(tenantLastName);
		builder.append(", tenantEmailId=");
		builder.append(tenantEmailId);
		builder.append(", tenantPhoneNumber=");
		builder.append(tenantPhoneNumber);
		builder.append(", tenantStatus=");
		builder.append(tenantStatus);
		builder.append("]");
		return builder.toString();
	}

}
