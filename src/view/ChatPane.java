package view;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import controller.ChatController;
import controller.DatabaseController;
import controller.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.ChatMessage;
import model.Player;

public class ChatPane extends VBox {

	private List<MessagePane> list = new ArrayList<>();
	private ListView<MessagePane> chatBox = new ListView<MessagePane>();
	private TextField Input = new TextField();
	ArrayList<ChatMessage> chatmessages = new ArrayList<>();
	private ChatController chatController;
	private Button sendButton;
	private Player player;
	private DatabaseController dbController;

	public ChatPane(Player player, DatabaseController dbController) {
		this.player = player;
		chatController = new ChatController(dbController);
		Input.setMinHeight(30);
		Input.setPrefWidth(295);
		sendButton = new Button("SEND");
		sendButton.setPrefSize(70, 30);
		sendButton.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		sendButton.setTextFill(Color.WHITE);
		sendButton.setOnMouseClicked(e -> {
			sendMessage();
			enableButtons();
		});
		Input.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				sendMessage();
				enableButtons();
			}
		});
		HBox messageInput = new HBox();
		messageInput.getChildren().addAll(Input, sendButton);
		getChildren().addAll(chatBox, messageInput);
		setPrefWidth(295);
		
		sendButton.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: Black; -fx-background-color: lightblue");
		Input.setStyle("-fx-font-weight: bold; -fx-font-size: 15; -fx-text-fill: Black; -fx-background-color: lightblue; -fx-prompt-text-fill: black;");
		Input.setPromptText("Type een bericht");
		try {
			refresh();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
		

	private void sendMessage() {
		ChatController chatController = new ChatController(dbController);
		String message = Input.getText();
		if (message.trim().length() > 0) {
			if (ChatController.validateLetters(message)) {
				Input.setDisable(true);
				sendButton.setDisable(true);
				String username = player.getUsername();
				Timestamp timestamp = null;
				try {
					timestamp = chatController.getTimestamp();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				chatController.sendChatMessageToDB(message, player);
				if (chatController.isMessageSent()) {
					chatmessages.add(new ChatMessage(timestamp, username, message));
					Input.clear();
				}
				refreshPane();
			}
		}

	}

	private void enableButtons() {
		Input.setDisable(false);
		sendButton.setDisable(false);
	}

	public void refreshPane() { // Refresh the pane to show messages
		chatBox.getItems().clear(); // Deletes all items from ChatBox Listview
		for (ChatMessage c : chatmessages) { // For every ChatMessage1 item in the ArrayList chatMessages:::::
			list.add(new MessagePane(c.getCurrentTimeString(), c.getUsername(), c.getMessage())); // Add the
																									// MessagePane1 item
																									// with the same
																									// parameters into
																									// the list
		}
		ObservableList<MessagePane> chatList = FXCollections.observableList(list);
		chatBox.setItems(chatList);
		chatBox.setStyle("-fx-background-color: lightblue");
		setPrefHeight(150);
		int index = list.size();
		chatBox.scrollTo(index);
	}

	public void refresh() throws SQLException { // Refresh chat messages

		chatmessages = chatController.getChatMessages(player);
		refreshPane();

	}
}