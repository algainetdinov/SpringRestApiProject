package com.ajax.restapiproject.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

import io.swagger.annotations.ApiOperation;

/**
 * User controller
 */
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	/**
	 * Since there is no service and DAO yet, initialize view with test data
	 */
	@ApiOperation("Returns list of users belonging to specified office")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<UserListViewResp> listOffice(@RequestBody UserListViewReq user) {
		List<UserListViewResp> users = new ArrayList <UserListViewResp>();
		UserListViewResp userOne = new UserListViewResp(4L, user.firstName, user.lastName, 
				user.middleName, user.position);
		users.add(userOne);
		return users;
	}
	
	@ApiOperation("Returns a user specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserIdViewResp getUserById(@PathVariable Long id){
		UserIdViewResp user = new UserIdViewResp(id, "Остап", "Бендер", 
				"Берта Мария", "Разработчик", "8 (347) 276-61-76", "Паспорт РСФСР", 
				"1111-11", "22.11.2010", "РСФСР", "001", true);		
        return user;
    }
	
	@ApiOperation("Updates a user with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateUser(@RequestBody UserUpdateViewReq updateUser){
		SuccessView success = new SuccessView("success");
		return success;
    }
	
	@ApiOperation("Creates a user with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveUser(@RequestBody UserSaveViewReq saveUser){
		SuccessView success = new SuccessView("success");
		return success;
    }
}
