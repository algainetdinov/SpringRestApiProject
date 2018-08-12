package com.ajax.restapiproject.organization.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Organization DAO implementation
 */
import com.ajax.restapiproject.organization.model.Organization;

/**
 * Organization DAO implementation
 * 
 * @author Al
 *
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final EntityManager em;

	/**
	 * Constructor to set final fields
	 * 
	 * @param em
	 */
	@Autowired
	OrganizationDaoImpl(EntityManager em) {
		this.em = em;
	}

	/**
	 * Load an organization by provided Id
	 */
	@Override
	public Organization findById(Long id) {

		logger.info("Organization to be loaded by Id: " + id);

		return em.find(Organization.class, id);
	}

	/**
	 * Load all organizations
	 */
	@Override
	public List<Organization> findAll() {

		logger.info("Organizations to be loaded");

		TypedQuery<Organization> query = em.createQuery("SELECT o FROM Organization o", Organization.class);

		return query.getResultList();
	}

	/**
	 * Load an organization by provided name
	 */
	@Override
	public List<Organization> findByName(Organization org) {

		logger.info("Organization to be loaded by name: " + org);

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);

		Root<Organization> orgRoot = cq.from(Organization.class);

		List<Predicate> preList = new ArrayList<Predicate>();

		Predicate namePre = cb.equal(orgRoot.get("name"), org.getName());

		preList.add(namePre);

		if (org.getInn() != null) {
			Predicate innPre = cb.equal(orgRoot.get("inn"), org.getInn());
			preList.add(innPre);
		}

		if (org.getIsActive() != null) {
			Predicate isActivePre = cb.equal(orgRoot.get("isActive"), org.getIsActive());
			preList.add(isActivePre);
		}

		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));

		TypedQuery<Organization> query = em.createQuery(cq);
		return query.getResultList();
	}

	/**
	 * Save provided organization
	 */
	@Override
	public void save(Organization org) {

		logger.info("Organization to be saved: " + org);

		em.persist(org);
	}

	/**
	 * Delete provided organization
	 */
	@Override
	public void delete(Organization org) {

		logger.info("Organization to be deleted: " + org);

		em.remove(org);
	}
}
