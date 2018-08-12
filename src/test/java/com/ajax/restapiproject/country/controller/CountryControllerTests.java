package com.ajax.restapiproject.country.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ajax.restapiproject.country.service.CountryService;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Test country controller
 * 
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CountryController.class)
public class CountryControllerTests {

	@MockBean
	private CountryService countryService;

	@Autowired
	private MockMvc mvc;

	/**
	 * Test get list of countries
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/countries/");

		List<DictionaryView> viewResp = Arrays.asList(new DictionaryView("643", "Российская Федерация"),
				new DictionaryView("111", "Трансильвания"));

		when(countryService.loadAll()).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"data\":[{\"name\":\"Российская Федерация\",\"code\":\"643\"},"
						+ "{\"name\":\"Трансильвания\",\"code\":\"111\"}]}"))
				.andReturn();
	}
}
