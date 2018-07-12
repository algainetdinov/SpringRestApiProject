package com.ajax.restapiproject.office.view;

/**
 * View for mapping office data retrieved by /list request
 */
public class OfficeListViewReq {
	
	public Long orgId;
	
	public String name;
	
	public String phone;
	
	public boolean isActive;
	
	/**
	 * Default constructor
	 */
	public OfficeListViewReq() {
	}
}
