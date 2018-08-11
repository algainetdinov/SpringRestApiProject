package com.ajax.restapiproject.office.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for displaying office data retrieved by /list request
 */
@ApiModel
public class OfficeListViewResp {
	
	/**
	 * Office identifier
	 */
	@ApiModelProperty(value = "Office identifier", position = 1)
	public String id;
	
	/**
	 * Office name
	 */
	@ApiModelProperty(value = "Office name", position = 2)
	public String name;
	
	/**
	 * Office activity
	 */
	@ApiModelProperty(value = "Office activity", position = 3)
	public String isActive;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param name
	 * @param isActive
	 */
	public OfficeListViewResp(String id, String name, String isActive) {
		this.id = id;
		this.name = name;
		this.isActive = isActive;
	}
}
