package com.mashkovich.education.models;

public class Professor extends Person {
	private Chair chair;
	public Professor() {}
	public String toString() {
		return getName()+getSecond_name()+getGender()+getPhone() + chair;
	}
	public Chair getChair() {
		return chair;
	}
	public void setChair(Chair chair) {
		this.chair = chair;
	}
}
