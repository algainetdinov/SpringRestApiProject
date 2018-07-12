package com.ajax.restapiproject.doctype.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.view.DictionaryView;

import io.swagger.annotations.ApiOperation;

/**
 * Document types controller
 */
@RestController
@RequestMapping(value = "/api/docs", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocTypeController {
	
	@ApiOperation("Returns a list of existing document types")
	@RequestMapping(value = "/", method= RequestMethod.GET)
    public List<DictionaryView> getDocTypes(){
		
		/**
		 * Since there is no service and DAO yet, initialize view with test data
		 */
		List<DictionaryView> docTypeViews= new ArrayList<DictionaryView>();
		
		DictionaryView dicViewOne = new DictionaryView ("03", "Свидетельство о рождении");
		DictionaryView dicViewTwo = new DictionaryView ("07", "Военный билет");
		DictionaryView dicViewThree = new DictionaryView ("21", "Паспорт гражданина Российской Федерации");		
		
		docTypeViews.add(dicViewOne);
		docTypeViews.add(dicViewTwo);
		docTypeViews.add(dicViewThree);
		return docTypeViews;
    }
}
