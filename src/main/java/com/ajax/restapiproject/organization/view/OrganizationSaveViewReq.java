package com.ajax.restapiproject.organization.view;

/**
 * View for mapping organization data retrieved by /save request
 */
public class OrganizationSaveViewReq {

	public String name;
	
	public String fullName;
	
	public String inn;
	
	public String kpp;
	
	public String address;
	
	public String phone;
	
	public String isActive;

	/**
	 * Default constructor
	 */
	public OrganizationSaveViewReq() {
	}

	@Override
	public String toString() {
		return "name=" + name + ", fullName=" + fullName + ", inn=" + inn + ", kpp=" + kpp
				+ ", address=" + address + ", phone=" + phone + ", isActive=" + isActive;
	}
}
