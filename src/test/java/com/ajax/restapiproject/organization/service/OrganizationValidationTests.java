package com.ajax.restapiproject.organization.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Organization validation tests
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizationValidationTests {
	
	@InjectMocks
	protected OrganizationValidation organizationVal;
	
	/**
	 * Validate organization name
	 */
	@Test
	public void valNameTest() {
		assertThat(organizationVal.valName("", false)).isEmpty();
		assertThat(organizationVal.valName("", true)).isNotEmpty();
		assertThat(organizationVal.valName("   Aj   ", false)).isNotEmpty();
		assertThat(organizationVal.valName("AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax"
				+ "AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax", false)).isNotEmpty();
		assertThat(organizationVal.valName("Ajax inc.", true)).isEmpty();
	}
	
	/**
	 * Validate organization full name
	 */
	@Test
	public void valFullNameTest() {
		assertThat(organizationVal.valFullName("", false)).isEmpty();
		assertThat(organizationVal.valFullName("", true)).isNotEmpty();
		assertThat(organizationVal.valFullName("   Aj   ", false)).isNotEmpty();
		assertThat(organizationVal.valFullName("AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax"
				+ "AjaxAjaxAjaxAjaxAjaxAjaxAjaxAjax", false)).isNotEmpty();
		assertThat(organizationVal.valFullName("Ajax inc.", true)).isEmpty();
	}
	
	/**
	 * Validate organization INN
	 */
	@Test
	public void valInnTest() {
		assertThat(organizationVal.valInn("", false)).isEmpty();
		assertThat(organizationVal.valInn("", true)).isNotEmpty();
		assertThat(organizationVal.valInn(" 34234334 ", false)).isNotEmpty();
		assertThat(organizationVal.valInn("f131322313", false)).isNotEmpty();
		assertThat(organizationVal.valInn("2365478954", true)).isEmpty();
	}
	
	/**
	 * Validate organization KPP
	 */
	@Test
	public void valKppTest() {
		assertThat(organizationVal.valKpp("", false)).isEmpty();
		assertThat(organizationVal.valKpp("", true)).isNotEmpty();
		assertThat(organizationVal.valKpp(" 3423433 ", false)).isNotEmpty();
		assertThat(organizationVal.valKpp("1313223f6", false)).isNotEmpty();
		assertThat(organizationVal.valKpp("236547846", true)).isEmpty();
	}
}
