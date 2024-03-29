package model;

import java.util.ArrayList;
import java.util.Random;

import controller.DatabaseController;
import controller.FavorTokenController;
import controller.GameController;
import controller.MainController;

public class Game {
	private int blueCounter;
	private int greenCounter;
	private int yellowCounter;
	private int redCounter;
	private int purpleCounter;

	private DatabaseController dbController;
	private MainController mainController;

	private int idGame;
	private int newIdGame;

	private FavorToken[] favorTokens; // 24 favor tokens per game.
	private ArrayList<Player> players; // 1 to 4 players per game
	private DiesInSupply diesInSupply;

	private GameController gameController;

	private String usernameCreator;

	// Constructor to load an existing game.
	public Game(int idGame, DatabaseController dbController, MainController mainController) {
		this.idGame = idGame;
		this.dbController = dbController;
		this.mainController = mainController;

		favorTokens = new FavorToken[24];
		players = new ArrayList<Player>();
		diesInSupply = new DiesInSupply();
		getUsernameCreator();

		loadGame();
	}

	// Constructor to create a new game.
	public Game(String usernameCreator, DatabaseController dbController, MainController mainController) {
		blueCounter = 0;
		greenCounter = 0;
		yellowCounter = 0;
		redCounter = 0;
		purpleCounter = 0;

		this.usernameCreator = usernameCreator;
		this.dbController = dbController;
		this.mainController = mainController;

		favorTokens = new FavorToken[24];
		players = new ArrayList<Player>();
		diesInSupply = new DiesInSupply();

		setupGame();
	}

	private void getUsernameCreator() {
		usernameCreator = dbController.getUsernameCreator(getIdGame());
	}

	// Set up a new game.
	private void setupGame() {
		addToDatabase();
		addCreatorPlayer(usernameCreator);
		createDies();
		createFavorTokens();
		dbController.setRoundID(idGame, 1);

		dbController.setTurnIdPlayer(idGame, dbController.getPlayerID(1, idGame));
		dbController.createNewPublicObjectives(idGame);
		dbController.createNewToolcards(idGame);
	}

	public void startGame() {
		createDiesInSupply();
		loadDiesInSupply();
	}

	// Load an existing game.
	private void loadGame() {
		loadPlayers();
		loadDiesInSupply();
	}

	private void loadDiesInSupply() {
		diesInSupply = dbController.getDiesInSupply(idGame, dbController.getRoundID(idGame));
	}

	private void createDiesInSupply() {
		int amount = players.size() * 2 + 1;
		dbController.createDiesInSupply(idGame, dbController.getRoundID(idGame), amount);
	}

	// Set up 90 dies to be created in the game.
	private void createDies() {
		dbController.createGameDies(idGame);
	}

	// Create 24 favortokens to be used in the game.
	private void createFavorTokens() {
		for (int i = 0; i < favorTokens.length; i++) {
			int idToken = i + 1;

			favorTokens[i] = new FavorToken(idToken, idGame, dbController);

		}
	}

	// check all players in game. assign favortokens with how many they have to get
	public void assignFavorTokens(int difficulty, int playerID) {
		FavorTokenController favorTokenController = new FavorTokenController(dbController);
		favorTokenController.assignFavorTokens(idGame, difficulty, playerID);
	}

	public FavorToken[] getFavorTokens() {
		return dbController.getFavorToken(idGame);
	}

	public int getAmountFavorTokens(int playerId) {
		// to-do: get the amount of favortokens
		FavorTokenController favorTokenController = new FavorTokenController(dbController);
		return favorTokenController.getAmountFavortokens(idGame, playerId);
	}

	// Add the creator of the game to the players of the game.
	private void addCreatorPlayer(String usernameCreator) {
		// to-do: add check if username exists.

		boolean isCreator = true;
		GameColor privateObjectiveCardColor = getObjectiveCardColor();
		Player creator = new Player(usernameCreator, isCreator, idGame, privateObjectiveCardColor, dbController,
				mainController);
		players.add(creator);
	}

	private void loadPlayers() {
		players = dbController.getPlayers(idGame);
	}

	// Add / invite a new player to the game.
	public void invitePlayer(String username) {
		// to-do: add check if username exists.

		// Check if there is room for more players.
		if (players.size() < 4) {
			GameColor privateObjectiveCardColor = getObjectiveCardColor();
			Player newPlayer = new Player(username, false, idGame, privateObjectiveCardColor, dbController,
					mainController);
			players.add(newPlayer);
		}
	}

	// Gets an available gameID and then adds a new row to the game table in the
	// database.
	public void addToDatabase() {

		gameController = new GameController(dbController, mainController);

		// Get an available gameID
		newIdGame = gameController.getAvailableGameID();

		boolean increasingID = true;
		while (increasingID) {
			int result = gameController.addRowToGameTable(newIdGame);
			if (result == 1) {
				increasingID = false;
				idGame = newIdGame;
			} else {
				newIdGame++;
			}
		}
	}

	// Gives an available GameColor to be used as the private objectivecard color.
	private GameColor getObjectiveCardColor() {
		

		Random rand = new Random(); // instance of random class
		String s = "";

		boolean done = false;
		while (!done) {
			int int_random = rand.nextInt(5);
			switch (int_random) {
			case 0:
				if (blueCounter < 1) {
					s = "BLUE";
					done = true;
					blueCounter++;
				} else {
					int_random = rand.nextInt(5);
				}
				break;

			case 1:
				if (redCounter < 1) {
					s = "RED";
					done = true;
					redCounter++;
				} else {
					int_random = rand.nextInt(5);
				}
				break;

			case 2:
				if (greenCounter < 1) {
					s = "GREEN";
					done = true;
					greenCounter++;
				} else {
					int_random = rand.nextInt(5);
				}
				break;

			case 3:
				if (yellowCounter < 1) {
					s = "YELLOW";
					done = true;
					yellowCounter++;
				} else {
					int_random = rand.nextInt(5);
				}
				break;

			case 4:
				if (purpleCounter < 1) {
					s = "PURPLE";
					done = true;
					purpleCounter++;
				} else {
					int_random = rand.nextInt(5);
				}
				break;

			default:
				break;
			}
		}

		return GameColor.valueOf(s.toUpperCase()); // this needs to be changed later!
	}

	public void setNextRound() {

		int currentRoundID = dbController.getRoundID(idGame);

		if (currentRoundID == 20) {

		
			EndScore endscore = new EndScore(players.get(0), dbController, mainController);
			endscore.totalEndScore();

			
			EndScore endscore2 = new EndScore(players.get(1), dbController, mainController);
			endscore2.totalEndScore();

			endGame(endscore.totalEndScore(), endscore2.totalEndScore(), 786, 786);

			if (players.size() == 4) {

				EndScore endscore3 = new EndScore(players.get(2), dbController, mainController);
				endscore3.totalEndScore();
				EndScore endscore4 = new EndScore(players.get(3), dbController, mainController);
				endscore4.totalEndScore();
			
				endGame(endscore.totalEndScore(), endscore2.totalEndScore(), endscore3.totalEndScore(),
						endscore4.totalEndScore());
			}

			if (players.size() == 3) {

				EndScore endscore3 = new EndScore(players.get(2), dbController, mainController);
				endscore3.totalEndScore();
			
				endGame(endscore.totalEndScore(), endscore2.totalEndScore(), endscore3.totalEndScore(), 786);

			}

			return;

		}

		int nextRoundID = currentRoundID + 1;
		dbController.setRoundID(idGame, nextRoundID);

		if (nextRoundID % 2 != 0) {
			addToRoundtrack(currentRoundID);

			if (usernameCreator.equals(mainController.getLoggedInUsername())) {
				createDiesInSupply();
				loadDiesInSupply();
				mainController.showGame(0);
			}
		}
	}

	// Adds the last die of the die supply to the roundtrack
	private void addToRoundtrack(int currentRoundID) {
		Die lastDie = getDiesInSupply().getDies().get(0);
		dbController.addDieToRoundtrack(lastDie, currentRoundID - 1, idGame);
	}

	private void endGame(int player1, int player2, int player3, int player4) {

		mainController.showEndScoreView(player1, player2, player3, player4, players, mainController);
		for (Player player : players) {
			player.setStatus(PlayerStatus.FINISHED);
		}
	}

	public ArrayList<String> getPlayerOrder() {
		return dbController.getPlayerOrder(idGame);
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public DiesInSupply getDiesInSupply() {
		return diesInSupply;
	}

	public int getIdGame() {
		return idGame;
	}

	public void setNextTurn() {
		int currentPlayerID = dbController.getCurrentPlayerID(idGame);

		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			if (player.getIdPlayer() == currentPlayerID) {

				// get seq nr of current player
				int currentSeqNr = player.getSeqnr();
				int newSeqNr;
				int newPlayerID;

				// check if seq nr needs to be increased or decreased
				if (dbController.isClockwise(dbController.getRoundID(idGame))) {
					// check if new round needs to be started because seqnr is at the end
					if (currentSeqNr == players.size()) {
						setNextRound();
						newSeqNr = players.size();
					} else {
						newSeqNr = currentSeqNr + 1;

						// TODO set turn playerid in database
					}
				} else {
					if (currentSeqNr == 1) {
						setNextRound();
						newSeqNr = 1;
					} else {
						newSeqNr = currentSeqNr - 1;
					}
				}
				newPlayerID = dbController.getPlayerID(newSeqNr, idGame);
				dbController.setTurnIdPlayer(idGame, newPlayerID);
				return;
			}
		}
	}

	public String getCurrentPlayer() {
		int currentPlayerID = dbController.getCurrentPlayerID(idGame);
		return dbController.getUsername(currentPlayerID);
	}

	public int getRoundID() {
		return dbController.getRoundID(idGame);
	}

	public ArrayList<Integer> getPublicObjectives() {
		return dbController.getPublicObjectiveIDs(idGame);
	}

}
