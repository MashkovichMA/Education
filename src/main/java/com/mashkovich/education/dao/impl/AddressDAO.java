package com.mashkovich.education.dao.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.mashkovich.education.connections.AbstractJDBC;
import com.mashkovich.education.connections.ConnectionPool;
import com.mashkovich.education.dao.IAddressDao;
import com.mashkovich.education.models.Address;
import com.mashkovich.education.models.City;

public class AddressDAO  extends AbstractJDBC implements IAddressDao {
	private static final String sql = "SELECT * FROM Addresses WHERE id = ?;";
	private static final String sqlDelete = "DELETE * FROM Addresses  WHERE id = ?;";
	private static final String sqlUpdate = "UPDATE Addresses SET street=?, house=?,flat=?";
	private static final String sqlInsert = "INSERT INTO Addresses (street, house,flat) VALUES (?,?,?)";
	private final static Logger log= Logger.getLogger(CountryDAO.class);
	public Address getByID(int id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			log.error(e1);
		}
		Address address =new Address();
		try (PreparedStatement stm = connection.prepareStatement(sql)) {
			stm.setInt(1, id);
			try(ResultSet rs = stm.executeQuery()){
			rs.next();
			address.setId(rs.getInt("id"));
			address.setStreet(rs.getString("street"));
			address.setHouse(rs.getInt("house"));
			address.setFlat(rs.getInt("flat"));
			City city = new City();
			city.setId(rs.getInt("Cities_id"));
			address.setCity(city);
		} catch (SQLException e) {
			log.error(e);
		}}
		catch (SQLException e) {
			log.error(e);
		}
		return address;
	}
	public void update(Address address) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlUpdate)){		
			ps.setString(1, address.getStreet());
			ps.setInt(2, address.getHouse());
			ps.setInt(3, address.getFlat());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		} 
	}
	public void delete(Address address) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}
		try (PreparedStatement ps= connection.prepareStatement(sqlDelete)){	
			ps.setInt(1,address.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
		}
	}
	public void save(Address address) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().getConnection();
		} catch (InterruptedException | SQLException | IOException e1) {
			e1.printStackTrace();
		}	
		try (PreparedStatement ps= connection.prepareStatement(sqlInsert)){	
			ps.setString(1, address.getStreet());
			ps.setInt(2, address.getHouse());
			ps.setInt(3, address.getFlat());
			ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
	}
	}
}
