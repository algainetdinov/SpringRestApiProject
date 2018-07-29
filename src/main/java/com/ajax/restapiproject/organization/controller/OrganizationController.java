package com.ajax.restapiproject.organization.controller;

import java.util.List;

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
	
	public OrganizationController (OrganizationService orgService) {
		this.orgService=orgService;
	}
	/**
	 * Since there is no service and DAO yet, initialize view with test data
	 */
	@ApiOperation("Returns filtered list of organizations")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OrganizationListViewResp> getOrgList(@RequestBody OrganizationListViewReq org) {
		return orgService.loadByName(org);		
	}
	
	@ApiOperation("Returns an organization specified by ID")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public OrganizationIdViewResp getOrgById(@PathVariable String id){
		return orgService.loadById(id);
    }
	
	@ApiOperation("Updates an organization with provided data")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value = "/update", method= RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
    public SuccessView updateOrg(@RequestBody OrganizationUpdateViewReq updateOrg){
		return orgService.update(updateOrg);
    }
	
	@ApiOperation("Creates an organization with provided data")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = "Saved"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@ResponseStatus(value = HttpStatus.CREATED)
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveOrg(@RequestBody OrganizationSaveViewReq saveOrg){
		return orgService.save(saveOrg);
    }
	
	@ApiOperation("Deletes an organization by ID")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/delete/{id}", method= RequestMethod.GET)
    public SuccessView deleteOrgById(@PathVariable String id){
		return orgService.deleteById(id);
    }
}
