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

/**
 *Controller for office
 */
@RestController
@RequestMapping(value = "/api/office", produces = MediaType.APPLICATION_JSON_VALUE)
public class OfficeController {
	
	private OfficeService officeService;
	
	@Autowired
	public OfficeController (OfficeService officeService) {
		this.officeService = officeService;
	}
	
	@ApiOperation("Returns lists of offices belonging to specified organization")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<OfficeListViewResp> listOffice(@RequestBody OfficeListViewReq reqView) {
		return officeService.loadByOrg(reqView);
	}
	
	@ApiOperation("Returns an office specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public OfficeIdViewResp getOfficeById(@PathVariable String id){
		return officeService.loadById(id);
	}
	
	@ApiOperation("Updates an office with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateOffice(@RequestBody OfficeUpdateViewReq reqView){
		return officeService.update(reqView);
    }
	
	@ApiOperation("Creates an office with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveOrg(@RequestBody OfficeSaveViewReq reqView){
		return officeService.save(reqView);
    }
	
	@ApiOperation("Deletes an office by Id")
	@RequestMapping(value = "/delete/{id}", method= RequestMethod.GET)
    public SuccessView delete(@PathVariable String id){
		return officeService.deleteById(id);
    }
}
