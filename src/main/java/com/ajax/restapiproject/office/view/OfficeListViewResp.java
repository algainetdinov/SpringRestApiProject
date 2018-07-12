package com.ajax.restapiproject.office.view;

/**
 * View for displaying office data retrieved by /list request
 */
public class OfficeListViewResp {
	
	public Long id;
	
	public String name;
	
	public boolean isActive;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param isActive
	 */
	public OfficeListViewResp(Long id, String name, boolean isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
}
