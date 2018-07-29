package com.ajax.restapiproject.office.view;

/**
 * View for mapping office data retrieved by /list request
 */
public class OfficeListViewReq {
	
	public String orgId;
	
	public String name;
	
	public String phone;
	
	public String isActive;
	
	/**
	 * Default constructor
	 */
	public OfficeListViewReq() {
	}
}
