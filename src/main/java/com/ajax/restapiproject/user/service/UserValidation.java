package com.ajax.restapiproject.user.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ajax.restapiproject.service.Validation;

/**
 * Provides User data validation
 * @author Al
 *
 */
@Component
public class UserValidation extends Validation {
	
	private final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * Validate provided first name
	 * @param firstName
	 * @param isRequired
	 * @return
	 */
	public String valFirstName (String firstName, boolean isRequired) {

		StringJoiner firstNameError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(firstName)) {
			firstNameError.add("First name should be provided");
		}
		
		else if (StringUtils.isNotBlank(firstName)) {
			if (firstName.length()<3 || firstName.length()>32) {
				firstNameError.add("First name length should be between 3 and 32 characters");
			}
		}	
		
		return firstNameError.toString();
	}
	
	/**
	 * Validate provided first name
	 * @param lastName
	 * @param isRequired
	 * @return
	 */
	public String valLastName (String lastName, boolean isRequired) {

		StringJoiner lastNameError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(lastName)) {
			lastNameError.add("First name should be provided");
		}
		
		else if (StringUtils.isNotBlank(lastName)) {
			if (lastName.length()<3 || lastName.length()>32) {
				lastNameError.add("Last name length should be between 3 and 32 characters");
			}
		}	
		
		return lastNameError.toString();
	}

	/**
	 * Validate provided middle name
	 * @param middleName
	 * @param isRequired
	 * @return
	 */
	public String valMiddleName (String middleName, boolean isRequired) {

		StringJoiner middleNameError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(middleName)) {
			middleNameError.add("Middle name should be provided");
		}
		
		else if (StringUtils.isNotBlank(middleName)) {
			if (middleName.length()<3 || middleName.length()>32) {
				middleNameError.add("Middle name length should be between 3 and 32 characters");
			}
		}	
		
		return middleNameError.toString();
	}
	
	/**
	 * Validate provided position
	 * @param position
	 * @param isRequired
	 * @return
	 */
	public String valPosition (String position, boolean isRequired) {

		StringJoiner positionError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(position)) {
			positionError.add("Position name should be provided");
		}
		
		else if (StringUtils.isNotBlank(position)) {
			if (position.length()<5 || position.length()>32) {
				positionError.add("Position name length should be between 5 and 32 characters");
			}
		}	
		
		return positionError.toString();
	}
	
	/**
	 * Validate provided isIdentified flag
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valIsIdentified (String isIdentified, boolean isRequired) {
		
		StringJoiner isIdentifiedError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(isIdentified)) {
			isIdentifiedError.add("isIdentified should be provided");
		}
		
		else if (StringUtils.isNotBlank(isIdentified) && (!isIdentified.equalsIgnoreCase("true")) && (!isIdentified.equalsIgnoreCase("false"))) {
			isIdentifiedError.add("isIdentified should be either true or false");
		}
		
		return isIdentifiedError.toString();
	}
	
	/**
	 * Validate provided docCode
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valDocCode (String docCode, boolean isRequired) {
		
		StringJoiner docCodeError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(docCode)) {
			docCodeError.add("Document type code should be provided");
		}
		
		else if (StringUtils.isNotBlank(docCode)) {
			if (docCode.length()!=2) {
				docCodeError.add("Document type code length should be 2 digits");
			}
			try {
				Long.parseLong(docCode);
				if (Integer.parseInt(docCode)<1) {
					docCodeError.add("Document type code should be a positive number");
				}
			}
			catch (Exception e) {
				logger.error("Parse document code exception: ", e);
				docCodeError.add("Document type code should be a number");
			}
		}
		
		return docCodeError.toString();
	}
	
	/**
	 * Validate provided Document number
	 * @param docNumber
	 * @param isRequired
	 * @return
	 */
	public String valDocNumber (String docNumber, boolean isRequired) {
		
		StringJoiner docNumberError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(docNumber)) {
			docNumberError.add("Document number should be provided");
		}
		
		else if (StringUtils.isNotBlank(docNumber)) {
			if (docNumber.length()<3 || docNumber.length()>32) {
				docNumberError.add("Document number length should be between 3 and 32 characters");
			}
		}
		
		return docNumberError.toString();
	}
	
	/**
	 * Validate provided Document date
	 * @param docDate
	 * @param isRequired
	 * @return
	 */
	public String valDocDate (String docDate, boolean isRequired) {
		
		StringJoiner docDateError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(docDate)) {
			docDateError.add("Document date should be provided");
		}
		
		else if (StringUtils.isNotBlank(docDate)) {
			if (docDate.length()!=10) {
				docDateError.add("Document date should be in a DD.MM.YYYY format");
			}
			else {
				try {
				    df.parse(docDate); 
				}
				catch (ParseException e) {
					logger.error("Parse date exception: ", e);
					docDateError.add("Document date should be in a DD.MM.YYYY format");
				}
			}
		}
		
		return docDateError.toString();
	}
	
	/**
	 * Validate provided Citizenship code
	 * @param citizCode
	 * @param isRequired
	 * @return
	 */
	public String valCitizCode (String citizCode, boolean isRequired) {
		
		StringJoiner citizCodeError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(citizCode)) {
			citizCodeError.add("Document type code should be provided");
		}
		
		else if (StringUtils.isNotBlank(citizCode)) {
			if (citizCode.trim().length()!=3) {
				citizCodeError.add("Citizenship code length should be 3 digits");
			}
			try {
				Long.parseLong(citizCode);
				if (Integer.parseInt(citizCode)<1) {
					citizCodeError.add("Citizenship code should be a positive number");
				}
			}
			catch (Exception e) {
				logger.error("Parse citizenship code exception: ", e);
				citizCodeError.add("Citizenship code should be a number");
			}
		}		
		return citizCodeError.toString();
	}	
}
