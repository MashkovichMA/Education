package com.mashkovich.education.services;


import org.apache.log4j.Logger;

import com.mashkovich.education.dao.IDao;
import com.mashkovich.education.dao.impl.CityDAO;
import com.mashkovich.education.dao.impl.CountryDAO;
import com.mashkovich.education.models.City;
import com.mashkovich.education.models.Country;

public class CityService {
	private IDao<City> c = new CityDAO();
	private IDao<Country>country=new CountryDAO();
	private final static Logger log= Logger.getLogger(CityService.class);
	public City getInf(int id) {
		City city = c.getByID(id);
		Country cou = country.getByID(city.getCountry().getId());
		city.setCountry(cou);
		log.info(city.toString());
		return city;
	}
	}
