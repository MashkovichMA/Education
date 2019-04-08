package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IUniversityDao;
import com.mashkovich.education.models.Address;
import com.mashkovich.education.models.University;

public class UniversityDAO extends AbstractJDBC implements IUniversityDao {
	private static final String sql = "SELECT * FROM Universities WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Universities WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Universities SET name=?";
	private static final String sqlInsert = "INSERT INTO Universities (name) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(UniversityDAO.class);
	public University getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		University university =new University();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			university.setId(rs.getInt("id"));
			university.setName(rs.getString("name"));
			Address address = new Address();
			address.setId(rs.getInt("Addresses_id"));
			university.setAddress(address);
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return university;
	}
	public void update(University university) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, university.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(University university) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,university.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(University university) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){
			ps.setString(1, university.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
}
}
