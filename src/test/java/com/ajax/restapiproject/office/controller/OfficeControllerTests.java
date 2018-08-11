package com.ajax.restapiproject.office.controller;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ajax.restapiproject.office.service.OfficeService;
import com.ajax.restapiproject.office.view.OfficeIdViewResp;
import com.ajax.restapiproject.office.view.OfficeListViewReq;
import com.ajax.restapiproject.office.view.OfficeListViewResp;
import com.ajax.restapiproject.office.view.OfficeSaveViewReq;
import com.ajax.restapiproject.office.view.OfficeUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Test office controller
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OfficeController.class)
public class OfficeControllerTests {
	
		@Autowired
		private MockMvc mvc;

		@MockBean
		private OfficeService officeService;
		
		/**
		 * Test get by Id request
		 * @throws Exception
		 */
		@Test
		public void getByIdTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.get("/api/office/1")
					.accept(MediaType.APPLICATION_JSON);
			
			OfficeIdViewResp viewResp = new OfficeIdViewResp("1", "Ajax", "Moscow Lenina str", "(495) 000 00 00", "true");
			
			when(officeService.loadById(anyString())).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"id\":\"1\",\"name\":\"Ajax\",\"address\":\"Moscow Lenina str\","
							+ "\"phone\":\"(495) 000 00 00\",\"isActive\":\"true\"}}"))
					.andReturn();
		}
		
		/**
		 * Test get by organization request
		 * @throws Exception
		 */
		@Test
		public void getByOrgTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/office/list")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"orgId\":\"1\"}");
			
			List<OfficeListViewResp> viewResp = Arrays.asList(new OfficeListViewResp("1", "Ajax", "true"));
			
			when(officeService.loadByOrg(Mockito.any(OfficeListViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":[{\"id\":\"1\",\"name\":\"Ajax\",\"isActive\":\"true\"}]}"))
					.andReturn();
		}
		
		/**
		 * Test update office request
		 * @throws Exception
		 */
		@Test
		public void updateTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/office/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":\"1\"}");
				
			SuccessView viewResp = new SuccessView("success");
			
			when(officeService.update(Mockito.any(OfficeUpdateViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
		
		/**
		 * Test save office request
		 * @throws Exception
		 */
		@Test
		public void saveTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/office/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"name\":\"Ajax\"}");
				
			SuccessView viewResp = new SuccessView("success");
			
			when(officeService.save(Mockito.any(OfficeSaveViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
		
		/**
		 * Test delete office request
		 * @throws Exception
		 */
		@Test
		public void deleteByIdTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.get("/api/office/delete/1")
					.contentType(MediaType.APPLICATION_JSON);
				
			SuccessView viewResp = new SuccessView("success");
			
			when(officeService.deleteById(anyString())).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
}
