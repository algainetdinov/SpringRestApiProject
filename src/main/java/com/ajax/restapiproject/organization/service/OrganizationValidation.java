package com.ajax.restapiproject.organization.service;

import java.util.StringJoiner;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.ajax.restapiproject.service.LegalEntityValidation;

@Component
/**
 * Class for validating fields of organization
 * 
 * @author Al
 *
 */
public class OrganizationValidation extends LegalEntityValidation {

	private static final Pattern INN_PATTERN = Pattern.compile("\\d{10}");

	private static final Pattern KPP_PATTERN = Pattern.compile("\\d{9}");

	/**
	 * Validate provided full name
	 * 
	 * @param fullName
	 * @param isRequired
	 * @return
	 */
	public String valFullName(String fullName, boolean isRequired) {

		StringJoiner fullNameError = new StringJoiner(". ");

		if (isRequired && StringUtils.isBlank(fullName)) {
			fullNameError.add("Full name should be provided");
		}

		else if (StringUtils.isNotBlank(fullName)) {
			if (fullName.trim().length() < 7 || fullName.trim().length() > 64) {
				fullNameError.add("Full name length should be between 7 and 64 characters");
			}
		}

		return fullNameError.toString();
	}

	/**
	 * Validate provided INN
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valInn(String inn, boolean isRequired) {

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
	 * 
	 * @param id
	 * @param isRequired
	 * @return
	 */
	public String valKpp(String kpp, boolean isRequired) {

		StringJoiner kppError = new StringJoiner(". ");

		if (isRequired && StringUtils.isBlank(kpp)) {
			kppError.add("KPP should be provided");
		}

		else if (StringUtils.isNotBlank(kpp) && (!KPP_PATTERN.matcher(kpp).matches())) {
			kppError.add("KPP should be 9 digit number");
		}

		return kppError.toString();
	}
}