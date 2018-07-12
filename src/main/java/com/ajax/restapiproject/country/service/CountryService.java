package com.ajax.restapiproject.country.service;

import java.util.List;

import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Country service
 */
public interface CountryService {
	
	/**
	 * Retrieve country by ID
	 * @param id
	 * @return
	 */
	Country getCountryById(Long id);
	
	/**
	 * Retrieve list of countries
	 * @return
	 */
	List<DictionaryView> getCountries();
	
}
