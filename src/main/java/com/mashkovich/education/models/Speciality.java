package com.mashkovich.education.models;

public class Speciality extends AbstractModel {
	private String name;
	private String code;
	private Chair chair;
	public Speciality() {}
	public String toString() {
		return "speciality: " + name + "code: " + code +chair;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Chair getChair() {
		return chair;
	}
	public void setChair(Chair chair) {
		this.chair = chair;
	}
}
