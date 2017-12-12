package com.selsoft.trackme.model;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document(collection = "TRANSACTION")
public class Transaction {

	@Id
	private String transactionId;
	private int propertyId;
	private long ownerId;
	private long managerId;
	private double amount;
	private String transactionType;
	private String transactionCode;
	private int paidById;
	private String paymentMode;
	private Date paidOn;
	private String category;
	private Date enteredOn;
	private byte[] file;
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public int getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
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
	public Date getPaidOn() {
		return paidOn;
	}
	public void setPaidOn(Date paidOn) {
		this.paidOn = paidOn;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getEnteredOn() {
		return enteredOn;
	}
	public void setEnteredOn(Date enteredOn) {
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
		return "Transaction [transactionId=" + transactionId + ", propertyId=" + propertyId + ", ownerId=" + ownerId
				+ ", managerId=" + managerId + ", amount=" + amount + ", transactionType=" + transactionType
				+ ", transactionCode=" + transactionCode + ", paidById=" + paidById + ", paymentMode=" + paymentMode
				+ ", paidOn=" + paidOn + ", category=" + category + ", enteredOn=" + enteredOn + ", file="
				+ Arrays.toString(file) + "]";
	}

}
