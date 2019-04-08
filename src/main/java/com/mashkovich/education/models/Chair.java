package com.mashkovich.education.models;

public class Chair extends AbstractModel {
	private String name;
	private String email;
	private String phone;
	private Faculty faculty;
	public Chair() {}
	public String toString() {
		return "chair: " + name + "email: " + email + "phone: " + phone + faculty;
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
	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
}
