package com.ajax.restapiproject.organization.view;

/**
 * View for mapping organization data retrieved by /update request
 */
public class OrganizationUpdateViewReq {
	
	public String id;
	
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
	public OrganizationUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", fullName=" + fullName + ", inn=" + inn
				+ ", kpp=" + kpp + ", address=" + address + ", phone=" + phone + ", isActive=" + isActive;
	}
	
	
}
