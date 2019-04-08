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
import com.mashkovich.education.dao.IProfessorDao;
import com.mashkovich.education.models.Professor;

public class ProfessorDAO extends AbstractJDBC implements IProfessorDao {
	private static final String sql = "SELECT * FROM Professors WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Professor WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Professor SET name=?, second_name=?,gender=?,date_of_birth,phone=?";
	private static final String sqlInsert = "INSERT INTO Professor ( name,second_name,gender,date_of_birth,phone) VALUES (?,?,?,?,?)";
	private final static Logger log= Logger.getLogger(ProfessorDAO.class);
	public Professor getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Professor professor =new Professor();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			professor.setId(rs.getInt("id"));
			professor.setName(rs.getString("name"));
			professor.setSecond_name(rs.getString("second_name"));
			professor.setGender(rs.getString("gender"));
			professor.setPhone(rs.getString("phone"));
			professor.setDate_of_birth(rs.getDate("date_of_birth"));
			log.info(professor.getName());
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return professor;
	}
	public void update(Professor professor) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){	
			ps.setString(1, professor.getName());
			ps.setString(2, professor.getSecond_name());
			ps.setString(3, professor.getGender());
			ps.setDate(4, (Date) professor.getDate_of_birth());
			ps.setString(5, professor.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Professor professor) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,professor.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Professor professor) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, professor.getName());
			ps.setString(2, professor.getSecond_name());
			ps.setString(3, professor.getGender());
			ps.setDate(4, (Date) professor.getDate_of_birth());
			ps.setString(5, professor.getPhone());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
