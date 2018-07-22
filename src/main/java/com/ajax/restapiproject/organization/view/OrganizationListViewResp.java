package com.ajax.restapiproject.organization.view;

/**
 * View for displaying office data retrieved by /list request
 */
public class OrganizationListViewResp {
	
	public Long id;
	
	public String name;
	
	public String isActive;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param isActive
	 */
	public OrganizationListViewResp(Long id, String name, String isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
}
