package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;
import controller.MainController;
import controller.GameController;
import controller.PlayerController;

public class Player {

	private int idPlayer;
	private int newIdPlayer;
	private String username;
	private int idGame;
	private PlayerStatus status;
	private GameColor privateObjectiveCardColor;
	private Patterncard patterncard;
	private boolean isCreator;
	private boolean diePlacedInRound;
	
	private DatabaseController dbController;
	private MainController mainController;
	private PlayerController playerController;

	// Constructor when the player is challenged
	public Player(DatabaseController dbController, int idPlayer, int idGame, MainController mainController) {
		this.dbController = dbController;
		this.idPlayer = idPlayer;
		this.idGame = idGame;
		this.mainController = mainController;
		
		loadPatterncard();
		username = dbController.getUsername(idPlayer);
		// TODO get all the info about the player from the database based on username
	}

	// Constructor when the player is the challengee.
	public Player(String username, boolean isCreator, int idGame, GameColor privateObjectiveCardColor, DatabaseController dbController, MainController mainController) {

		this.username = username;
		this.isCreator = isCreator;
		this.idGame = idGame;
		this.privateObjectiveCardColor = privateObjectiveCardColor;
		this.dbController = dbController;
		this.mainController = mainController;

		setUpPlayer();
	}

	private void loadPatterncard() {
		int idPatterncard = dbController.getPatterncardID(idPlayer);
		if (idPatterncard == 0) {
			// Player has not chosen patterncard yet
			return;
		}
		Patterncard patterncard = new Patterncard(idPatterncard, dbController, this);
		this.patterncard = patterncard;
	}

	private void setUpPlayer() {
		setInitialStatus();
		addToDatabase();
		createPlayerFrameField();
		setSeqNr();
	}

	private void setSeqNr() {
		dbController.setNewSeqNr(idGame, idPlayer);
	}

	public int getSeqNr() {
		return dbController.getSeqNr(idPlayer);
	}

	// Create all the playerframefield rows in the database.
	private void createPlayerFrameField() {

		playerController = new PlayerController(dbController);

		for (int position_y = 1; position_y <= 4; position_y++) {
			for (int position_x = 1; position_x <= 5; position_x++) {
				playerController.createPlayerFrameField(idPlayer, idGame, position_x, position_y);
			}
		}
	}

	private void setInitialStatus() {
		if (isCreator) {
			status = PlayerStatus.CHALLENGER;
		} else {
			status = PlayerStatus.CHALLENGEE;
		}
	}

	// Adds a new user to the database.
	public void addToDatabase() {

		playerController = new PlayerController(dbController);

		// Get an available playerID
		newIdPlayer = playerController.getAvailablePlayerId();

		boolean increasingID = true;
		while (increasingID) {
			int result = playerController.addRowToPlayerTable(newIdPlayer, username, idGame, status,
					privateObjectiveCardColor);
			if (result == 1) {
				increasingID = false;
				idPlayer = newIdPlayer;
				System.out.println(getClass() + " - New player created with id " + idPlayer); // for testing
																								// purposes
			} else {
				newIdPlayer++;
			}
		}
	}

	public PlayerStatus getStatus() {
		// to-do: get status from database
		return status;
	}

	public void setStatus(PlayerStatus status) {
		// to-do: set status in database.
		this.status = status;
	}

	public int getSeqnr() {
		return dbController.getSeqNr(idPlayer);
	}

	public Patterncard getPatterncard() {
		return patterncard;
	}

	public void setPatternCard(int patternCardID) {
		Patterncard patterncard = new Patterncard(patternCardID, dbController ,this);
		this.patterncard = patterncard;

		dbController.setPatterncard(idPlayer, patterncard.getID());
	}

	public int getScore() {
		// to-do: get score from database table instead of instance variable.
		return 0;
	}

	public void setScore(int score) {
		// to-do: set score to database table instead of instance variable.
	}

	public int getIdPlayer() {
		// to-do: get score from database table instead of instance variable.
		return idPlayer;
	}

	public String getUsername() {
		// to-do: get username from database table instead of instance variable.
		return username;
	}

	public GameColor getPrivateObjectiveCardColor() {
		// to-do: get color from database table and convert it to instance variable.
		return privateObjectiveCardColor;
	}

	public boolean isCreator() {
		return isCreator;
	}

	public int getGameID() {
		return idGame;
	}

	public void setDiePlacedInRound(boolean b) {
		diePlacedInRound = b;
	}
	
	public boolean getDiePlacedInRound() {
		return diePlacedInRound;
	}
}
