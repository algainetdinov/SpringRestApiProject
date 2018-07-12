package com.ajax.restapiproject.user.view;

/**
 * View for mapping user data retrieved by /save request
 */
public class UserSaveViewReq {

	public String firstName;
	
	public String lastName;
	
	public String middleName;
	
	public String position;
	
	public String phone;
	
	public String docCode;
	
	public String docName;
	
	public String docNumber;
	
	public String docDate;
		
	public String citizenshipCode;
	
	public boolean isIdentified;
	
	/**
	 * Default constructor
	 */
	public UserSaveViewReq() {
	}

	@Override
	public String toString() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", position=" + position + ", phone=" + phone + ", docCode=" + docCode + ", docName=" + docName
				+ ", docNumber=" + docNumber + ", docDate=" + docDate + ", citizenshipCode=" + citizenshipCode
				+ ", isIdentified=" + isIdentified;
	}
}
