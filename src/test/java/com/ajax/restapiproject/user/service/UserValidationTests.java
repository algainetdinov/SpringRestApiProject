package com.ajax.restapiproject.user.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * User validation tests
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserValidationTests {
	
	@InjectMocks
	protected UserValidation userVal;
	
	@Test
	public void valFirstNameTest() {
		assertThat(userVal.valFirstName("", false)).isEmpty();
		assertThat(userVal.valFirstName("", true)).isNotEmpty();
		assertThat(userVal.valFirstName("   Ал   ", false)).isNotEmpty();
		assertThat(userVal.valFirstName("АлексейАлексейАлексейАлексейАлексей", false)).isNotEmpty();
		assertThat(userVal.valFirstName("Алексей", true)).isEmpty();
	}
	
	@Test
	public void valLastNameTest() {
		assertThat(userVal.valLastName("", false)).isEmpty();
		assertThat(userVal.valLastName("", true)).isNotEmpty();
		assertThat(userVal.valLastName("   Ив   ", false)).isNotEmpty();
		assertThat(userVal.valLastName("ИвановИвановИвановИвановИвановИванов", false)).isNotEmpty();
		assertThat(userVal.valLastName("Иванов", true)).isEmpty();
	}
	
	@Test
	public void valMiddleNameTest() {
		assertThat(userVal.valMiddleName("", false)).isEmpty();
		assertThat(userVal.valMiddleName("", true)).isNotEmpty();
		assertThat(userVal.valMiddleName("Пе", false)).isNotEmpty();
		assertThat(userVal.valMiddleName("ПетровичПетровичПетровичПетровичПетрович", false)).isNotEmpty();
		assertThat(userVal.valMiddleName("Петрович", true)).isEmpty();
	}
	
	@Test
	public void valPositionTest() {
		assertThat(userVal.valPosition("", false)).isEmpty();
		assertThat(userVal.valPosition("", true)).isNotEmpty();
		assertThat(userVal.valPosition("Дире", false)).isNotEmpty();
		assertThat(userVal.valPosition("ДиректорДиректорДиректорДиректорДиректор", false)).isNotEmpty();
		assertThat(userVal.valPosition("Директор", true)).isEmpty();
	}
	
	@Test
	public void valIsIdentifiedTest() {
		assertThat(userVal.valIsIdentified("", false)).isEmpty();
		assertThat(userVal.valIsIdentified("", true)).isNotEmpty();
		assertThat(userVal.valIsIdentified("false1", false)).isNotEmpty();
		assertThat(userVal.valIsIdentified("false", false)).isEmpty();
		assertThat(userVal.valIsIdentified("true", false)).isEmpty();
		assertThat(userVal.valIsIdentified("fAlsE", false)).isEmpty();
		assertThat(userVal.valIsIdentified("trUE", false)).isEmpty();
	}
	
	@Test
	public void valDocCodeTest() {
		assertThat(userVal.valDocCode("", false)).isEmpty();
		assertThat(userVal.valDocCode("", true)).isNotEmpty();
		assertThat(userVal.valDocCode("AA", false)).isNotEmpty();
		assertThat(userVal.valDocCode("11 ВВ", false)).isNotEmpty();
		assertThat(userVal.valDocCode("Номер_1_Номер_1Номер_1Номер_1Номер_1Номер_1Номер", false)).isNotEmpty();
	}
	
	@Test
	public void valDocDateTest() {
		assertThat(userVal.valDocDate("", false)).isEmpty();
		assertThat(userVal.valDocDate("", true)).isNotEmpty();
		assertThat(userVal.valDocDate("13.13.2100", false)).isNotEmpty();
		assertThat(userVal.valDocDate("11.11.2011", false)).isEmpty();
		assertThat(userVal.valDocDate("дата", false)).isNotEmpty();
		assertThat(userVal.valDocDate("ДатаДатаДатаДатаДатаДатаДатаДатаДатаДатаДата", false)).isNotEmpty();
	}
	
	@Test
	public void valCitizCodeTest() {
		assertThat(userVal.valCitizCode("", false)).isEmpty();
		assertThat(userVal.valCitizCode("", true)).isNotEmpty();
		assertThat(userVal.valCitizCode("VVV", false)).isNotEmpty();
		assertThat(userVal.valCitizCode("123", false)).isEmpty();
		assertThat(userVal.valCitizCode("  123   ", false)).isEmpty();
		assertThat(userVal.valCitizCode("-23", false)).isNotEmpty();
		assertThat(userVal.valCitizCode("3453", false)).isNotEmpty();
	}
	
}
