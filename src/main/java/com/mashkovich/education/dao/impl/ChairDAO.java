package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IChairDao;
import com.mashkovich.education.models.Chair;

public class ChairDAO  extends AbstractJDBC implements IChairDao {
	private static final String sql = "SELECT * FROM Chairs WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Chairs WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Chairs SET name=?, email=?,phone=?";
	private static final String sqlInsert = "INSERT INTO Chairs (name, email, phone) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(ChairDAO.class);
	public Chair getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Chair chair =new Chair();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			chair.setId(rs.getInt("id"));
			chair.setName(rs.getString("name"));
			chair.setEmail(rs.getString("email"));
			chair.setPhone(rs.getString("phone"));
			log.info(chair.getName());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return chair;
	}
	public void update(Chair chair) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, chair.getName());
			ps.setString(2, chair.getEmail());
			ps.setString(3, chair.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Chair chair) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,chair.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Chair chair) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, chair.getName());
			ps.setString(2, chair.getEmail());
			ps.setString(3, chair.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
