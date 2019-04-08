package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IScheduleDao;
import com.mashkovich.education.models.Schedule;

public class ScheduleDAO extends AbstractJDBC implements IScheduleDao {
	private static final String sql = "SELECT * FROM Schedules WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Schedules WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Schedules SET id=?";
	private static final String sqlInsert = "INSERT INTO Schedules (id) VALUES (?)";
	private final static Logger log= Logger.getLogger(ScheduleDAO.class);
	public Schedule getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Schedule schedule =new Schedule();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			schedule.setId(rs.getInt("id"));
			log.info(schedule.getId());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return schedule;
	}
	public void update(Schedule schedule) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){		
			ps.setInt(1, schedule.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Schedule schedule) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,schedule.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Schedule schedule) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setInt(1, schedule.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
