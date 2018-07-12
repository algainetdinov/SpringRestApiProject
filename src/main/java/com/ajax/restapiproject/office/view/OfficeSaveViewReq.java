package com.ajax.restapiproject.office.view;

/**
 * View for mapping office data retrieved by /save request
 */
public class OfficeSaveViewReq {
		
	public String name;
	
	public String address;

	public String phone;
	
	public boolean isActive;
	
	/**
	 * Default constructor
	 */
	public OfficeSaveViewReq() {
	}

	@Override
	public String toString() {
		return "name=" + name + ", address=" + address + ", phone=" + phone + ", isActive="+ isActive;
	}
}