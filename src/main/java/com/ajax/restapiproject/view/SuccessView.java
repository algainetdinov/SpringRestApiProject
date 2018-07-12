package com.ajax.restapiproject.view;

/**
 * View for displaying success status
 */
public class SuccessView {
	
	public String result;
	
	/**
	 * Variable for storing request data used for testing
	 */
	public String requestData;
	
	/**
	 * Constructor for initializing class fields 
	 * @param result
	 * @param requestData
	 */
	public SuccessView(String result, String requestData) {
		this.result = result;
		this.requestData= requestData;
	}
}
