package com.mashkovich.education.models;

public class Schedule extends AbstractModel {
	private Group group;
	private Lecture lecture;
	public Schedule() {}
	public String inf() {
		return "schedule "+group+" "+lecture;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Lecture getLecture() {
		return lecture;
	}
	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
}
