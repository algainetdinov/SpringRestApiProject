package com.ajax.restapiproject.user.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * View for mapping user data retrieved by /save request
 */
@ApiModel
public class UserSaveViewReq {

	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User first name", position = 1, required = true)
	public String firstName;
	
	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User last name", position = 2)
	public String lastName;
	
	/**
	 * User first name
	 */
	@ApiModelProperty(value = "User middle name", position = 3)
	public String middleName;
	
	/**
	 * User position
	 */
	@ApiModelProperty(value = "User position", position = 4, required = true)
	public String position;
	
	/**
	 * User phone
	 */
	@ApiModelProperty(value = "User phone", position = 5)
	public String phone;
	
	/**
	 * User's office identifier
	 */
	@ApiModelProperty(value = "User's office identifier", position = 6)
	public String officeId;
	
	/**
	 * User's document code
	 */
	@ApiModelProperty(value = "User's document code", position = 7)
	public String docCode;
	
	/**
	 * User's document number
	 */
	@ApiModelProperty(value = "User's document number", position = 8)
	public String docNumber;
	
	/**
	 * User's document date
	 */
	@ApiModelProperty(value = "User's document date", position = 9)
	public String docDate;
		
	/**
	 * User's country code
	 */
	@ApiModelProperty(value = "User's country code", position = 10)
	public String citizenshipCode;
	
	/**
	 * User identification
	 */
	@ApiModelProperty(value = "User identification", position = 11)
	public String isIdentified;
	
	/**
	 * Default constructor
	 */
	public UserSaveViewReq() {
	}

	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "UserSaveViewReq [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", position=" + position + ", phone=" + phone + ", officeId=" + officeId + ", docCode=" + docCode
				+ ", docNumber=" + docNumber + ", docDate=" + docDate + ", citizenshipCode=" + citizenshipCode
				+ ", isIdentified=" + isIdentified + "]";
	}
}
