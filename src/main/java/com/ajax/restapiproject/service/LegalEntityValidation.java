package com.ajax.restapiproject.service;

import java.util.StringJoiner;

import org.apache.commons.lang3.StringUtils;

/**
 * Legal entity validation
 * 
 * @author Al
 *
 */
public class LegalEntityValidation extends Validation {

	/**
	 * Validate provided name
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valName(String name, boolean isRequired) {

		StringJoiner nameError = new StringJoiner(". ");

		if (isRequired && StringUtils.isBlank(name)) {
			nameError.add("Name should be provided");
		}

		else if (StringUtils.isNotBlank(name)) {
			if (name.trim().length() < 5 || name.trim().length() > 64) {
				nameError.add("Name length should be between 5 and 64 characters");
			}
		}
		return nameError.toString();
	}

	/**
	 * Validate provided address
	 * 
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
			if (address.trim().length() < 5 || address.trim().length() > 64) {
				addressError.add("Address length should be between 5 and 64 characters");
			}
		}
		return addressError.toString();
	}

	/**
	 * Validate provided isActive flag
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valIsActive(String isActive, boolean isRequired) {

		StringJoiner isActiveError = new StringJoiner(". ");

		if (isRequired && StringUtils.isBlank(isActive)) {
			isActiveError.add("Activity should be provided");
		}

		else if (StringUtils.isNotBlank(isActive) && (!isActive.equalsIgnoreCase("true"))
				&& (!isActive.equalsIgnoreCase("false"))) {
			isActiveError.add("Activity should be either true or false");
		}

		return isActiveError.toString();
	}
}
