package com.ajax.restapiproject.exception;

/**
 * Thrown in case of requested entity is not found
 */
@SuppressWarnings("serial")
public class NotFoundException extends ApiException {

	/**
	 * Constructor of the ApiException superclass
	 * 
	 * @param code
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message + " is not found");
	}
}
