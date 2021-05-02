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
import model.Die;

// View of one die in the supply (a draggable die)

public class DieToDragView extends StackPane {

	private final int dieSize = 50;
	private Rectangle dieRectangle;
	private Die die;

	public DieToDragView(Die die) {
		this.die = die;
		
		// Styling the die
		dieRectangle = new Rectangle(dieSize, dieSize);
		dieRectangle.setStroke(Color.BLACK);
		dieRectangle.setStrokeWidth(2);
		dieRectangle.setFill(die.getColor());

		Label eyesCount = new Label(Integer.toString(die.getEyesCount()));
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
				
				// Shows the die that is being dragged mext to cursor.
				db.setDragView(snapshot(null, null), 35, 35);
				
				// Adding the die data to the dragboard.
				ClipboardContent content = new ClipboardContent();
				content.putString(die.getEyesCount() + "" + die.getColor().toString());
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
