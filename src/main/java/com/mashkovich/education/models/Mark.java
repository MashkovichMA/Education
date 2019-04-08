package com.mashkovich.education.models;

public class Mark extends AbstractModel{
	private int mark;
	private Lecture lecture;
	public Mark() {}
	public String toString() {
		return "mark: " + mark + lecture.toString();
	}
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public Lecture getLecture() {
		return lecture;
	}
	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
}
