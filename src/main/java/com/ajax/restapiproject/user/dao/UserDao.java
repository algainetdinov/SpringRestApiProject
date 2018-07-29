package com.ajax.restapiproject.user.dao;

import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * User DAO
 * @author Al
 *
 */
public interface UserDao {

	UserIdViewResp findById(Long id);
	
	UserListViewResp findByOfficeId(UserListViewReq reqView);
	
	SuccessView update(UserUpdateViewReq reqView);
	
	SuccessView save(UserSaveViewReq reqView);
}
