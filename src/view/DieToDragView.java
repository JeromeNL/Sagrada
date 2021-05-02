package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.DieFieldModel;

// View of one die in the supply (a draggable die)

public class DieToDragView extends StackPane {

	private final int dieSize = 50;
	private Rectangle dieRectangle;
	private DieFieldModel dieField;

	public DieToDragView(DieFieldModel dieField) {
		this.dieField = dieField;
		
		// Styling the die
		dieRectangle = new Rectangle(dieSize, dieSize);
		dieRectangle.setStroke(Color.BLACK);
		dieRectangle.setStrokeWidth(2);
		dieRectangle.setFill(dieField.getDieColor());

		Label eyesCount = new Label(Integer.toString(dieField.getEyesCount()));
		eyesCount.setFont(new Font("Arial", 20));
		eyesCount.setTextFill(Color.BLACK);

		getChildren().addAll(dieRectangle, eyesCount);
		
		setUpDragAbility();
	}

	// Makes the die draggable.
	private void setUpDragAbility() {
		// Adding die data to clipboard so the patterncard can identify the die.
		setOnDragDetected(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent event) {

				Dragboard db = startDragAndDrop(TransferMode.ANY);
				
				// Adding the die data to the dragboard.
				ClipboardContent content = new ClipboardContent();
				content.putString(dieField.getEyesCount() + "" + dieField.getDieColor().toString());
				db.setContent(content);
				
				event.consume();
			}
		});

		// Change color of die on supply if placed on the board.
		setOnDragDone(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// Remove the die from supply if it was successfully placed on the patterncard.
				if (event.getTransferMode() == TransferMode.MOVE) {
					removeDieFromSupply();
				}
				event.consume();
			}
		});
	}
	
	private void removeDieFromSupply() {
		// to-do: code to remove die from the supply....
		dieRectangle.setFill(Color.BLACK); // temporary visual feedback
	}

}
