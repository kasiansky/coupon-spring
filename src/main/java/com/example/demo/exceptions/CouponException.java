package com.example.demo.exceptions;

public class CouponException extends Exception {
	

	private String message;

	public CouponException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
