package com.mashkovich.education.services;

import org.apache.log4j.Logger;

import com.mashkovich.education.dao.IDao;
import com.mashkovich.education.dao.impl.AddressDAO;
import com.mashkovich.education.models.Address;

public class AddressService {
	private IDao<Address> a = new AddressDAO();
	private final static Logger log= Logger.getLogger(AddressService.class);
	public Address getInf(int id) {
		Address address = a.getByID(id);
		CityService cs = new CityService();
		address.setCity(cs.getInf(address.getCity().getId()));
		log.info(address.toString());
		return address;
	}
}
