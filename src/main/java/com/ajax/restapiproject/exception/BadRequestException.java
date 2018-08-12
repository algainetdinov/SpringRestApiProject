package com.ajax.restapiproject.exception;

/**
 * Thrown in case of failed validation
 */
@SuppressWarnings("serial")
public class BadRequestException extends ApiException {

	/**
	 * Constructor for calling of the ApiException superclass constructor
	 * 
	 * @param code
	 * @param message
	 */
	public BadRequestException(String message) {
		super(message);
	}
}
