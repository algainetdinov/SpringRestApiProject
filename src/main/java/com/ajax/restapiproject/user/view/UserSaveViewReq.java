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
	
	public String officeId;
	
	public String docCode;
	
	public String docNumber;
	
	public String docDate;
		
	public String citizenshipCode;
	
	public String isIdentified;
	
	/**
	 * Default constructor
	 */
	public UserSaveViewReq() {
	}

	@Override
	public String toString() {
		return "UserSaveViewReq [firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
				+ ", position=" + position + ", phone=" + phone + ", officeId=" + officeId + ", docCode=" + docCode
				+ ", docNumber=" + docNumber + ", docDate=" + docDate + ", citizenshipCode=" + citizenshipCode
				+ ", isIdentified=" + isIdentified + "]";
	}
}
