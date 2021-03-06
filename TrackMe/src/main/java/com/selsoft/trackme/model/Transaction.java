package com.selsoft.trackme.model;

import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "TRANSACTION")
public class Transaction {

	@Id
	private String transactionId;
	private String propertyId;
	private String ownerId;
	private String managerId;
	private String amount;
	private String transactionType;
	private String transactionCode;
	private int paidById;
	private String paidOn;
	private String category;
	private String enteredOn;
	private String filePath;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public int getPaidById() {
		return paidById;
	}
	public void setPaidById(int paidById) {
		this.paidById = paidById;
	}
	public String getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(String paidOn) {
		this.paidOn = paidOn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getEnteredOn() {
		return enteredOn;
	}
	public void setEnteredOn(String enteredOn) {
		this.enteredOn = enteredOn;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", propertyId=");
		builder.append(propertyId);
		builder.append(", ownerId=");
		builder.append(ownerId);
		builder.append(", managerId=");
		builder.append(managerId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", transactionCode=");
		builder.append(transactionCode);
		builder.append(", paidById=");
		builder.append(paidById);
		builder.append(", paidOn=");
		builder.append(paidOn);
		builder.append(", category=");
		builder.append(category);
		builder.append(", enteredOn=");
		builder.append(enteredOn);
		builder.append(", filePath=");
		builder.append(filePath);
		builder.append("]");
		return builder.toString();
	}
	
		}