package com.selsoft.trackme.dto;

import java.util.Arrays;
import java.util.Date;

public class TransactionDto {
	
	private String transactionId;
	private String propertyId;
	private String ownerId;
	private String managerId;
	private String amount;
	private String transactionType;
	private String transactionCode;
	private int paidById;
	private String paymentMode;
	private String paidOn;
	private String category;
	private String enteredOn;
	private byte[] file;
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
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
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
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	@Override
	public String toString() {
		return "TransactionDto [transactionId=" + transactionId + ", propertyId=" + propertyId + ", ownerId=" + ownerId
				+ ", managerId=" + managerId + ", amount=" + amount + ", transactionType=" + transactionType
				+ ", transactionCode=" + transactionCode + ", paidById=" + paidById + ", paymentMode=" + paymentMode
				+ ", paidOn=" + paidOn + ", category=" + category + ", enteredOn=" + enteredOn + ", file="
				+ Arrays.toString(file) + "]";
	}
	

}
