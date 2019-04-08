package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.ICityDao;
import com.mashkovich.education.models.City;
import com.mashkovich.education.models.Country;

public class CityDAO  extends AbstractJDBC implements ICityDao{
	private static final String sql = "SELECT * FROM Cities WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Cities WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Cities SET city=?";
	private static final String sqlInsert = "INSERT INTO Cities (city) VALUES (?)";
	private final static Logger log= Logger.getLogger(CityDAO.class);
	public City getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		City city =new City();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			city.setId(rs.getInt("id"));
			city.setCity(rs.getString("city"));
			Country country = new Country();
			country.setId(rs.getInt("Countries_id"));
			city.setCountry(country);
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return city;	
	}
	public void update(City city) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){		
			ps.setString(1, city.getCity());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(City city) {	
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,city.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(City city) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, city.getCity());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
