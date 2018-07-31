package com.ajax.restapiproject.user.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ajax.restapiproject.user.service.UserService;
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
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@ApiOperation("Returns list of users belonging to specified office")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<UserListViewResp> listByOffice(@RequestBody UserListViewReq reqView) {
		return userService.loadByOffice(reqView);
	}
	
	@ApiOperation("Returns a user specified by ID")
	@RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public UserIdViewResp getUserById(@PathVariable String id){
		return userService.loadById(id);
    }
	
	@ApiOperation("Updates a user with provided data")
	@RequestMapping(value = "/update", method= RequestMethod.POST)
    public SuccessView updateUser(@RequestBody UserUpdateViewReq reqView){
		return userService.update(reqView);
    }
	
	@ApiOperation("Creates a user with provided data")
	@RequestMapping(value = "/save", method= RequestMethod.POST)
    public SuccessView saveUser(@RequestBody UserSaveViewReq reqView){
		return userService.save(reqView);
    }
	
	@ApiOperation("Deletes a user specified by Id")
	@RequestMapping(value = "delete/{id}", method= RequestMethod.GET)
    public SuccessView deleteUserById(@PathVariable String id){
		return userService.deleteById(id);
    }
	
	
}
