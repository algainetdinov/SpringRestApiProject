package com.ajax.restapiproject.doctype.controller;

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

import com.ajax.restapiproject.doctype.service.DoctypeService;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Test doctype controller
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DoctypeController.class)
public class DoctypeControllerTests {
	
		@MockBean
		private DoctypeService doctypeService;
	
		@Autowired
		private MockMvc mvc;
		
		/**
		 * Test get list of doctypes
		 * @throws Exception
		 */
		@Test
		public void loadAllTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.get("/api/docs/");
			
			List<DictionaryView> viewResp = Arrays.asList(new DictionaryView("10", "Birth certificate"),
					new DictionaryView("11", "Passport"));
			
			when(doctypeService.loadAll()).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":[{\"name\":\"Birth certificate\",\"code\":\"10\"},"
							+ "{\"name\":\"Passport\",\"code\":\"11\"}]}"))
					.andReturn();
		}
}
