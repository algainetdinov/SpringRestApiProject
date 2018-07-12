package com.ajax.restapiproject.office.view;

/**
 * View for displaying office data retrieved by ID
 */
public class OfficeIdViewResp {
	
	public Long id;
	
	public String name;
	
	public String address;
	
	public String phone;
	
	public boolean isActive;

	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param address
	 * @param phone
	 * @param isActive
	 */
	public OfficeIdViewResp(Long id, String name, String address, String phone, boolean isActive) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.isActive = isActive;
	}	
}
