package com.ajax.restapiproject.organization.service;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
/**
 * Class for validating fields of organization
 * @author Al
 *
 */
public class OrganizationValidation {
	
	private static final Pattern INN_PATTERN = Pattern.compile("\\d{10}");
	
	private static final Pattern KPP_PATTERN = Pattern.compile("\\d{9}");
	
	private static final Pattern PHONE_PATTERN = 
			Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");
	
	/**
	 * Validate provided Id
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valId (String id, boolean isRequired) {
		StringJoiner idError = new StringJoiner(". ");
		if (isRequired && StringUtils.isBlank(id)) {
			idError.add("Identifier should be provided");
			}
		
		else if (StringUtils.isNotBlank(id)) {
			try {
				Long.parseLong(id);
				if (Long.parseLong(id)<1) {
					idError.add("Identifier should be a positive number");
				}
			}
			catch (Exception e) {
				idError.add("Identifier should be a number");
			}
		}
		return idError.toString();
	}
	
	/**
	 * Validate provided name
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valName (String name, boolean isRequired) {
		
		StringJoiner nameError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(name)) {
			nameError.add("Name should be provided");
		}
		
		else if (StringUtils.isNotBlank(name)) {
			if (name.length()<5||name.length()>64) {
			nameError.add("Name length should be between 5 and 64 characters");
			}			
		}
		return nameError.toString();	
	}
	
	/**
	 * Validate provided full name
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valFullName (String fullName, boolean isRequired) {

		StringJoiner fullNameError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(fullName)) {
			fullNameError.add("Full name should be provided");
		}
		
		else if (StringUtils.isNotBlank(fullName)) {
			if (fullName.length()<7||fullName.length()>128) {
				fullNameError.add("Full name length should be between 5 and 64 characters");
			}
		}	
		
		return fullNameError.toString();
	}
	
	/**
	 * Validate provided INN
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valInn (String inn, boolean isRequired) {
		
		StringJoiner innError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(inn)) {
			innError.add("INN should be provided");
		}
		else if (StringUtils.isNotBlank(inn) && (!INN_PATTERN.matcher(inn).matches())) {
			innError.add("INN should be 10 digit number");
		}
		
		return innError.toString();
		
	}
	
	/**
	 * Validate provided KPP
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valKpp (String kpp, boolean isRequired) {
		
		StringJoiner kppError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(kpp)) {
			kppError.add("KPP should be provided");
		}
		else if (StringUtils.isNotBlank(kpp) && (!KPP_PATTERN.matcher(kpp).matches())) {
			kppError.add("KPP should be 9 digit number");
		}	
		
		return kppError.toString();		
	}
	
	/**
	 * Validate provided phone
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valPhone (String phone, boolean isRequired) {
		
		StringJoiner phoneError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(phone)) {
			phoneError.add("Phone should be provided");
		}
		
		else if (StringUtils.isNotBlank(phone) && (!PHONE_PATTERN.matcher(phone).matches())) {
			phoneError.add("Phone number should be in +7 xxx xx xx xx format");
		}	
		
		return phoneError.toString();
	}
		
	/**
	 * Validate provided isActive flag
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valIsActive (String isActive, boolean isRequired) {
		
		StringJoiner isActiveError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(isActive)) {
			isActiveError.add("Activity should be provided");
		}
		
		else if (StringUtils.isNotBlank(isActive) && (!isActive.equalsIgnoreCase("true")) && (!isActive.equalsIgnoreCase("false"))) {
			isActiveError.add("Activity should be either true or false");
		}
		
		return isActiveError.toString();
	}
	
	/**
	 * Validate provided address
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valAddress(String address, boolean isRequired) {

		StringJoiner addressError = new StringJoiner(". ");
		
		if (isRequired && StringUtils.isBlank(address)) {
			addressError.add("Address should be provided");
		}
		
		else if (StringUtils.isNotBlank(address)) {
			if (address.length()<5||address.length()>64) {
				addressError.add("Address length should be between 5 and 64 characters");
			}
		}	
		return addressError.toString();
	}
}