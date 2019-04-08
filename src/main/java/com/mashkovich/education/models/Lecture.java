package com.mashkovich.education.models;

public class Lecture extends AbstractModel {
	private Professor professor;
	private Discipline discipline;
	public Lecture() {}
	public String toString() {
		return "lecture: " + professor+" "+ discipline ;
	}
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public Discipline getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}
}
