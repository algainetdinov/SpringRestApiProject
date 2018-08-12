package com.ajax.restapiproject.office.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Office validation tests
 * 
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OfficeValidationTests {

	@InjectMocks
	protected OfficeValidation officeVal;

	/**
	 * Validate office name
	 */
	@Test
	public void valNameTest() {
		assertThat(officeVal.valName("", false)).isEmpty();
		assertThat(officeVal.valName("", true)).isNotEmpty();
		assertThat(officeVal.valName("   Aj   ", false)).isNotEmpty();
		assertThat(
				officeVal.valName("AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax" + "AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax", false))
						.isNotEmpty();
		assertThat(officeVal.valName("Ajax inc.", true)).isEmpty();
	}

	/**
	 * Validate office address
	 */
	@Test
	public void valAddressTest() {
		assertThat(officeVal.valAddress("", false)).isEmpty();
		assertThat(officeVal.valAddress("", true)).isNotEmpty();
		assertThat(officeVal.valAddress("   Mos   ", false)).isNotEmpty();
		assertThat(
				officeVal.valAddress("MoscowMoscowMoscowMoscowMoscowMoscow" + "MoscowMoscowMoscowMoscowMoscow", false))
						.isNotEmpty();
		assertThat(officeVal.valAddress("Moscow Lenina str", true)).isEmpty();
	}

	/**
	 * Validate office activity
	 */
	@Test
	public void valIsActiveTest() {
		assertThat(officeVal.valIsActive("", false)).isEmpty();
		assertThat(officeVal.valIsActive("", true)).isNotEmpty();
		assertThat(officeVal.valIsActive("false1", false)).isNotEmpty();
		assertThat(officeVal.valIsActive("false", false)).isEmpty();
		assertThat(officeVal.valIsActive("true", false)).isEmpty();
		assertThat(officeVal.valIsActive("fAlsE", false)).isEmpty();
		assertThat(officeVal.valIsActive("trUE", false)).isEmpty();
	}
}
