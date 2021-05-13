package view;

import javafx.scene.layout.BorderPane;

public class ToolcardView extends BorderPane {

	private ToolcardButtonView toolcardButton;
	private ToolcardCardView toolcardCard;

	public ToolcardView() {
		toolcardButton = new ToolcardButtonView();
		toolcardCard = new ToolcardCardView();

		this.setCenter(toolcardCard);
		this.setBottom(toolcardButton);
	}

}
