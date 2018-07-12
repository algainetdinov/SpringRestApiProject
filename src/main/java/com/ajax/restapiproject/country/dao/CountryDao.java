package com.ajax.restapiproject.country.dao;

import java.util.List;

import com.ajax.restapiproject.country.model.Country;

/**
 * DAO for counties
 */
public interface CountryDao {
	
	/**
	 * Get list of countries
	 */
	List<Country> getCountries();
	
	/**
	 * Find country by code
	 */
	Country findByCode (String code);
	
	/**
	 * Find country by ID
	 */
	Country findById (Long id);	
}
