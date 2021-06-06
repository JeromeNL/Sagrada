package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FavorTokenController {

	private DatabaseController dbController;

	public FavorTokenController(DatabaseController dbController) {
		this.dbController = dbController;
	}

	// Adds the favortoken to the gamefavortoken table.
	public void addToDatabase(int idToken, int idGame) {
		// to-do: insert row into gamefavortoken table with idfavortoken and idgame
		String query = "INSERT INTO gamefavortoken VALUES (" + idToken + "," + idGame + ",NULL,NULL,NULL);";
		dbController.doUpdateQuery(query);
	}
	
	// Assign favortoken
	public void assignFavorTokens(int idGame, int difficulty, int idPlayer) {
		String query = ("SELECT * FROM gamefavortoken WHERE idplayer IS NULL AND idgame = " + idGame + " LIMIT " + difficulty);
		ResultSet rs = dbController.doQuery(query);
		try {
			while (rs.next()) {
				int idFavortoken = rs.getInt("idfavortoken");
				updateFavorToken(idFavortoken, idPlayer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateFavorToken(int idFavortoken, int idPlayer) {
		dbController.doUpdateQuery("UPDATE gamefavortoken SET idplayer = " + idPlayer + " WHERE idfavortoken = " + idFavortoken);
	}
	
	//get amount of favortokens
	public int getAmountFavortokens(int idGame, int idPlayer) {
		String query = ("SELECT * FROM gamefavortoken WHERE idplayer = " +  idPlayer + " AND idgame = " + idGame );
		ResultSet rs = dbController.doQuery(query);
		int amount = 0;
		
		try {
			while (rs.next()) {
				int idFavortoken = rs.getInt("idfavortoken");
				amount ++;
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(amount);
		return amount;
	}



	// Updates the favortoken in the gamefavortoken table.
	public void updateDatabase() {
		// to-do: update row from gamefavortoken table so it contains the latest data
	}
}
