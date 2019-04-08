package com.mashkovich.education.services;

import org.apache.log4j.Logger;

import com.mashkovich.education.dao.IDao;
import com.mashkovich.education.dao.impl.UniversityDAO;
import com.mashkovich.education.models.Address;
import com.mashkovich.education.models.University;

public class UniversityService {
	private IDao<University> u = new UniversityDAO();
	private final static Logger log= Logger.getLogger(UniversityService.class);
	public void getInf(int id) {
		University university = u.getByID(id);
		AddressService as = new AddressService();
		Address address = as.getInf(university.getAddress().getId());
		university.setAddress(address);
		log.info(university.toString());
	}
}
