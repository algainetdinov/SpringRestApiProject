package com.ajax.restapiproject.office.view;

/**
 * View for mapping office data retrieved by /update request
 */
public class OfficeUpdateViewReq {
	
	public String id;
	
	public String name;
	
	public String address;
	
	public String phone;
	
	public String isActive;
	
	/**
	 * Default constructor
	 */
	public OfficeUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "OfficeUpdateViewReq [id=" + id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", isActive=" + isActive + "]";
	}
}
