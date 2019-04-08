package com.mashkovich.education.models;

public class City extends AbstractModel {
	private String city;
	private Country country;
	public City() {}
	public String toString() {
		return city+" "+country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
}
