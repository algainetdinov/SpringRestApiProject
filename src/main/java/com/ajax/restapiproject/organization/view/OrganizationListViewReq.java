package com.ajax.restapiproject.organization.view;

/**
 * View for mapping organization data retrieved by /list request
 */
public class OrganizationListViewReq {

	public String name;
	
	public String inn;
	
	public String isActive;
	
	/**
	 * Default constructor
	 */
	public OrganizationListViewReq() {}

	@Override
	public String toString() {
		return "OrganizationListViewReq [name=" + name + ", inn=" + inn + ", isActive=" + isActive + "]";
	}
}
