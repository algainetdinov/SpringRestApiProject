package com.ajax.restapiproject.service;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * Class for common validation
 * 
 * @author Al
 *
 */
public class Validation {

	private static final Pattern PHONE_PATTERN = Pattern
			.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$");

	/**
	 * Validate provided Id
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valId(String id, boolean isRequired) {
		StringJoiner idError = new StringJoiner(". ");
		if (isRequired && StringUtils.isBlank(id)) {
			idError.add("Identifier should be provided");
		}

		else if (StringUtils.isNotBlank(id)) {
			try {
				Long.parseLong(id);
				if (Long.parseLong(id) < 1) {
					idError.add("Identifier should be a positive number");
				}
			} catch (Exception e) {
				idError.add("Identifier should be a number");
			}
		}
		return idError.toString();
	}

	/**
	 * Validate provided phone
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valPhone(String phone, boolean isRequired) {

		StringJoiner phoneError = new StringJoiner(". ");

		if (isRequired && StringUtils.isBlank(phone)) {
			phoneError.add("Phone should be provided");
		}

		else if (StringUtils.isNotBlank(phone) && (!PHONE_PATTERN.matcher(phone).matches())) {
			phoneError.add("Phone number should be in +7 xxx xx xx xx format");
		}

		return phoneError.toString();
	}
}
