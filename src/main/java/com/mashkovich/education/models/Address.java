package com.mashkovich.education.models;

public class Address extends AbstractModel {
	private Country country;
	private City city;
	private String street;
	private int house;
	private int flat;
	public Address() {}
	public String toString() {
		return "city: " + city+" "+ "street: " + street+" "+ "house: " + house;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public City getCity() {
		return city;
	}
	public City setCity(City city) {
		return this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getHouse() {
		return house;
	}
	public void setHouse(int house) {
		this.house = house;
	}
	public int getFlat() {
		return flat;
	}
	public void setFlat(int flat) {
		this.flat = flat;
	}
}
