package view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MessagePane extends StackPane {
	
	public MessagePane(String timestamp, String username, String text) {
		String message = timestamp + " - " + username + ": " + text;
		Label label = new Label(message);
		label.setWrapText(true);
		getChildren().add(label);
		setMaxSize(320, Region.USE_PREF_SIZE );
		setAlignment(label, Pos.TOP_LEFT);
		
		label.setStyle("-fx-font-size: 16; -fx-text-fill: black; -fx-background-color: transparent");
		
	}
	
}