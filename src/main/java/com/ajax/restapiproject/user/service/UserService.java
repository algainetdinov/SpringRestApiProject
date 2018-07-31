package com.ajax.restapiproject.user.service;

import java.util.List;

import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

/**
 * User service
 * @author Al
 *
 */
public interface UserService {
	
	UserIdViewResp loadById(String id);
	
	List<UserListViewResp> loadByOffice(UserListViewReq reqView);
	
	SuccessView save (UserSaveViewReq reqView);
	
	SuccessView update (UserUpdateViewReq reqView);
	
	SuccessView deleteById (String id);
}
