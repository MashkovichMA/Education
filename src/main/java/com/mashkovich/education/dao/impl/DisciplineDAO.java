package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IDisciplineDao;
import com.mashkovich.education.models.Discipline;

public class DisciplineDAO  extends AbstractJDBC implements IDisciplineDao {
	private static final String sql = "SELECT * FROM Disciplines WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Disciplines WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Disciplines SET name=?";
	private static final String sqlInsert = "INSERT INTO Disciplines (name) VALUES (?)";
	private final static Logger log= Logger.getLogger(DisciplineDAO.class);
	public Discipline getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Discipline discipline =new Discipline();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			discipline.setId(rs.getInt("id"));
			discipline.setName(rs.getString("name"));
			log.info(discipline.getName());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return discipline;
	}
	public void update(Discipline discipline) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){		
			ps.setString(1, discipline.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Discipline discipline) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,discipline.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Discipline discipline) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, discipline.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
