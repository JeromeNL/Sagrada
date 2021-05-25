package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import model.Die;
import model.DiesInSupply;
import model.GameColor;
import model.Player;

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

	public ArrayList<Player> getPlayers(int idGame) {
		ArrayList<Player> players = new ArrayList<Player>();

		ResultSet rs = doQuery("SELECT * FROM player WHERE idgame = " + idGame);
		try {
			while (rs.next()) {
				Player player = new Player(this, rs.getInt(1), idGame);
				players.add(player);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}

	public Die[] getDies(int idGame) {
		Die[] dies = new Die[90];
		int counter = 0;

		ResultSet rs = doQuery("SELECT * FROM gamedie WHERE idgame = " + idGame);

		try {
			while (rs.next()) {
				String stringColor = rs.getString("diecolor");
				GameColor gameColor = GameColor.valueOf(stringColor.toUpperCase());
				Die die = new Die(gameColor, rs.getInt("eyes"), rs.getInt("dienumber"));
				dies[counter] = die;
				counter++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dies;
	}

	public DiesInSupply getDiesInSupply(int idGame, int roundID) {
		DiesInSupply diesInSupply = new DiesInSupply();
		String allDiesQuery = "SELECT * FROM gamedie WHERE idgame = " + idGame + " AND (roundID = " + roundID
				+ " OR roundID = " + (roundID - 1) + ")" + " AND roundtrack IS NULL";
		ResultSet allDies = doQuery(allDiesQuery);
		try {
			while (allDies.next()) {

				int dieNumber = allDies.getInt("dienumber");
				String stringColor = allDies.getString("diecolor");

				String query = "SELECT * FROM playerframefield WHERE idGame = " + idGame + " AND dienumber = "
						+ dieNumber + " AND diecolor = \"" + stringColor + "\"";

				ResultSet diePlaced = doQuery(query);
				if (diePlaced.next()) {
					continue;
				} else {
					GameColor gameColor = GameColor.valueOf(stringColor.toUpperCase());
					Die die = new Die(gameColor, allDies.getInt("eyes"), allDies.getInt("dienumber"));
					diesInSupply.addDie(die);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diesInSupply;
	}

	public void createGameDies(int idGame) {
		ResultSet rs = doQuery("SELECT * FROM die");
		Random random = new Random();

		try {
			while (rs.next()) {
				doUpdateQuery("INSERT INTO gamedie VALUES (" + idGame + ", " + rs.getInt("number") + ", \""
						+ rs.getString("color") + "\", " + (random.nextInt(6) + 1) + ", NULL, NULL);");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createDiesInSupply(int idGame, int roundID, int amount) {
		ResultSet rs = doQuery("SELECT * FROM gamedie WHERE idgame = " + idGame
				+ " AND roundtrack IS NULL AND roundID IS NULL ORDER BY RAND() LIMIT " + amount);

		try {
			while (rs.next()) {
				doUpdateQuery(
						"UPDATE gamedie SET roundID = " + roundID + " WHERE idgame = " + idGame + " AND dienumber = "
								+ rs.getInt("dienumber") + " AND diecolor = \"" + rs.getString("diecolor") + "\"");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void placeDie(int idPlayer, int idGame, Die die, int xPosition, int yPosition) {
		String query = "UPDATE playerframefield SET dienumber = " + die.getDieID() + ", diecolor = \""
				+ die.getColor().toString().toLowerCase() + "\" WHERE idplayer = " + idPlayer + " AND idgame = "
				+ idGame + " AND position_x = " + xPosition + " AND position_y = " + yPosition;

		if (doUpdateQuery(query) != 1) {
			System.out.println("Error placing die");
		}
	}

	public Die getDie(int idPlayer, int xPosition, int yPosition) {
		String query = "SELECT * FROM playerframefield WHERE idplayer = " + idPlayer + " AND position_x = " + xPosition
				+ " AND position_y = " + yPosition + " AND dienumber IS NOT NULL AND diecolor IS NOT NULL;";
		ResultSet rs = doQuery(query);
		try {
			while (rs.next()) {
				String colorString = rs.getString("diecolor").toUpperCase();
				GameColor color = GameColor.valueOf(colorString);
				int dieNumber = rs.getInt("dienumber");
				int idGame = rs.getInt("idgame");

				String query2 = "SELECT * FROM gamedie WHERE idgame = " + idGame + " AND dienumber = " + dieNumber
						+ " AND diecolor = \"" + colorString + "\"";
				ResultSet rs2 = doQuery(query2);
				while (rs2.next()) {
					int eyesCount = rs2.getInt("eyes");

					return new Die(color, eyesCount, dieNumber);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void setPatterncard(int idPlayer, int idPatterncard) {
		String query = "UPDATE player SET idpatterncard = " + idPatterncard + " WHERE idplayer = " + idPlayer;
		doUpdateQuery(query);
	}

	public int getPatterncardID(int idPlayer) {
		ResultSet rs = doQuery("SELECT * FROM player WHERE idplayer = " + idPlayer);
		int idPatterncard = 0;
		try {
			while (rs.next()) {
				idPatterncard = rs.getInt("idpatterncard");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPatterncard;
	}

	public String getUsername(int idPlayer) {
		ResultSet rs = doQuery("SELECT * FROM player WHERE idplayer = " + idPlayer);
		String username = "";
		try {
			while (rs.next()) {
				username = rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
	}

	public void setNewSeqNr(int idGame, int idPlayer) {
		boolean done = false;
		while (!done) {
			int newSeqNr = 1;
			ResultSet rs = doQuery("SELECT * FROM player WHERE idgame = " + idGame + " ORDER BY seqnr DESC LIMIT 1");
			try {
				while (rs.next()) {
					newSeqNr = rs.getInt("seqnr") + 1;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int result = doUpdateQuery("UPDATE player SET seqnr = " + newSeqNr + " WHERE idplayer = " + idPlayer);
			if (result == 1)
				done = true;
		}
	}

	public int getSeqNr(int idPlayer) {
		int seqnr = 0;
		ResultSet rs = doQuery("SELECT seqnr FROM player WHERE idplayer = " + idPlayer);
		try {
			while (rs.next()) {
				seqnr = rs.getInt(1);
				return seqnr;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return seqnr;
	}

	public ArrayList<String> getPlayerOrder(int gameID) {
		ArrayList<String> playerOrder = new ArrayList<String>();
		ResultSet rs = doQuery("SELECT * FROM player WHERE idgame = " + gameID + " ORDER BY seqnr ASC");
		ResultSet rs2 = doQuery("SELECT * FROM player WHERE idgame = " + gameID + " ORDER BY seqnr DESC");
		try {
			while (rs.next()) {
				playerOrder.add(rs.getString("username"));
			}
			while (rs2.next()) {
				playerOrder.add(rs2.getString("username"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return playerOrder;
	}

	public void setRoundID(int idGame, int idRound) {
		int result = doUpdateQuery("UPDATE game SET current_roundID = " + idRound + " WHERE idgame = " + idGame);
		if (result != 1) {
			System.out.println(getClass() + " - Something went wrong while setting roundID");
		}
	}

	public int getRoundID(int idGame) {
		int roundID = 0;
		ResultSet rs = doQuery("SELECT * FROM game WHERE idgame = " + idGame);
		try {
			while (rs.next()) {
				return rs.getInt("current_roundID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roundID;
	}

	public int getRoundNr(int roundID) {
		int roundNr = 0;
		ResultSet rs = doQuery("SELECT * FROM round WHERE roundID = " + roundID);
		try {
			while (rs.next()) {
				roundNr = rs.getInt("roundnr");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roundNr;
	}

	public boolean isClockwise(int roundID) {
		boolean isClockwise = false;
		ResultSet rs = doQuery("SELECT * FROM round WHERE roundID = " + roundID);
		try {
			while (rs.next()) {
				isClockwise = rs.getBoolean("clockwise");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isClockwise;
	}

	public void setTurnIdPlayer(int idGame, int idPlayer) {
		int result = doUpdateQuery("UPDATE game SET turn_idplayer = " + idPlayer + " WHERE idgame = " + idGame);
		if (result != 1) {
			System.out.println(getClass() + " - Something went wrong while setting turn idplayer");
		}
	}

	public int getCurrentPlayerID(int idGame) {
		int idPlayer = 0;
		ResultSet rs = doQuery("SELECT * FROM game WHERE idgame = " + idGame);

		try {
			while (rs.next()) {
				idPlayer = rs.getInt("turn_idplayer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPlayer;
	}

	public int getPlayerID(int seqnr, int idGame) {
		int idPlayer = 0;
		ResultSet rs = doQuery("SELECT * FROM player WHERE idgame = " + idGame + " AND seqnr = " + seqnr);
		try {
			while (rs.next()) {
				idPlayer = rs.getInt("idplayer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idPlayer;
	}

	public String getUsernameCreator(int idGame) {
		String query = "SELECT * FROM player WHERE idgame = " + idGame + " AND playstatus = \"challenger\"";
		ResultSet rs = doQuery(query);
		try {
			while (rs.next()) {
				return rs.getString("username");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
