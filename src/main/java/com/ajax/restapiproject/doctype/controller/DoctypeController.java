package com.ajax.restapiproject.doctype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.doctype.service.DoctypeService;
import com.ajax.restapiproject.view.DictionaryView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Document types controller
 */
@RestController
@RequestMapping(value = "/api/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctypeController {

	private DoctypeService doctypeService;

	/**
	 * Constructor to set final fields
	 * 
	 * @param doctypeService
	 */
	@Autowired
	public DoctypeController(DoctypeService doctypeService) {
		this.doctypeService = doctypeService;
	}

	/**
	 * Handle request for a list of doctypes
	 * 
	 * @return
	 */
	@ApiOperation("Returns a list of existing document types")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<DictionaryView> getAll() {
		return doctypeService.loadAll();
	}
}
