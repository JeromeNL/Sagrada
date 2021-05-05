package model;

import controller.DatabaseController;

public class FavorToken {

	private final int idToken; // number between 1 - 24
	private final int idGame; // id of the game where the favortoken is being used
	private int assignedToPlayerID; // favor token owned by player id
	private int usedForToolcardID; // has gametoolcard id if favortoken has been used for gametoolcard
	private int roundID; // has round id if favortoken has been used
	private boolean isUsed;

	public FavorToken(int idToken, int idGame) {
		this.idToken = idToken;
		this.idGame = idGame;
		isUsed = false;
		addToDatabase();
	}
	
	// Game favor tokens are assigned to game playerid after choosing patterncard
	public void assignToPlayer(int playerID) {
		assignedToPlayerID = playerID;
		updateDatabase();
	}

	// Method that is called when a favortoken is used.
	public void useToken(int toolcardID, int roundID) {
		this.usedForToolcardID = toolcardID;
		this.roundID = roundID;
		isUsed = true;
		updateDatabase();
	}
	
	// Adds the favortoken to the gamefavortoken table.
	private void addToDatabase() {
		// to-do: insert row into gamefavortoken table with idfavortoken and idgame
		DatabaseController dc = new DatabaseController();
		String query = "INSERT INTO gamefavortoken VALUES ("+idToken+","+idGame+",NULL,NULL,NULL);";
		dc.doUpdateQuery(query);
	}
	
	// Updates the favortoken in the gamefavortoken table.
	private void updateDatabase() {
		// to-do: update row from gamefavortoken table so it contains the latest data
	}

	public boolean isUsed() {
		return isUsed;
	}
	
	public int getIdToken() {
		return idToken;
	}

	public int getAssignedToPlayerID() {
		return assignedToPlayerID;
	}
}
