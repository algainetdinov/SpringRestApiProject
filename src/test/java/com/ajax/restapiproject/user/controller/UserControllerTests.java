package com.ajax.restapiproject.user.controller;

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

import com.ajax.restapiproject.user.service.UserService;
import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * Test user controller
 * @author Al
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
	
		@Autowired
		private MockMvc mvc;

		@MockBean
		private UserService userService;
		
		/**
		 * Test get by Id request
		 * @throws Exception
		 */
		@Test
		public void getByIdTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.get("/api/user/1")
					.accept(MediaType.APPLICATION_JSON);
			
			UserIdViewResp viewResp = new UserIdViewResp("1", "James", "Brown", "Tom", "worker", "1", 
					"1234", "01", "Passport", "11 11", "11.12.2015", "Russia", "02", "true");
			
			when(userService.loadById(anyString())).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"id\":\"1\",\"firstName\":\"James\",\"lastName\":\"Brown\",\"middleName\":\"Tom\""
							+ ",\"position\":\"worker\",\"officeId\":\"1\",\"phone\":\"1234\",\"docCode\":\"01\",\"docName\":\"Passport\","
							+ "\"docNumber\":\"11 11\",\"docDate\":\"11.12.2015\",\"citizenshipName\":\"Russia\",\"citizenshipCode\":\"02\","
							+ "\"isIdentified\":\"true\"}}"))
					.andReturn();
		}
		
		/**
		 * Test get by Office request
		 * @throws Exception
		 */
		@Test
		public void getByOfficeTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/user/list")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"officeId\":\"1\"}");
			
			List<UserListViewResp> viewResp = Arrays.asList(new UserListViewResp("1", "James", "Brown", "Tom", "worker"));
			
			when(userService.loadByOffice(Mockito.any(UserListViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":[{\"id\":\"1\",\"firstName\":\"James\",\"lastName\":\"Brown\","
							+ "\"middleName\":\"Tom\",\"position\":\"worker\"}]}"))
					.andReturn();
		}
		
		/**
		 * Test update user request
		 * @throws Exception
		 */
		@Test
		public void updateTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/user/update")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"id\":\"1\"}");
				
			SuccessView viewResp = new SuccessView("success");
			
			when(userService.update(Mockito.any(UserUpdateViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
		
		/**
		 * Test save user request
		 * @throws Exception
		 */
		@Test
		public void saveTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.post("/api/user/save")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{\"firstName\":\"John\"}");
				
			SuccessView viewResp = new SuccessView("success");
			
			when(userService.save(Mockito.any(UserSaveViewReq.class))).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
		
		/**
		 * Test delete user request
		 * @throws Exception
		 */
		@Test
		public void deleteByIdTest() throws Exception {
			
			RequestBuilder request = MockMvcRequestBuilders
					.get("/api/user/delete/1")
					.contentType(MediaType.APPLICATION_JSON);
				
			SuccessView viewResp = new SuccessView("success");
			
			when(userService.deleteById(anyString())).thenReturn(viewResp);
			
			mvc.perform(request)
					.andExpect(status().isOk())
					.andExpect(content().json("{\"data\":{\"result\":\"success\"}}"))
					.andReturn();
		}
}
