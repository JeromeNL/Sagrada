package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseController {

	private Connection m_Conn;
	
	public DatabaseController() {
		if (loadDataBaseDriver("com.mysql.jdbc.Driver")) {
			makeConnection();			
		}
	}	
	
	public boolean loadDataBaseDriver(String driverName) {
		try {
			Class.forName(driverName);
			return true;
		} catch (Exception ClassNotFountException) {
			return false;
		}
	}
	
	public boolean makeConnection() {
		try {
			m_Conn = DriverManager.getConnection(
					"jdbc:mysql://databases.aii.avans.nl/jwkwette_db2?" + "user=jwkwette&password=Ab12345");
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("Kan geen verbinding maken met de database. Lees hieronder waarom:");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;
	}
	
/// Methode om een query uit te voeren op de database. Methode returned de result set.
	
	public ResultSet doQuery(String query) {
		Statement statement = null;
		try {
			statement = m_Conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
//			statement.close();
			
			return resultSet;
		} catch (SQLException e) {
			System.out.println("Something went wrong while executing the following query: " + query);
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/*
	 * Methode om INSERT/UPDATE/DELETE statements uit te voeren op de database.
	 * Returns: aantal geüpdate rows OF 0 als het SQL query is dat niks teruggeeft.
	 */
	public int doUpdateQuery(String query) {
		Statement statement;
		try {
			statement = m_Conn.createStatement();
			return statement.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Something went wrong while executing the following insert/update/delete query: " + query);
			System.out.println(e.getMessage());
		}
		return 0;
	}

	public void doSomeQuering() {
		System.out.println("Gaat nu een query doen, moment..");

		Statement stmt = null;
		String query = "SELECT * FROM round";
		try {
			stmt = m_Conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String roundID = rs.getString("RoundID");
				String roundnr = rs.getString(2);

				System.out.println("RoundID: " + roundID + " roundnr: " + roundnr);

			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

//	// eigengemaakte test: vraagt alle kleuren die een 'u' bevatten op uit de DB.
//	public void printColorsWithU() {
//		System.out.println("Vraagt alle kleuren met een U op...");
//		Statement s = null;
//		String query = "SELECT * FROM color WHERE color LIKE '%u%';";
//
//		try {
//			s = m_Conn.createStatement();
//			ResultSet rs = s.executeQuery(query);
//			System.out.println("-----()-----");
//			while (rs.next()) {
//				String askTable = rs.getString("color");
//
//				System.out.println("Kleuren met een U: " + askTable);
//			}
//			s.close();
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//	}

}
