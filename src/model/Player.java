package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import controller.DatabaseController;
import sun.security.pkcs11.Secmod.DbMode;

public class Player {

	private int idPlayer;
	private String username;
	private int idGame;
	private PlayerStatus status;
	private int seqnr;
	private GameColor privateObjectiveCardColor;
	private Patterncard patterncard;
	private boolean isCreator;
	private DatabaseController dbController;

	// Constructor when the player is challenged
	public Player(DatabaseController dbController, int idPlayer) {
		this.dbController = dbController;
		this.idPlayer = idPlayer;
		// to do: get all the info about the player from the database based on username
	}
	
	// Constructor when the player is the challengee.
	public Player(String username, boolean isCreator, int idGame, GameColor privateObjectiveCardColor, DatabaseController dbController) {
		this.username = username;
		this.isCreator = isCreator;
		this.idGame = idGame;
		this.privateObjectiveCardColor = privateObjectiveCardColor;
		this.dbController = dbController;

		setUpPlayer();
	}

	private void setUpPlayer() {
		setInitialStatus();
		addToDatabase();
		createPlayerFrameField();
	}

	// Create all the playerframefield rows in the database.
	private void createPlayerFrameField() {		
		for (int position_y = 1; position_y <= 4; position_y++) {
			for (int position_x = 1; position_x <= 5; position_x++) {
				String query = "INSERT INTO playerframefield VALUES ("+idPlayer+","+position_x+","+position_y+","+idGame+",NULL,NULL);";
				dbController.doUpdateQuery(query);
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
	private void addToDatabase() {
		// Get an available gameID
		ResultSet rs = dbController.doQuery("SELECT idplayer FROM player ORDER BY idplayer DESC LIMIT 1;");
		try {
			int newPlayerID = 1;
			if (rs.next()) {
				newPlayerID = rs.getInt(1) + 1;
			}

			boolean increasingID = true;
			while (increasingID) {
				// Add a new row to the game table.
				String query = "INSERT INTO player VALUES (" + newPlayerID + ",\"" + username + "\"," + idGame + ",\""
						+ status + "\", NULL, \"" + privateObjectiveCardColor + "\", NULL, NULL);";

				int result = dbController.doUpdateQuery(query);
				if (result == 1) {
					increasingID = false;
					idPlayer = newPlayerID;
					System.out.println(getClass() + " - New player created with id " + idPlayer); // for testing
																									// purposes
				} else {
					newPlayerID++;
				}
			}

		} catch (SQLException e) {
			System.out.println("Something went wrong while adding a new player of a game to the database.");
			e.printStackTrace();
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
		// to-do: get seqnr from database
		return seqnr;
	}

	public void setSeqnr(int seqnr) {
		// to-do: set seqnr in database
		this.seqnr = seqnr;
	}

	public Patterncard getPatterncard() {
		return patterncard;
	}

	public void setPatternCard(int patternCardID) {
		Patterncard patterncard = new Patterncard(patternCardID, new DatabaseController(),this);
		this.patterncard = patterncard;

		addPatterncardChoiceToDatabase();
	}

	private void addPatterncardChoiceToDatabase() {
		// to-do: add row to patterncardoption table in database with idplayer and
		// idpatterncard
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
}
