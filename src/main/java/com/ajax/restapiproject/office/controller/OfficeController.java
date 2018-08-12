package com.ajax.restapiproject.office.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.office.service.OfficeService;
import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for office
 */
@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {

	private OfficeService officeService;

	/**
	 * Constructor to set final fields
	 * 
	 * @param officeService
	 */
	@Autowired
	public OfficeController(OfficeService officeService) {

		this.officeService = officeService;
	}

	/**
	 * Request for retrieving list of offices
	 * 
	 * @param reqView
	 * @return
	 */
	@ApiOperation("Returns lists of offices belonging to specified organization")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OfficeListViewResp> getByOrg(@RequestBody OfficeListViewReq reqView) {

		return officeService.loadByOrg(reqView);
	}

	/**
	 * Request for retrieving office by Id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Returns an office specified by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public OfficeIdViewResp getById(@PathVariable String id) {

		return officeService.loadById(id);
	}

	/**
	 * Request for updating office
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Updates an office with provided data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public SuccessView update(@RequestBody OfficeUpdateViewReq reqView) {

		return officeService.update(reqView);
	}

	/**
	 * Request for saving an office
	 * 
	 * @param reqView
	 * @return
	 */
	@ApiOperation("Creates an office with provided data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SuccessView save(@RequestBody OfficeSaveViewReq reqView) {

		return officeService.save(reqView);
	}

	/**
	 * Request for deleting an office
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Deletes an office by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public SuccessView deleteById(@PathVariable String id) {

		return officeService.deleteById(id);
	}
}
