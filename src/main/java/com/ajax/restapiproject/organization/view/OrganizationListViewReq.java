package com.ajax.restapiproject.organization.view;

/**
 * View for mapping organization data retrieved by /list request
 */
public class OrganizationListViewReq {

	public String name;
	
	public String inn;
	
	public boolean isActive;
	
	/**
	 * Default constructor
	 */
	public OrganizationListViewReq() {
	}
}
