package com.ajax.restapiproject.organization.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.organization.service.OrganizationService;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;

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
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OrganizationListViewResp> getOrgList(@RequestBody OrganizationListViewReq org) {
		return orgService.loadByName(org);		
	}
	
	@ApiOperation("Returns an organization specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public OrganizationIdViewResp getOrgById(@PathVariable String id){
		return orgService.loadById(id);
    }
	
	/*@ApiOperation("Updates an organization with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateOrg(@RequestBody OrganizationUpdateViewReq updateOrg){
		return orgService.loadByName(name, inn, isActive)
    }*/
	
	@ApiOperation("Creates an organization with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveOrg(@RequestBody OrganizationSaveViewReq saveOrg){
		SuccessView success = new SuccessView("success", saveOrg.toString());
		return success;
    }
}
