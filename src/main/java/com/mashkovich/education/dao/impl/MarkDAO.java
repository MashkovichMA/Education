package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IMarkDao;
import com.mashkovich.education.models.Mark;

public class MarkDAO extends AbstractJDBC implements IMarkDao{
	private static final String sql = "SELECT * FROM Marks WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Marks WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Marks SET mark=?";
	private static final String sqlInsert = "INSERT INTO Marks (mark) VALUES (?)";
	private final static Logger log= Logger.getLogger(MarkDAO.class);
	public Mark getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Mark mark =new Mark();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			mark.setId(rs.getInt("id"));
			mark.setMark(rs.getInt("mark"));
			log.info(mark.getId());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return mark;
	}
	public void update(Mark mark) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){
			ps.setInt(1, mark.getMark());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Mark mark) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,mark.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Mark mark) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setInt(1, mark.getMark());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
