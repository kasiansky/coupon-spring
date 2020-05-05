package com.example.demo.exceptions;

public class UniqueValueException extends RuntimeException {
	/**
	 * This exception will be thrown due to creating data in db that already exists
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String errorValue;
	public String getMessage() {
		return message;
	}
	public String getErrorValue() {
		return errorValue;
	}
	public UniqueValueException(String message, String errorValue) {
		super(message);
		this.message = message;
		this.errorValue = errorValue;
	}
	public UniqueValueException(String message) {
		super();
		this.message = message;
	}
	

	

}
