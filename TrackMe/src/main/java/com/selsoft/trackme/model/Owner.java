package com.selsoft.trackme.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OWNER")
public class Owner {
	private int ownerId;
	private String ownerFirstName;
	private String ownerLastName;
	private String ownerStatus;
	private int emailId;
	private int ownerPhoneNumber;

	public Owner() {
		this.ownerStatus = OwnerStatus.NEW.toString();
	}

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
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}

	public int getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(int ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

}