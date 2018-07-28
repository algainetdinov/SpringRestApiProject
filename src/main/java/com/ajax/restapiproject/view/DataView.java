package com.ajax.restapiproject.view;

/**
 * Wrapper view for displaying success responses
 */
public class DataView {
	
	public Object data;
	
	/**
	 * Constructor for initializing class fields 
	 * @param data
	 */
	public DataView(Object data) {
		this.data = data;
	}
}
