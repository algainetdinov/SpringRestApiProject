package com.ajax.restapiproject.user.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping organization data retrieved by /list request
 */
@ApiModel
public class UserListViewReq {
	
	/**
	 * User organization identifier
	 */
	@ApiModelProperty(value = "User organization identifier", position = 1, required = true)
	public String officeId;
	
	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User first name", position = 2)
	public String firstName;
	
	/**
	 * User last name
	 */
	@ApiModelProperty(value = "User last name", position = 3)
	public String lastName;
	
	/**
	 * User middle name
	 */
	@ApiModelProperty(value = "User middle name", position = 4)
	public String middleName;
	
	/**
	 * User position
	 */
	@ApiModelProperty(value = "User position", position = 5)
	public String position;
	
	/**
	 * User document code
	 */
	@ApiModelProperty(value = "User document code", position = 6)
	public String docCode;
		
	/**
	 * User citizenship code
	 */
	@ApiModelProperty(value = "User citizenship code", position = 7)
	public String citizenshipCode;
	
	/**
	 * Default constructor
	 */
	public UserListViewReq() {
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "UserListViewReq [officeId=" + officeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", position=" + position + ", docCode=" + docCode
				+ ", citizenshipCode=" + citizenshipCode + "]";
	}	
}
