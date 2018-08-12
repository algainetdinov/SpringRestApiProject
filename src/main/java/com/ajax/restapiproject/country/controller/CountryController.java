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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for countries
 */
@RestController
@RequestMapping(value = "/api/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountryController {

	private final CountryService countryService;

	/**
	 * Constructor to set final fields
	 * 
	 * @param countryService
	 */
	@Autowired
	public CountryController(CountryService countryService) {

		this.countryService = countryService;
	}

	/**
	 * Handle request for a list of countries
	 * 
	 * @return
	 */
	@ApiOperation("Returns list of countries")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<DictionaryView> getAll() {

		return countryService.loadAll();
	}
}