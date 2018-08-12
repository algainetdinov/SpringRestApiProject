package com.ajax.restapiproject.exception;

/**
 * Thrown in case of requested entity cannot be saved
 */
@SuppressWarnings("serial")
public class NotSavedException extends ApiException {

	/**
	 * Constructor of the ApiException superclass
	 * 
	 * @param code
	 * @param message
	 */
	public NotSavedException(String message) {
		super(message);
	}
}
