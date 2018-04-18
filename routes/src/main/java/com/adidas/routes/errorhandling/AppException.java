package com.adidas.routes.errorhandling;

/**
 * Class to map application related exceptions
 * 
 *
 */
public class AppException extends Exception {

	private static final long serialVersionUID = 1L;
	private String errorMessage;
 
	public String getErrorMessage() {
		return errorMessage;
	}
	public AppException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public AppException() {
		super();
	}

}
