package com.ajax.restapiproject.user.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.ajax.restapiproject.user.view.UserIdViewResp;
import com.ajax.restapiproject.user.view.UserListViewReq;
import com.ajax.restapiproject.user.view.UserListViewResp;
import com.ajax.restapiproject.user.view.UserSaveViewReq;
import com.ajax.restapiproject.user.view.UserUpdateViewReq;
import com.ajax.restapiproject.view.SuccessView;

public class UserDaoImpl implements UserDao {
	
	private EntityManager em;
	
	@Autowired
	public UserDaoImpl (EntityManager em) {
		this.em=em;
	}
	
	@Override
	public UserIdViewResp findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserListViewResp findByOfficeId(UserListViewReq reqView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessView update(UserUpdateViewReq reqView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SuccessView save(UserSaveViewReq reqView) {
		// TODO Auto-generated method stub
		return null;
	}

}
