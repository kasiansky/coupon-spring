package com.example.demo.exceptions;

public class MySQLException extends Exception {

	
	public MySQLException(String message, Throwable cause) {
		super(message,cause);
		
	}


	public MySQLException(String message) {
		super(message);
		
	}
	


	
}
