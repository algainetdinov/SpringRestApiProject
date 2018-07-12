package com.ajax.restapiproject.user.view;

/**
 * View for mapping user data retrieved by /update request
 */
public class UserUpdateViewReq {
	
	public Long id;
	
	public String firstName;
	
	public String lastName;
	
	public String middleName;
	
	public String position;
	
	public String phone;
	
	public String docName;
	
	public String docNumber;
	
	public String docDate;
	
	public String citizenshipName;
	
	public String citizenshipCode;
	
	public boolean isIdentified;
	
	/**
	 * Default constructor 
	 */
	public UserUpdateViewReq() {
	}

	@Override
	public String toString() {
		return "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", position=" + position + ", phone=" + phone + ", docName=" + docName + ", docNumber="
				+ docNumber + ", docDate=" + docDate + ", citizenshipName=" + citizenshipName + ", citizenshipCode="
				+ citizenshipCode + ", isIdentified=" + isIdentified;
	}
}
