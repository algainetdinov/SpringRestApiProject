package com.ajax.restapiproject.user.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping user data retrieved by /update request
 */
@ApiModel
public class UserUpdateViewReq {
	
	/**
	 * User identifier
	 */
	@ApiModelProperty(value = "User identifier", position = 1, required = true)
	public String id;
	
	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User first name", position = 2, required = true)
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
	@ApiModelProperty(value = "User position", position = 5, required = true)
	public String position;
	
	/**
	 * User phone
	 */
	@ApiModelProperty(value = "User phone", position = 6)
	public String phone;
	
	/**
	 * User document code
	 */
	@ApiModelProperty(value = "User document code", position = 7)
	public String docCode;
	
	/**
	 * User document number
	 */
	@ApiModelProperty(value = "User document number", position = 8)
	public String docNumber;
	
	/**
	 * User document date
	 */
	@ApiModelProperty(value = "User document date", position = 9)
	public String docDate;
	
	/**
	 * User country code
	 */
	@ApiModelProperty(value = "User country code", position = 10)
	public String citizenshipCode;
	
	/**
	 * User identification
	 */
	@ApiModelProperty(value = "User identification", position = 11)
	public String isIdentified;
	
	/**
	 * Default constructor 
	 */
	public UserUpdateViewReq() {
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", position=" + position + ", phone=" + phone + ", docCode=" + docCode + ", docNumber="
				+ docNumber + ", docDate=" + docDate + ", citizenshipCode="	+ citizenshipCode + ", isIdentified=" + isIdentified;
	}
}
