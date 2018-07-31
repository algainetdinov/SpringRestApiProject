package com.ajax.restapiproject.user.view;

/**
 * View for mapping user data retrieved by /update request
 */
public class UserUpdateViewReq {
	
	public String id;
	
	public String firstName;
	
	public String lastName;
	
	public String middleName;
	
	public String position;
	
	public String phone;
	
	public String docCode;
	
	public String docNumber;
	
	public String docDate;
		
	public String citizenshipCode;
	
	public String isIdentified;
	
	/**
	 * Default constructor 
	 */
	public UserUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", position=" + position + ", phone=" + phone + ", docCode=" + docCode + ", docNumber="
				+ docNumber + ", docDate=" + docDate + ", citizenshipCode="	+ citizenshipCode + ", isIdentified=" + isIdentified;
	}
}
