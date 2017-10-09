package com.selsoft.trackme.model;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection = "PROPERTY")
public class Property {

	@JsonProperty(required = true)
	private String propertyName;
	@JsonProperty(required = true)
	private String address1;
	private String address2;
	@JsonProperty(required = true)
	private String city;
	@JsonProperty(required = true)
	private String state;
	@JsonProperty(required = true)
	private String zipCode;
	private String ownerFirstName;
	private String ownerLastName;
	@JsonProperty(required = true)
	private int ownerid;
	@JsonProperty(required = true)
	private RentalDetail rentalDetail;

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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

	public int getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public RentalDetail getRentalDetail() {
		return rentalDetail;
	}

	public void setRentalDetail(RentalDetail rentalDetail) {
		this.rentalDetail = rentalDetail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Property [propertyName=");
		builder.append(propertyName);
		builder.append(", address1=");
		builder.append(address1);
		builder.append(", address2=");
		builder.append(address2);
		builder.append(", city=");
		builder.append(city);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipCode=");
		builder.append(zipCode);
		builder.append(", ownerFirstName=");
		builder.append(ownerFirstName);
		builder.append(", ownerLastName=");
		builder.append(ownerLastName);
		builder.append(", ownerid=");
		builder.append(ownerid);
		builder.append(", rentalDetail=");
		builder.append(rentalDetail);
		builder.append("]");
		return builder.toString();
	}

}