package model;

public class GameStats {

	private String username;
	private int round;
	private int idgame;
	private String creationdate;
	private String gamestatus;

	public GameStats(String username, int round, int idgame, String creationdate, String gamestatus) {
		super();
		this.username = username;
		this.round = round;
		this.idgame = idgame;
		this.creationdate = creationdate;
		this.gamestatus = gamestatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getIdgame() {
		return idgame;
	}

	public void setIdgame(int idgame) {
		this.idgame = idgame;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
	}

	public String getGamestatus() {
		return gamestatus;
	}

	public void setGamestatus(String gamestatus) {
		this.gamestatus = gamestatus;
	}

}
