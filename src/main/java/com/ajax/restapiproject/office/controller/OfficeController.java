package com.ajax.restapiproject.office.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;

/**
 *Controller for office
 */
@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {
	
	/**
	 * Since there is no service and DAO yet, initialize view with test data
	 */
	@ApiOperation("Returns lists of offices belonging to specified organization")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OfficeListViewResp> listOffice(@RequestBody OfficeListViewReq office) {
		List<OfficeListViewResp> offices = new ArrayList <OfficeListViewResp>();
		OfficeListViewResp officeOne = new OfficeListViewResp(3L, office.name, office.isActive);
		OfficeListViewResp officeTwo = new OfficeListViewResp(8L, office.name, !office.isActive);
		offices.add(officeOne);
		offices.add(officeTwo);
		return offices;
	}
	
	@ApiOperation("Returns an office specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public OfficeIdViewResp getOfficeById(@PathVariable Long id){
		OfficeIdViewResp office = new OfficeIdViewResp(id, "МАКДОНАЛДС", "УФА Г, ЛЕНИНА УЛ, ДОМ 5", 
				"8 (347) 276-61-76", true);		
        return office;
    }
	
	@ApiOperation("Updates an office with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateOffice(@RequestBody OfficeUpdateViewReq updateOffice){
		SuccessView success = new SuccessView("success");
		return success;
    }
	
	@ApiOperation("Creates an office with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveOrg(@RequestBody OfficeSaveViewReq saveOffice){
		SuccessView success = new SuccessView("success");
		return success;
    }
}
