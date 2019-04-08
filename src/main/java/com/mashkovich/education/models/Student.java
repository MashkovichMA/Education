package com.mashkovich.education.models;

public class Student extends Person {
	private Group group;
	private Mark mark;
	public Student() {}
	public String inf() {
		return "student "+getName()+getSecond_name()+getGender()+getPhone()+group+" "+mark;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Mark getMark() {
		return mark;
	}
	public void setMark(Mark mark) {
		this.mark = mark;
	}
}
