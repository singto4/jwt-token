package com.example.app.exception;

import org.springframework.http.HttpStatus;

public class APIException extends Exception {

	private static final long serialVersionUID = 1L;

	private final String errorMessage;
	private final HttpStatus httpStatus;
	
	public APIException(HttpStatus httpStatus, String errorMessage) {
		super("[" + httpStatus + "] " +  errorMessage);

		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public APIException(HttpStatus httpStatus, String errorMessage, Throwable t) {
		super("[" + httpStatus + "] " +  errorMessage, t);

		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

}
