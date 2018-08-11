package com.ajax.restapiproject.user.dao;

import java.util.List;

import com.ajax.restapiproject.user.model.User;

/**
 * User DAO interface
 * @author Al
 *
 */
public interface UserDao {
	
	/**
	 * Find user by Id
	 * @param id
	 * @return
	 */
	User findById(Long id);
	
	/**
	 * Find user by office
	 * @param user
	 * @return
	 */
	List<User> findByOffice(User user);
	
	/**
	 * Retrieve all users
	 * @return
	 */
	List<User> findAll();
		
	/**
	 * Save user
	 * @param user
	 */
	void save(User user);
	
	/**
	 * Delete user
	 * @param user
	 */
	void delete(User user);
}
