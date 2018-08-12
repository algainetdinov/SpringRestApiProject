package com.ajax.restapiproject.organization.controller;

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

import com.ajax.restapiproject.organization.service.OrganizationService;
import com.ajax.restapiproject.organization.view.OrganizationIdViewResp;
import com.ajax.restapiproject.organization.view.OrganizationListViewReq;
import com.ajax.restapiproject.organization.view.OrganizationListViewResp;
import com.ajax.restapiproject.organization.view.OrganizationSaveViewReq;
import com.ajax.restapiproject.organization.view.OrganizationUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Test organization controller
 * 
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(OrganizationController.class)
public class OrganizationControllerTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private OrganizationService organizationService;

	/**
	 * Test get by Id request
	 * 
	 * @throws Exception
	 */
	@Test
	public void getByIdTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/organization/1").accept(MediaType.APPLICATION_JSON);

		OrganizationIdViewResp viewResp = new OrganizationIdViewResp("1", "Ajax", "Ajax inc.", "1111111111",
				"222222222", "Moscow Lenina str", "(495) 000 00 00", "true");

		when(organizationService.loadById(anyString())).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"data\":{\"id\":\"1\",\"name\":\"Ajax\",\"address\":\"Moscow Lenina str\","
						+ "\"phone\":\"(495) 000 00 00\",\"isActive\":\"true\"}}"))
				.andReturn();
	}

	/**
	 * Test get list of organizations
	 * 
	 * @throws Exception
	 */
	@Test
	public void getByNameTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/api/organization/list")
				.contentType(MediaType.APPLICATION_JSON).content("{\"name\":\"Ajax\"}");

		List<OrganizationListViewResp> viewResp = Arrays.asList(new OrganizationListViewResp("1", "Ajax", "true"));

		when(organizationService.loadByName(Mockito.any(OrganizationListViewReq.class))).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().isOk())
				.andExpect(content().json("{\"data\":[{\"id\":\"1\",\"name\":\"Ajax\",\"isActive\":\"true\"}]}"))
				.andReturn();
	}

	/**
	 * Test update organization request
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/api/organization/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\":\"1\",\"name\":\"Ajax\",\"fullName\":\"Ajax inc.\",\"inn\":\"1111111111\","
						+ "\"kpp\":\"22222222\",\"address\":\"Moscow Lenina str\"}");

		SuccessView viewResp = new SuccessView("success");

		when(organizationService.update(Mockito.any(OrganizationUpdateViewReq.class))).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
				.andReturn();
	}

	/**
	 * Test save organization request
	 * 
	 * @throws Exception
	 */
	@Test
	public void saveTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/api/organization/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"Ajax\",\"fullName\":\"Ajax inc.\",\"inn\":\"1111111111\","
						+ "\"kpp\":\"22222222\",\"address\":\"Moscow Lenina str\"}");

		SuccessView viewResp = new SuccessView("success");

		when(organizationService.save(Mockito.any(OrganizationSaveViewReq.class))).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().is2xxSuccessful())
				.andExpect(content().json("{\"data\":{\"result\":\"success\"}}")).andReturn();
	}

	/**
	 * Test delete organization request
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdTest() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.get("/api/organization/delete/1")
				.contentType(MediaType.APPLICATION_JSON);

		SuccessView viewResp = new SuccessView("success");

		when(organizationService.deleteById(anyString())).thenReturn(viewResp);

		mvc.perform(request).andExpect(status().isOk()).andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
				.andReturn();
	}
}
