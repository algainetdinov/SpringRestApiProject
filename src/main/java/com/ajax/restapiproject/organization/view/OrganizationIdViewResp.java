package com.ajax.restapiproject.organization.view;

/**
 * View for displaying organization data retrieved by ID
 */
public class OrganizationIdViewResp {

	public Long id;
	
	public String name;
	
	public String fullName;
	
	public String inn;
	
	public String kpp;
	
	public String address;
	
	public String phone;
	
	public boolean isActive;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param fullName
	 * @param inn
	 * @param kpp
	 * @param address
	 * @param phone
	 * @param isActive
	 */
	public OrganizationIdViewResp(Long id, String name, String fullName, String inn, String kpp, String address,
			String phone, boolean isActive) {
		this.id = id;
		this.name = name;
		this.fullName = fullName;
		this.inn = inn;
		this.kpp = kpp;
		this.address = address;
		this.phone = phone;
		this.isActive = isActive;
	}
}
