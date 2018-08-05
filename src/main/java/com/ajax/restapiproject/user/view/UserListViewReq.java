package com.ajax.restapiproject.user.view;

/**
 * View for mapping organization data retrieved by /list request
 */
public class UserListViewReq {
	
	public String officeId;
	
	public String firstName;
	
	public String lastName;
	
	public String middleName;
	
	public String position;
	
	public String docCode;
	
	public String citizenshipCode;
	
	/**
	 * Default constructor
	 */
	public UserListViewReq() {
	}

	public String getOfficeId() {
		return officeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getPosition() {
		return position;
	}

	public String getDocCode() {
		return docCode;
	}

	public String getCitizenshipCode() {
		return citizenshipCode;
	}	
}
