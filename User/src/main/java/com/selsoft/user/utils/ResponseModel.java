package com.selsoft.user.utils;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseModel {
	
	int STATUS_CODE;
	String STATUS_MSG;
	String message;
	List<?> RESULT;
	String USER_STATUS;
	String OTP;

	public ResponseModel() {

	}

	public ResponseModel(int statusCode, String message, String statusMsg, List<?> result) {
		this.STATUS_CODE = statusCode;
		this.message = message;
		this.STATUS_MSG = statusMsg;
		this.RESULT = result;
	}

	public ResponseModel(int statusCode, String message, String statusMsg, List<?> result, String userStatus) {
		this.STATUS_CODE = statusCode;
		this.message = message;
		this.STATUS_MSG = statusMsg;
		this.RESULT = result;
		this.USER_STATUS = userStatus;
	}

	public ResponseModel(int statusCode, String message, String statusMsg, List<?> result, String userStatus,
			String otp) {
		this.STATUS_CODE = statusCode;
		this.message = message;
		this.STATUS_MSG = statusMsg;
		this.RESULT = result;
		this.USER_STATUS = userStatus;
		this.OTP = otp;
	}

	public List<?> getResult() {
		return RESULT;
	}

	@JsonProperty("RESULT")
	public void setResult(List<?> RESULT) {
		this.RESULT = RESULT;
	}

	public String getSTATUS_MSG() {
		return STATUS_MSG;
	}

	@JsonProperty("STATUS_MSG")
	public void setSTATUS_MSG(String STATUS_MSG) {
		this.STATUS_MSG = STATUS_MSG;
	}

	public int getSTATUS_CODE() {
		return STATUS_CODE;
	}

	public String getMESSAGE() {
		return message;
	}

	@JsonProperty("STATUS_CODE")
	public void setSTATUS_CODE(int STATUS_CODE) {
		this.STATUS_CODE = STATUS_CODE;
	}

	@JsonProperty("MESSAGE")
	public void setMESSAGE(String MESSAGE) {
		this.message = MESSAGE;
	}

	public String getUSER_STATUS() {
		return USER_STATUS;
	}

	@JsonProperty("USER_STATUS")
	public void setUSER_STATUS(String USER_STATUS) {
		this.USER_STATUS = USER_STATUS;
	}

	public String getOTP() {
		return OTP;
	}

	@JsonProperty("OTP")
	public void setOTP(String OTP) {
		this.OTP = OTP;
	}

}
