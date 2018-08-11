package com.ajax.restapiproject.country.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ajax.restapiproject.country.dao.CountryDao;
import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Tests Country service implementation
 * @author Al
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CountryServiceImplTests {
	
	@InjectMocks
	private CountryServiceImpl countryService;
	
	@Mock
	private CountryDao countryDao;
	
	@Mock
	private Country country;
	
	/**
	 * Check that country with id 1L exists
	 */
	@Test
	public void loadByIdTest() {	
		
		when(countryDao.findById(anyLong())).thenReturn(country);
		assertThat(countryService.loadById(1L)).isNotNull();
	}	
	
	/**
	 * Check that list of countries could be retrieved
	 */
	@Test
	public void loadAllTest() {
		
		when(countryDao.findAll()).thenReturn(Arrays.asList(new Country()));
		assertThat(countryService.loadAll()).hasAtLeastOneElementOfType(DictionaryView.class);
	}
}
