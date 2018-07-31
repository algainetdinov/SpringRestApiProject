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

/**
 * Document types controller
 */
@RestController
@RequestMapping(value = "/api/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctypeController {
	
	private DoctypeService doctypeService;
	
	@Autowired
	public DoctypeController (DoctypeService doctypeService) {
		this.doctypeService = doctypeService;
	}
	
	@ApiOperation("Returns a list of existing document types")
	@RequestMapping(value = "/", method= RequestMethod.GET)
    public List<DictionaryView> getDoctypes(){
		return doctypeService.findAll();
    }
}
