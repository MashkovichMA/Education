package com.mashkovich.education.models;

public class Discipline extends AbstractModel {
	private String name;
	public Discipline() {}
	public String toString() {
		return "discipline: " + name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
