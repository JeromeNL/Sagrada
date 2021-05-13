package model;

import controller.DatabaseController;
import controller.PlayerController;
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
	private PlayerController playerController;

	// Constructor when the player is challenged
	public Player(DatabaseController dbController) {
		this.dbController = dbController;
		// to do: get all the info about the player from the database based on username
	}

	// Constructor when the player is the challengee.
	public Player(String username, boolean isCreator, int idGame, GameColor privateObjectiveCardColor,
			DatabaseController dbController) {
		this.username = username;
		this.isCreator = isCreator;
		this.idGame = idGame;
		this.privateObjectiveCardColor = privateObjectiveCardColor;
		this.dbController = dbController;

		setUpPlayer();
	}

	private void setUpPlayer() {
		setInitialStatus();
		playerController.addToDatabase();
		playerController.createPlayerFrameField();
	}

	private void setInitialStatus() {
		if (isCreator) {
			status = PlayerStatus.CHALLENGER;
		} else {
			status = PlayerStatus.CHALLENGEE;
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
		Patterncard patterncard = new Patterncard(patternCardID, new DatabaseController());
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

	public int getIdGame() {
		return idGame;
	}
}
