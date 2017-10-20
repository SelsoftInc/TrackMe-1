package com.selsoft.trackme.model;

public class GenericResponse {

	@SuppressWarnings("unused")
	private String message;
	@SuppressWarnings("unused")
	private String error;

	public GenericResponse(String message) {
		super();
		this.message = message;
	}

	public GenericResponse(String message, String error) {
		super();
		this.message = message;
		this.error = error;
	}
}
