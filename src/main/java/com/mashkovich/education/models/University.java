package com.mashkovich.education.models;

public class University extends AbstractModel{
	private String name;
	private Address address;
	public University() {}
	public String toString() {
		return "university: " + name+" " + "address: " + address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}

