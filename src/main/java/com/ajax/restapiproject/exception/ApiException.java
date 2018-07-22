package com.ajax.restapiproject.exception;

/**
 * Api exception superclass
 */
public class ApiException extends RuntimeException{
		
	/**
	 * Exception constructor
	 * @param message
	 */
	public ApiException(String message) {
		super(message);
	}
}
