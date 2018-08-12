package com.ajax.restapiproject.country.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ajax.restapiproject.country.dao.CountryDao;
import com.ajax.restapiproject.country.model.Country;
import com.ajax.restapiproject.view.DictionaryView;

/**
 * Country service implementation
 */
@Service
public class CountryServiceImpl implements CountryService {

	private final CountryDao countryDao;

	/**
	 * Constructor to set final fields
	 * 
	 * @param countryDao
	 */
	@Autowired
	public CountryServiceImpl(CountryDao countryDao) {

		this.countryDao = countryDao;
	}

	/**
	 * Retrieve country by Id
	 */
	@Override
	public Country loadById(Long id) {

		return countryDao.findById(id);
	}

	/**
	 * Retrieve all countries
	 */
	@Override
	public List<DictionaryView> loadAll() {

		List<DictionaryView> countryViews = new ArrayList<DictionaryView>();

		for (Country c : countryDao.findAll()) {

			DictionaryView countryView = new DictionaryView(c.getCode(), c.getName());

			countryViews.add(countryView);
		}

		return countryViews;
	}
}