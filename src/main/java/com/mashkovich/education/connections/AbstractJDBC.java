package com.mashkovich.education.connections;

public class AbstractJDBC {
	private ConnectionPool connectionPool;
	public AbstractJDBC() {}
	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}
	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}
}
