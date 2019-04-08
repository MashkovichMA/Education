package com.mashkovich.education.connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MyThread extends Thread {
	private static final Logger log = Logger.getLogger(MyThread.class);
	public MyThread() {}
	public void start() {
		Connection c = null;
		try {
			c = ConnectionPool.getInstance().getConnection();
			Thread.sleep(3000);
			log.info("MyThread run");
		} catch (InterruptedException | SQLException | IOException e) {
			log.error(e);
		}
		ConnectionPool.getInstance().releaseConnection(c);
	}
}
