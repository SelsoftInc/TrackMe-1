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
	private int transactionId;
	private int propertyId;
	private long ownerId;
	private long tenantId;
	private long propertyManagerId;
	private double amount;
	private String transactionType;
	private String paidBy;
	private int paidById;
	private String paymentMode;
	private Date paidOn;
	private String transactionDesc;
	private String category;
	private byte[] file;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
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

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public long getPropertyManagerId() {
		return propertyManagerId;
	}

	public void setPropertyManagerId(long propertyManagerId) {
		this.propertyManagerId = propertyManagerId;
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

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
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

	public String getTransactionDesc() {
		return transactionDesc;
	}

	public void setTransactionDesc(String transactionDesc) {
		this.transactionDesc = transactionDesc;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
				+ ", tenantId=" + tenantId + ", propertyManagerId=" + propertyManagerId + ", amount=" + amount
				+ ", transactionType=" + transactionType + ", paidBy=" + paidBy + ", paidById=" + paidById
				+ ", paymentMode=" + paymentMode + ", paidOn=" + paidOn + ", transactionDesc=" + transactionDesc
				+ ", category=" + category + ", file=" + Arrays.toString(file) + "]";
	}

	

}
