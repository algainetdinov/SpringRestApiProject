package com.ajax.restapiproject.organization.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;

/**
 * Organization controller
 */
@RestController
@RequestMapping(value = "/api/organization", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrganizationController {
	
	/**
	 * Since there is no service and DAO yet, initialize view with test data
	 */
	@ApiOperation("Returns filtered list of organizations")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OrganizationListViewResp> getOrgList(@RequestBody OrganizationListViewReq org) {
		OrganizationListViewResp orgOne = new OrganizationListViewResp(1L, org.name, org.isActive);
		OrganizationListViewResp orgTwo = new OrganizationListViewResp(2L, org.name, !org.isActive);
		List<OrganizationListViewResp> orgList = new ArrayList<OrganizationListViewResp>();
		orgList.add(orgOne);
		orgList.add(orgTwo);
		return orgList;		
	}
	
	@ApiOperation("Returns an organization specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public OrganizationIdViewResp getOrgById(@PathVariable Long id){
		OrganizationIdViewResp org = new OrganizationIdViewResp(id, "Газпром", "ПАО Газпром", "7736050003", "772801001", 
				"117420, МОСКВА Г, НАМЁТКИНА УЛ, ДОМ 16", "8 (495) 719-23-68", true);		
        return org;
    }
	
	@ApiOperation("Updates an organization with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateOrg(@RequestBody OrganizationUpdateViewReq updateOrg){
		SuccessView success = new SuccessView("success", updateOrg.toString());
		return success;
    }
	
	@ApiOperation("Creates an organization with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveOrg(@RequestBody OrganizationSaveViewReq saveOrg){
		SuccessView success = new SuccessView("success", saveOrg.toString());
		return success;
    }
}
