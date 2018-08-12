package com.ajax.restapiproject.user.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.user.model.User;

/**
 * User DAO implementation
 * 
 * @author Al
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private EntityManager em;

	/**
	 * Constructor to set final fields
	 * 
	 * @param em
	 */
	@Autowired
	public UserDaoImpl(EntityManager em) {

		this.em = em;
	}

	/**
	 * Find user by Id
	 */
	@Override
	public User findById(Long id) {

		return em.find(User.class, id);
	}

	/**
	 * Retrieve list of users
	 */
	@Override
	public List<User> findAll() {

		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);

		return query.getResultList();
	}

	/**
	 * Retrieve user by office
	 */
	@Override
	public List<User> findByOffice(User user) {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<User> cq = cb.createQuery(User.class);

		Root<User> userRoot = cq.from(User.class);

		List<Predicate> preList = new ArrayList<>();

		Predicate officePre = cb.equal(userRoot.get("office"), user.getOffice());

		preList.add(officePre);

		if (user.getFirstName() != null) {
			Predicate firstNamePre = cb.equal(userRoot.get("firstName"), user.getFirstName());
			preList.add(firstNamePre);
		}

		if (user.getLastName() != null) {
			Predicate lastNamePre = cb.equal(userRoot.get("lastName"), user.getLastName());
			preList.add(lastNamePre);
		}

		if (StringUtils.isNotBlank(user.getMiddleName())) {
			Predicate middleNamePre = cb.equal(userRoot.get("middleName"), user.getMiddleName());
			preList.add(middleNamePre);
		}

		if (user.getDoc() != null) {
			Predicate docTypePre = cb.equal(userRoot.get("doc").get("type"), user.getDoc().getType());
			preList.add(docTypePre);
		}

		if (StringUtils.isNotBlank(user.getPosition())) {
			Predicate positionPre = cb.equal(userRoot.get("position"), user.getPosition());
			preList.add(positionPre);
		}

		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));

		TypedQuery<User> query = em.createQuery(cq);

		return query.getResultList();
	}

	/**
	 * Save user
	 */
	@Override
	public void save(User user) {

		logger.info("User to be saved: " + user);

		em.persist(user);
	}

	/**
	 * Delete user
	 */
	@Override
	public void delete(User user) {

		logger.info("User to be deleted: " + user.getId());

		em.remove(user);
	}
}
