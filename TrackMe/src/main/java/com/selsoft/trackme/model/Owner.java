package com.selsoft.trackme.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OWNER")
public class Owner {
	private int ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerStatus;
	private int EmailId;
	private int ownerPhoneNumber;

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getOwnerFirstName() {
		return ownerFirstName;
	}

	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}

	public String getOwnerLastName() {
		return ownerLastName;
	}

	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}

	public String getOwnerStatus() {
		return ownerStatus;
	}

	public void setOwnerStatus(String ownerStatus) {
		this.ownerStatus = ownerStatus;
	}

	public int getEmailId() {
		return EmailId;
	}

	public void setEmailId(int emailId) {
		EmailId = emailId;
	}

	public int getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(int ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewOwner [ownerId=");
		builder.append(ownerId);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", ownerStatus=");
		builder.append(ownerStatus);
		builder.append(", EmailId=");
		builder.append(EmailId);
		builder.append(", ownerPhoneNumber=");
		builder.append(ownerPhoneNumber);
		builder.append("]");
		return builder.toString();
	}

}
