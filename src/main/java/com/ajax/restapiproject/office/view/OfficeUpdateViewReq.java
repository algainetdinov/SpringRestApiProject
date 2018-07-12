package com.ajax.restapiproject.office.view;

/**
 * View for mapping office data retrieved by /update request
 */
public class OfficeUpdateViewReq {
	
	public Long id;
	
	public String name;
	
	public String address;
	
	public String phone;
	
	public boolean isActive;
	
	/**
	 * Default constructor
	 */
	public OfficeUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", isActive=" + isActive;
	}
}
