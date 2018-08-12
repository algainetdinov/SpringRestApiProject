package com.ajax.restapiproject.organization.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.organization.service.OrganizationService;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Organization controller
 */
@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {

	private final OrganizationService orgService;

	/**
	 * Constructor to set final fields
	 * 
	 * @param orgService
	 */
	@Autowired
	public OrganizationController(OrganizationService orgService) {
		this.orgService = orgService;
	}

	/**
	 * Retrieve list of organizations
	 */
	@ApiOperation("Returns filtered list of organizations")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OrganizationListViewResp> getByName(@RequestBody OrganizationListViewReq org) {

		return orgService.loadByName(org);
	}

	/**
	 * Retrieve an organization by Id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Returns an organization specified by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public OrganizationIdViewResp getById(@PathVariable String id) {

		return orgService.loadById(id);
	}

	/**
	 * Update an organization
	 * 
	 * @param updateOrg
	 * @return
	 */
	@ApiOperation("Updates an organization with provided data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public SuccessView update(@RequestBody OrganizationUpdateViewReq updateOrg) {

		return orgService.update(updateOrg);
	}

	/**
	 * Save an organization
	 * 
	 * @param saveOrg
	 * @return
	 */
	@ApiOperation("Creates an organization with provided data")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Saved"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SuccessView save(@RequestBody OrganizationSaveViewReq saveOrg) {

		return orgService.save(saveOrg);
	}

	/**
	 * Delete an organization by Id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Deletes an organization by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public SuccessView deleteById(@PathVariable String id) {

		return orgService.deleteById(id);
	}
}
