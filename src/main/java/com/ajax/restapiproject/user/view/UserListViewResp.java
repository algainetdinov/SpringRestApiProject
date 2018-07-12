package com.ajax.restapiproject.user.view;

/**
 * View for displaying user data retrieved by /list request
 */
public class UserListViewResp {
	public Long id;
	
	public String firstName;
	
	public String lastName;
	
	public String middleName;
	
	public String position;
	
	/**
	 * Constructor for initializing class fields
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param position
	 */
	public UserListViewResp(Long id, String firstName, String lastName, String middleName, String position) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.position = position;
	}
}