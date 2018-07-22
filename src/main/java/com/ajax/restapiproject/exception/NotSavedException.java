package com.ajax.restapiproject.exception;

public class NotSavedException extends ApiException {
	
	/**
	 * Constructor of the ApiException superclass
	 * @param code
	 * @param message
	 */
	public NotSavedException(String message) {
		super(message);
	}
}
