package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

	private Connection m_Conn;

	public DatabaseController() {
		// Setting up database connection
		if (loadDataBaseDriver("com.mysql.jdbc.Driver")) {
			makeConnection();
		}
	}

	/*
	 * Method that loads the database driver. Required to execute before
	 * establishing the connection. Returns true if database driver has been loaded.
	 */
	public boolean loadDataBaseDriver(String driverName) {
		try {
			Class.forName(driverName);
			return true;
		} catch (Exception ClassNotFountException) {
			return false;
		}
	}

	/*
	 * Method that attempts to establish a connection to the database. Returns true
	 * if connection is established.
	 */
	public boolean makeConnection() {
		try {
			m_Conn = DriverManager.getConnection(
					"jdbc:mysql://databases.aii.avans.nl/jwkwette_db2?" + "user=jwkwette&password=Ab12345");
		} catch (SQLException ex) {
			System.out.println("Kan geen verbinding maken met de database. Lees hieronder waarom:");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}

	/*
	 * Method to execute SELECT statements on the database. Returns: a ResultSet
	 * object that contains the data produced by the given query; never null
	 */
	public ResultSet doQuery(String query) {
		Statement statement;
		try {
			statement = m_Conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);

			return resultSet;
		} catch (SQLException e) {
			System.out.println("Something went wrong while executing the following query: " + query);
			System.out.println(e.getMessage());
		}
		return null;
	}

	/*
	 * Method to execute INSERT/UPDATE/DELETE on the database. Returns: either (1)
	 * the row count for SQL Data Manipulation Language (DML) statementsor (2) 0 for
	 * SQL statements that return nothing
	 */
	public int doUpdateQuery(String query) {
		Statement statement;
		try {
			statement = m_Conn.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out
					.println("Something went wrong while executing the following insert/update/delete query: " + query);
			System.out.println(e.getMessage());
		}
		return 0;
	}
}
