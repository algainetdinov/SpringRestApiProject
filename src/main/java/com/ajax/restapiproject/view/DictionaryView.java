package com.ajax.restapiproject.view;

/**
 * View for displaying dictionary records
 */
public class DictionaryView {
	
	public String code;
	
	public String name;
	
	/**
	 * Constructor for initializing class fields
	 * @param code
	 * @param name
	 */
	public DictionaryView(String code, String name) {
		this.code = code;
		this.name = name;
	}
}
