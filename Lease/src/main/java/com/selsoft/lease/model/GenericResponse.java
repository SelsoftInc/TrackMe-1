package com.selsoft.lease.model;

public class GenericResponse {

	@SuppressWarnings("unused")
	private String message;
	@SuppressWarnings("unused")
	private String error;

	public GenericResponse(String message) {
		this.message = message;
	}

	public GenericResponse(String message, String error) {
		this.message = message;
		this.error = error;
	}
}
