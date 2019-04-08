package com.mashkovich.education.models;

public abstract class AbstractModel {
	private int id;
	public String toString() {
		return "id: "+ id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
