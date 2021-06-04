package controller;

import model.ChatMessage;
import model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatController {
	private boolean messageSent;
	private DatabaseController dbController;
	

	public ArrayList<ChatMessage> getChatMessages() throws SQLException { // Maakt een arraylist van alle verstuurde
																			// berichten in de database
		ArrayList<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
		DatabaseController DBGetChat = new DatabaseController();
		ResultSet rs = DBGetChat.doQuery(
				"SELECT username, chatline.time, message FROM chatline INNER JOIN player ON player.idplayer = chatline.idplayer AND player.idgame = "
						+ DBGetChat.getGameId() + " ORDER BY chatline.time ASC");
		while (rs.next()) {
			chatMessages
					.add(new ChatMessage(rs.getTimestamp("time"), rs.getString("username"), rs.getString("message")));
		}
		return chatMessages;
	}

	public void sendChatMessageToDB(String chatMessage, Player player) { // Sends a message to the database.

		DatabaseController DBsendChat = new DatabaseController();
		DBsendChat.doUpdateQuery("INSERT INTO chatline VALUES (" + player.getIdPlayer() + ", NOW() ,'" + chatMessage + "')");
		// (" + player.getPlayerId() + ", '" + chatMessage + "', NOW())");
		messageSent = true;

	}

	public Timestamp getTimestamp() throws SQLException{

		DatabaseController DBtimeStamp = new DatabaseController();
		ResultSet rs = DBtimeStamp.doQuery("SELECT NOW()");
		rs.next();
		return rs.getTimestamp("NOW()");
	}

	public boolean isMessageSent() {
		return messageSent;
	}

	public static boolean validateLetters(String txt) {

		String regx = "^[\\$#\\+{}:\\?!\\.,~@\"a-zA-Z0-9_ ]+$";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(txt);
		return matcher.find();
	}

}