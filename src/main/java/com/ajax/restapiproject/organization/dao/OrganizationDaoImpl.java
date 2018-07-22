package com.ajax.restapiproject.organization.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Organization DAO implementation
 */
import com.ajax.restapiproject.organization.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao{
	
	private final EntityManager em;
	
	@Autowired
	OrganizationDaoImpl (EntityManager em) {
		this.em=em;
	}

	@Override
	public Organization loadById(Long id) {
		return em.find(Organization.class, id);
	}

	@Override
	public List<Organization> loadByName(Organization org) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Organization> cq = cb.createQuery(Organization.class);
		Root<Organization> orgRoot = cq.from(Organization.class);
		List<Predicate> preList = new ArrayList<Predicate>();
		Predicate namePre = cb.equal(orgRoot.get("name"), org.getName());
		preList.add(namePre);
		
		if (!org.getInn().isEmpty()) {
			Predicate innPre = cb.equal(orgRoot.get("inn"), org.getInn());
			preList.add(innPre);
		}
		
		if (org.getIsActive()!=null) {
			Predicate isActivePre = cb.equal(orgRoot.get("name"), org.getIsActive());
			preList.add(isActivePre);
		}
		
		cq.where(cb.and(preList.toArray(new Predicate[preList.size()])));
		
		TypedQuery<Organization> query = em.createQuery(cq);
		return query.getResultList();	
	}

	@Override
	public void save(Organization org) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Organization org) {
		// TODO Auto-generated method stub
		
	}

}
