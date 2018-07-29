package com.ajax.restapiproject.country.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ajax.restapiproject.country.model.Country;

/**
 * Country DAO implementation
 */
@Repository
public class CountryDaoImpl implements CountryDao{
	
	private final EntityManager em;
	
	@Autowired
	public CountryDaoImpl(EntityManager em) {
		this.em=em;
	}
	
	/**
	 * Implement logic for a getting list of countries through entity manager
	 */
	@Override
	public List<Country> getCountries() {
		TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c", Country.class);
        return query.getResultList();
	}
	
	/**
	 * Implement logic for a getting country with specified code (unique value)
	 */
	@Override
	public Country findByCode (String code) {
		TypedQuery<Country> query = em.createQuery("SELECT c FROM Country c WHERE c.code = :code", Country.class);
		Country country = query.getSingleResult();
		return country;
	}
	
	/**
	 * Implement logic for a getting country by ID
	 */
	@Override
	public Country findById (Long id) {
		return em.find(Country.class, id);
	}	
}
