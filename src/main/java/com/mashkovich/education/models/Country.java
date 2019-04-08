package com.mashkovich.education.models;

public class Country extends AbstractModel {
	private String country;
	public Country() {}
	public String toString() {
		return country;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
