package com.example.demo.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(MySQLException.class)
	public ResponseEntity<Object> handleMySQLException(MySQLException e){
		ApiError apiError = new ApiError(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Object> handleThrowable(Throwable t) {

		ApiError apiError = new ApiError(t.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UniqueValueException.class)
	public ResponseEntity<Object> handleUniqueValueException (UniqueValueException e) {

		ApiError apiError = new ApiError(e.getMessage(), HttpStatus.BAD_REQUEST,
				ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleNotFoundException (NotFoundException e) {
		ApiError apierror = new ApiError(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apierror, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	@ExceptionHandler(CouponSystemException.class)
	public ResponseEntity<Object> handleCouponSystemException (CouponSystemException e) {
		ApiError apierror = new ApiError(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("Z")));
		return new ResponseEntity<Object>(apierror, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	

}
