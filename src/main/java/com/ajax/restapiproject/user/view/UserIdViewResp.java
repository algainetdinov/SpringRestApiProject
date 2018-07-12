package com.ajax.restapiproject.user.view;

/**
 * View for displaying user data retrieved by ID
 */
public class UserIdViewResp {

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
	 * Constructor for initializing class fields
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param position
	 * @param phone
	 * @param docName
	 * @param docNumber
	 * @param docDate
	 * @param citizenshipName
	 * @param citizenshipCode
	 * @param isIdentified
	 */
	public UserIdViewResp(Long id, String firstName, String lastName, String middleName, String position, String phone,
				String docName, String docNumber, String docDate, String citizenshipName, String citizenshipCode,
				boolean isIdentified) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
			this.position = position;
			this.phone = phone;
			this.docName = docName;
			this.docNumber = docNumber;
			this.docDate = docDate;
			this.citizenshipName = citizenshipName;
			this.citizenshipCode = citizenshipCode;
			this.isIdentified = isIdentified;
		}	
}
