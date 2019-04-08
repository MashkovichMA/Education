package com.mashkovich.education.models;

public class Faculty extends AbstractModel {
	private String name;
	private String email;
	private String phone;
	private University university;
	public Faculty() {}
	public String toString() {
		return "faculty: " + name + "email: " + email + "phone: " + phone + university;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}

}
