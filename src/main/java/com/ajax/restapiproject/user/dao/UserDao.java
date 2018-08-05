package com.ajax.restapiproject.user.dao;

import java.util.List;

import com.ajax.restapiproject.user.model.User;

/**
 * User DAO
 * @author Al
 *
 */
public interface UserDao {
	
	User loadById(Long id);
	
	List<User> findByOfficeId(User user);
	
	List<User> findAll();
		
	void save(User user);
	
	void delete(User user);
}
