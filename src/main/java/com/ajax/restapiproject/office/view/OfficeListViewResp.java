package com.ajax.restapiproject.office.view;

/**
 * View for displaying office data retrieved by /list request
 */
public class OfficeListViewResp {
	
	public String id;
	
	public String name;
	
	public String isActive;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param isActive
	 */
	public OfficeListViewResp(String id, String name, String isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
}
