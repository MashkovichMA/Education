package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IGroupDao;
import com.mashkovich.education.models.Group;

public class GroupDAO extends AbstractJDBC implements IGroupDao {
	private static final String sql = "SELECT * FROM Groups WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Groups WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Groups SET number=?";
	private static final String sqlInsert = "INSERT INTO Groups (number) VALUES (?)";
	private final static Logger log= Logger.getLogger(GroupDAO.class);
	public Group getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Group group =new Group();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			group.setId(rs.getInt("id"));
			group.setNumber(rs.getString("number"));
			log.info(group.getNumber());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return group;
	}
	public void update(Group group) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, group.getNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Group group) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,group.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Group group) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){
			ps.setString(1, group.getNumber());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
