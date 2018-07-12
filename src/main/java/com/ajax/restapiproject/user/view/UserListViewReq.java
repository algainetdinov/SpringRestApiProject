package com.ajax.restapiproject.user.view;

/**
 * View for mapping organization data retrieved by /list request
 */
public class UserListViewReq {
	
	public Long officeId;
	
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
}
