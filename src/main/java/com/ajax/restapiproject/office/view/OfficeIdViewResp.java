package com.ajax.restapiproject.office.view;

/**
 * View for displaying office data retrieved by ID
 */
public class OfficeIdViewResp {
	
	public String id;
	
	public String name;
	
	public String orgId;

	public String address;
	
	public String phone;
	
	public String isActive;

	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param orgId
	 * @param address
	 * @param phone
	 * @param isActive
	 */
	public OfficeIdViewResp(String id, String name, String orgId, String address, String phone, String isActive) {
		this.id = id;
		this.name = name;
		this.orgId = orgId;
		this.address = address;
		this.phone = phone;
		this.isActive = isActive;
	}
}
