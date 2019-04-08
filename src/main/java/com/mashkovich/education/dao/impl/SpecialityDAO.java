package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.ISpecialityDao;
import com.mashkovich.education.models.Speciality;

public class SpecialityDAO extends AbstractJDBC implements ISpecialityDao {
	private static final String sql = "SELECT * FROM Specialities WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Specialities WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Specialities SET name=?, code=?";
	private static final String sqlInsert = "INSERT INTO Specialities (name, code) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(SpecialityDAO.class);
	public Speciality getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Speciality speciality =new Speciality();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			speciality.setId(rs.getInt("id"));
			speciality.setName(rs.getString("name"));
			speciality.setCode(rs.getString("code"));
			log.info(speciality.getId());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return speciality;
	}
	public void update(Speciality speciality) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, speciality.getName());
			ps.setString(2, speciality.getCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Speciality speciality) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,speciality.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Speciality speciality) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, speciality.getName());
			ps.setString(2, speciality.getCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
