package com.ajax.restapiproject.user.service;

import java.util.List;

import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * User service interface
 * 
 * @author Al
 *
 */
public interface UserService {

	/**
	 * Find user by Id
	 * 
	 * @param id
	 * @return
	 */
	UserIdViewResp loadById(String id);

	/**
	 * Retrieve users by office
	 * 
	 * @param reqView
	 * @return
	 */
	List<UserListViewResp> loadByOffice(UserListViewReq reqView);

	/**
	 * Save user
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView save(UserSaveViewReq reqView);

	/**
	 * Update user
	 * 
	 * @param reqView
	 * @return
	 */
	SuccessView update(UserUpdateViewReq reqView);

	/**
	 * Delete user
	 * 
	 * @param id
	 * @return
	 */
	SuccessView deleteById(String id);
}
