package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IFacultyDao;
import com.mashkovich.education.models.Faculty;

public class FacultyDAO extends AbstractJDBC implements IFacultyDao {
	private static final String sql = "SELECT * FROM Faculties WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Faculties WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Faculties SET name=?, email=?,phone=?";
	private static final String sqlInsert = "INSERT INTO Faculties (name, email, phone) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(FacultyDAO.class);
	public Faculty getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Faculty faculty =new Faculty();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			faculty.setId(rs.getInt("id"));
			faculty.setName(rs.getString("name"));
			faculty.setEmail(rs.getString("email"));
			faculty.setPhone(rs.getString("phone"));
			log.info(faculty.getName());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return faculty;
	}
	public void update(Faculty faculty) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){
			ps.setString(1, faculty.getName());
			ps.setString(2, faculty.getEmail());
			ps.setString(3, faculty.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Faculty faculty) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,faculty.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Faculty faculty) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){
			ps.setString(1, faculty.getName());
			ps.setString(2, faculty.getEmail());
			ps.setString(3, faculty.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
