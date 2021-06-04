package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Die;
import model.Player;

public class PatterncardFieldsController {

	private DatabaseController dbController;

	public PatterncardFieldsController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	// Adds a row to the playerframefield table so the placement of a die is saved
	// to the database.
	public void addDieToDatabase(int playerID, int xPosition, int yPosition, int gameID, Die die) {
		// playerframfield has columns : idplayer, position_x, position_y, idgame,
		// dienumber, diecolor
		String query = "INSERT INTO playerframefield VALUES (" + playerID + ", " + xPosition + ", " + yPosition + ", "
				+ gameID + ", " + die.getEyesCount() + ", \"" + die.getColor() + "\");";

		dbController.doUpdateQuery(query);
	}

	// Removes the die from the database.
	public void removeDieFromDatabase(int xPosition, int yPosition) {
		String query = "UPDATE playerframefield SET dienumber = NULL, diecolor = NULL WHERE position_x = " + xPosition
				+ " AND position_y = " + yPosition + ";";

		dbController.doUpdateQuery(query);
	}

	// Is this die the first die?
	public int isFirstTurn(Player owner) throws SQLException {

		ResultSet amountOfDies = dbController.doQuery("SELECT count(diecolor) FROM playerframefield WHERE idgame ='"
				+ owner.getGameID() + "' AND idplayer ='" + owner.getIdPlayer() + "'");

		int value = 0;

		while (amountOfDies.next()) {
			value = ((Number) amountOfDies.getObject(1)).intValue();
		}
		return value;

	}

	public String isAdjacentFieldSameColor(Player owner, int y, int x) throws SQLException {

		ResultSet colorOfAdjacentDie = dbController.doQuery(
				"SELECT diecolor FROM playerframefield " + "WHERE idgame = '" + owner.getGameID() + "' AND idplayer = '"
						+ owner.getIdPlayer() + "' AND position_x = '" + x + "' AND position_y = '" + y + "'");

		String color = "";

		while (colorOfAdjacentDie.next()) {
			color = colorOfAdjacentDie.getObject(1).toString().toLowerCase();
			System.out.println("color of adjacent die: " + color);
			System.out.println(x + " " + y);
		}

		return color;
	}

	public int isAdjacentFieldSameValue(Player owner, int y, int x) throws SQLException {

		ResultSet valueOfAdjacentDie = dbController.doQuery(
				"SELECT gamedie.eyes FROM playerframefield INNER JOIN gamedie ON playerframefield.idgame = gamedie.idgame AND playerframefield.dienumber = gamedie.dienumber AND playerframefield.diecolor = gamedie.diecolor WHERE playerframefield.idplayer = '"
						+ owner.getIdPlayer() + "' AND playerframefield.idgame = '" + owner.getGameID()
						+ "' AND playerframefield.position_x = '" + x + "' AND playerframefield.position_y = '" + y
						+ "'");

		int value = 0;

		while (valueOfAdjacentDie.next()) {
			value = valueOfAdjacentDie.getInt(1);
		}
		return value;

	}
	
	public int isFieldEmpty(Player owner, int y, int x) throws SQLException {
			ResultSet zeroOrOneDie = dbController
					.doQuery("select COUNT(dienumber) from playerframefield where  idgame = '" + owner.getGameID()
							+ "' AND idplayer = '" + owner.getIdPlayer() + "' AND position_y = '" + y
							+ "' AND position_x = '" + x + "'");

			int value = 0;

			while (zeroOrOneDie.next()) {
				value = ((Number) zeroOrOneDie.getObject(1)).intValue();
			}
			return value;
			
		}
	}


