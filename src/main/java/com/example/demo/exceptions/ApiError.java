package com.example.demo.exceptions;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApiError {
	private final String message;
	private final HttpStatus status;
	private final ZonedDateTime timestamp;
	
	public ApiError(String message, HttpStatus status, ZonedDateTime timestamp) {
		
		this.message = message;
		this.status = status;
		this.timestamp = timestamp;
	}
	

	public String getMessage() {
		return message;
	}


	public HttpStatus getStatus() {
		return status;
	}


	public ZonedDateTime getTimestamp() {
		return timestamp;
	}


	
}
