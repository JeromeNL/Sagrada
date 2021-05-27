package view;

import javafx.scene.layout.BorderPane;

public class ToolcardView extends BorderPane {

	private ChooseButton toolcardButton;
	private ToolcardCardView toolcardCard;

	public ToolcardView() {
		toolcardButton = new ChooseButton();
		toolcardCard = new ToolcardCardView();

		this.setCenter(toolcardCard);
		this.setBottom(toolcardButton);
	}

}
