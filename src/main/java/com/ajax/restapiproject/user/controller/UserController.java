package com.ajax.restapiproject.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * User controller
 */
@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	private UserService userService;

	/**
	 * Constructor to set final fields
	 * 
	 * @param userService
	 */
	@Autowired
	public UserController(UserService userService) {

		this.userService = userService;
	}

	/**
	 * Retrieve list of users by office
	 * 
	 * @param reqView
	 * @return
	 */
	@ApiOperation("Returns list of users belonging to specified office")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public List<UserListViewResp> getByOffice(@RequestBody UserListViewReq reqView) {

		return userService.loadByOffice(reqView);
	}

	/**
	 * Retrieve a user by Id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Returns a user specified by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public UserIdViewResp getById(@PathVariable String id) {

		return userService.loadById(id);
	}

	/**
	 * Update a user
	 * 
	 * @param reqView
	 * @return
	 */
	@ApiOperation("Updates a user with provided data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public SuccessView update(@RequestBody UserUpdateViewReq reqView) {

		return userService.update(reqView);
	}

	/**
	 * Save a user
	 * 
	 * @param reqView
	 * @return
	 */
	@ApiOperation("Creates a user with provided data")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public SuccessView save(@RequestBody UserSaveViewReq reqView) {

		return userService.save(reqView);
	}

	/**
	 * Delete a user by Id
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation("Deletes a user specified by Id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"), @ApiResponse(code = 404, message = "Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public SuccessView deleteById(@PathVariable String id) {

		return userService.deleteById(id);
	}

}
