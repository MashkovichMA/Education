package com.mashkovich.education.models;

import java.util.Date;

public abstract class Person extends AbstractModel {
	private String name;
	private String second_name;
	private String gender;
	private Date date_of_birth;
	private String phone;
	public Person() {}
	public String toString() {
		return "name: " + name+" " + "second name: " + second_name+" " + "gender: " + gender+" "+ "date of birth: " + date_of_birth+ "phone: " + phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSecond_name() {
		return second_name;
	}
	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
