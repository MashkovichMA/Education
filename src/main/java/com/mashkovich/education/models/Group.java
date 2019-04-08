package com.mashkovich.education.models;

public class Group extends AbstractModel{
	private String number;
	private Speciality speciality;
	public Group() {}
	public String toString() {
		return "group: " + number+speciality.toString();
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Speciality getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}	
}
