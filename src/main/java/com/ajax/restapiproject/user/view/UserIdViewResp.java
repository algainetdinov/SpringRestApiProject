package com.ajax.restapiproject.user.view;

/**
 * View for displaying user data retrieved by ID
 */
public class UserIdViewResp {

	public String id;
		
	public String firstName;
		
	public String lastName;
		
	public String middleName;
		
	public String position;
	
	public String officeId;
	
	public String phone;
		
	public String docCode;
	
	public String docName;
		
	public String docNumber;
		
	public String docDate;
		
	public String citizenshipName;
		
	public String citizenshipCode;
		
	public String isIdentified;

	/**
	 * Constructor using fields
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param middleName
	 * @param position
	 * @param officeId
	 * @param phone
	 * @param docCode
	 * @param docName
	 * @param docNumber
	 * @param docDate
	 * @param citizenshipName
	 * @param citizenshipCode
	 * @param isIdentified
	 */
	public UserIdViewResp(String id, String firstName, String lastName, String middleName, String position,
			String officeId, String phone, String docCode, String docName, String docNumber, String docDate,
			String citizenshipName, String citizenshipCode, String isIdentified) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.position = position;
		this.officeId = officeId;
		this.phone = phone;
		this.docCode = docCode;
		this.docName = docName;
		this.docNumber = docNumber;
		this.docDate = docDate;
		this.citizenshipName = citizenshipName;
		this.citizenshipCode = citizenshipCode;
		this.isIdentified = isIdentified;
	}
}
