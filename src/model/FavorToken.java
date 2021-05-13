package model;

import controller.DatabaseController;
import controller.FavorTokenController;

public class FavorToken {

	private final int idToken; // number between 1 - 24
	private final int idGame; // id of the game where the favortoken is being used
	private int assignedToPlayerID; // favor token owned by player id
	private int usedForToolcardID; // has gametoolcard id if favortoken has been used for gametoolcard
	private int roundID; // has round id if favortoken has been used
	private boolean isUsed;
	private DatabaseController dbController;
	private FavorTokenController favorTokenController;

	public FavorToken(int idToken, int idGame, DatabaseController dbController) {
		this.idToken = idToken;
		this.idGame = idGame;
		this.dbController = dbController;
		isUsed = false;
		favorTokenController.addToDatabase();
	}

	// Game favor tokens are assigned to game playerid after choosing patterncard
	public void assignToPlayer(int playerID) {
		assignedToPlayerID = playerID;
		favorTokenController.updateDatabase();
	}

	// Method that is called when a favortoken is used.
	public void useToken(int toolcardID, int roundID) {
		this.usedForToolcardID = toolcardID;
		this.roundID = roundID;
		isUsed = true;
		favorTokenController.updateDatabase();
	}

	public boolean isUsed() {
		return isUsed;
	}

	public int getIdToken() {
		return idToken;
	}

	public int getIdGame() {
		return idGame;
	}

	public int getAssignedToPlayerID() {
		return assignedToPlayerID;
	}
}
