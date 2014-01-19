package com.leenmeij.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * A layer between the application and the database. With this class we can
 * connect and disconnect from the database.
 * 
 * @author Deam Kop (s1075228)
 */
public class Database {

	private Connection connection;

	/**
	 * Opens the database connection.
	 */
	public void connect() {
		// Load the driver, catch the exeption
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Couldn't load the MySQL JDBC Driver.");
		}

		// If we could load the MySQL JDBC Driver successfully we can try
		// and store it in a connection object.
		try {
			// String for vpn connection @school
			String url = "jdbc:mysql://145.97.16.202/leenmeij";

			// Set the properties, set username and password.
			Properties p = new Properties();
			p.setProperty("user", "root");
			p.setProperty("password", "laravel");

			// Store the connection in the connection object.
			setConnection(DriverManager.getConnection(url, p));
		} catch (SQLException e) {
			// Could not connect to database, print error message.
			e.printStackTrace();
		}
	}

	/**
	 * Close the database connection, so we don't get too many connections to
	 * the database
	 */
	public void close() {
		try {
			getConnection().close();
		} catch (SQLException e) {
			// Could not close connection, display stack trace.
			e.printStackTrace();
		}
	}

	/**
	 * Get the connection
	 * @return the connection to the database
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * Set the connection to the database
	 * @param connection
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}