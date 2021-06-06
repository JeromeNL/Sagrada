package model;

import controller.DatabaseController;
import controller.FavorTokenController;

public class FavorToken {

	private final int idToken; // number between 1 - 24
	private final int idGame; // id of the game where the favortoken is being used
	private int assignedToPlayerID; // favor token owned by player id
	private boolean isUsed;
	private DatabaseController dbController;
	private FavorTokenController favorTokenController;

	// Constructor create a new favortoken
	public FavorToken(int idToken, int idGame, DatabaseController dbController) {
		this.idToken = idToken;
		this.idGame = idGame;
		this.dbController = dbController;
		isUsed = false;
		favorTokenController = new FavorTokenController(dbController);
		favorTokenController.addToDatabase(idToken, idGame);
	}

	// Method that is called when a favortoken is used.
	public void useToken(int toolcardID, int roundID) {
		isUsed = true;
		favorTokenController = new FavorTokenController(dbController);
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
