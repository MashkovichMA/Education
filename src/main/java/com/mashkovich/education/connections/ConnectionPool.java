package com.mashkovich.education.connections;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private String url;
    private String user;
    private String password;
    private String driver;
	private Properties properties;
	private int connections;
	private static int countConnection;
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private BlockingQueue <Connection> pool;
    private final static ConnectionPool INSTANCE = new ConnectionPool();
    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private ConnectionPool() {
    	properties = new Properties();
    	try {
			properties.load(new FileInputStream("src/main/resources/database.properties"));
		} catch (IOException e) {
			log.error(e);
		}
    	connections=Integer.valueOf(properties.getProperty("jdbc.size"));
    	url = properties.getProperty("jdbc.url");
    	user = properties.getProperty("jdbc.username");
    	password = properties.getProperty("jdbc.password");
    	pool = new ArrayBlockingQueue<Connection>(connections,true);
    }
    public static ConnectionPool getInstance() {
		return INSTANCE;
	}
    public Connection createConnection() throws InterruptedException{
    	properties = new Properties();
    	try {
			properties.load(new FileInputStream("src/main/resources/database.properties"));
		} catch (IOException e) {
			log.error(e);
		}
    	driver = properties.getProperty("jdbc.driver");
    		Connection connection = pool.poll(10L, TimeUnit.SECONDS);
    		if (connection==null&&connections>0) {
			try {
				Class.forName(driver);
				connections--;
			} catch (ClassNotFoundException e) {
				log.error(e);
			}
			try {
				connection = DriverManager.getConnection(url,user,password);
			} catch (SQLException e) {
				log.error(e);
			}
    		}
			if (connection!=null) {
				return connection;	
			}
			return connection;
    }
    public Connection getConnection() throws InterruptedException, SQLException, IOException {
		Connection connection =pool.poll();
		if (connection!=null)
			return connection;
		lock.writeLock().lock();
		if ( countConnection < connections) {
			pool.add(createConnection());
			countConnection++;
		}
		connection = pool.take();
		lock.writeLock().unlock();
		return connection;
	}
    public void releaseConnection(Connection connection) {
    	pool.add(connection);
    }
}
