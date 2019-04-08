package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.ILectureDao;
import com.mashkovich.education.models.Lecture;

public class LectureDAO extends AbstractJDBC implements ILectureDao {
	private static final String sql = "SELECT * FROM Lectures WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Lectures WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Lectures SET id=?";
	private static final String sqlInsert = "INSERT INTO Lectures (id) VALUES (?)";
	private final static Logger log= Logger.getLogger(LectureDAO.class);
	public Lecture getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Lecture lecture =new Lecture();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			lecture.setId(rs.getInt("id"));
			log.info(lecture.getId());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return lecture;
	}
	public void update(Lecture lecture) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){		
			ps.setInt(1, lecture.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Lecture lecture) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,lecture.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Lecture lecture) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setInt(1, lecture.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}