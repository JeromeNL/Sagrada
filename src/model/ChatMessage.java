package model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ChatMessage {

	private String message; 			// Players message
	private int playerid; 				// Player who sent the message
	private Timestamp timestamp; 		//Time SQL Format
	private String username;

	public ChatMessage(Timestamp timestamp, String username, String message) {
		this.username = username;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getPlayerID() {
		return playerid;
	}

	public String getMessage() {
		return message;
	}
	
	public Timestamp getCurrentTime() { 
		return timestamp;
	}
	
	public void setTime(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void setPlayerid(int playerid) {
		this.playerid = playerid;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}

	public String getCurrentTimeString() { // Returns the timestamp as to how it will be displayed in the game
		return new SimpleDateFormat("dd-MM HH:mm").format(timestamp);
	}
}