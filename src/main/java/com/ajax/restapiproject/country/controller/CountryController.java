package com.ajax.restapiproject.country.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.country.service.CountryService;
import com.ajax.restapiproject.view.DictionaryView;

import io.swagger.annotations.ApiOperation;

/**
 * Controller for countries
 */
@RestController
@RequestMapping(value = "/api/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {
	
	private final CountryService countryService;
	
	@Autowired
	public CountryController (CountryService countryService) {
		this.countryService=countryService;
	}

	@ApiOperation("Returns list of countries")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<DictionaryView> countries() {
		return countryService.getCountries();
	}
}