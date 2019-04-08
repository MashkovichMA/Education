package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IStudentDao;
import com.mashkovich.education.models.Student;

public class StudentDAO extends AbstractJDBC implements IStudentDao {
	private static final String sql = "SELECT * FROM Students WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Students WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Students SET name=?, second_name=?,gender=?,date_of_birth,phone=?";
	private static final String sqlInsert = "INSERT INTO Students (name,second_name,gender,date_of_birth,phone) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(StudentDAO.class);
	public Student getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Student student =new Student();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			student.setId(rs.getInt("id"));
			student.setName(rs.getString("name"));
			student.setSecond_name(rs.getString("second_name"));
			student.setGender(rs.getString("gender"));
			student.setPhone(rs.getString("phone"));
			log.info(student.getName());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return student;
	}
	public void update(Student student) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, student.getName());
			ps.setString(2, student.getSecond_name());
			ps.setString(3, student.getGender());
			ps.setDate(4, (Date) student.getDate_of_birth());
			ps.setString(5, student.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Student student) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,student.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Student student) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, student.getName());
			ps.setString(2, student.getSecond_name());
			ps.setString(3, student.getGender());
			ps.setDate(4, (Date) student.getDate_of_birth());
			ps.setString(5, student.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}

}
