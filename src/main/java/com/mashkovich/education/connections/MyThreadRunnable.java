package com.mashkovich.education.connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class MyThreadRunnable implements Runnable {
	public MyThreadRunnable() {}
	private static final Logger log = Logger.getLogger(MyThreadRunnable.class);
	public void run() {
		Connection c = null;
		try {
			c = ConnectionPool.getInstance().getConnection();
			Thread.sleep(3000);
			log.info("MyThreadRunnable run");
		} catch (InterruptedException | SQLException | IOException e) {
			log.error(e);
		}
		ConnectionPool.getInstance().releaseConnection(c);
	}
}
