package view;

import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.Die;
import model.GameColor;
import model.PatterncardField;

// View of one die in the patterncard (non-draggable die).

public class DieView extends StackPane {

	private final int dieSize = 50;
	private PatterncardField patternCardField;
	private Rectangle dieFieldRectangle;

	public DieView(PatterncardField patternCardField) {
		this.patternCardField = patternCardField;
		drawDieField();
		setUpAcceptDrag();
	}

	// Allowing the placement of a die by dragging & dropping a supply die onto the
	// DieView.
	public void setUpAcceptDrag() {
		setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// Accept drag if it is not dragged from the same node and if it contains date
				// of the die in a string.
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					// Setting up transfer mode
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		// Showing visual feedback when a die is dragged over a field. Changes opacity
		// of the field.
		setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					setOpacity(0.5);
				}
				event.consume();
			}
		});

		// Resets opacity back to normal value when draggable die is no longer on the
		// field.
		setOnDragExited(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				setOpacity(1);
				event.consume();
			}
		});

		// Executes when user drops the DieToDrag on a DieView.
		setOnDragDropped(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// Get the data (eyesCount and color) from the dragged die.
				Dragboard db = event.getDragboard();
				boolean diePlaced = false;
				if (db.hasString()) {

					String dieData = db.getString();
					System.out.println(dieData);
					String[] data = dieData.split("\\s+");
					
					// Extracting die data from the string.
					int dieNumber = Integer.valueOf(data[0]);
					GameColor dieColor = GameColor.valueOf(data[1]);
					int eyesCount = Integer.valueOf(data[2]);

					// Check if die can actually be placed.
					try {
						if (patternCardField.isValidMove(eyesCount, dieColor)) {
							patternCardField.placeDie(new Die(dieColor, eyesCount, dieNumber));
							drawDieField();
							diePlaced = true;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

				// Let the DieSupplyView know if the die was actually placed.
				event.setDropCompleted(diePlaced);
				event.consume();
			}
		});
	}

	// Checks if die can be placed. Returns boolean representing the validity.
	private boolean isValidMove(int eyesCount, GameColor dieColor) {
		if (fieldHasDie()) {
			return false;
		}
		
		// to-do: more checks...

		if (isCorrectNumber(eyesCount) && isCorrectColor(dieColor)) {
			return true;
		}
		return false;
	}

	private boolean fieldHasDie() {
		if (patternCardField.hasDie()) {
			return true;
		}
		return false;
	}

	// Checks if color requirement of diefield allows the die to be placed.
	private boolean isCorrectColor(GameColor dieColor) {
		if (patternCardField.hasColorRequirement()) {
			GameColor colorRequirement = patternCardField.getColorRequirement();
			if (colorRequirement == dieColor) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	// Checks if number of eyes requirement of diefield allows the die to be placed.
	private boolean isCorrectNumber(int eyesCount) {
		if (patternCardField.hasEyesCountRequirement()) {
			if (patternCardField.getEyesCountRequirement() == eyesCount) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	// Draws a die on the patterncard.
	private void drawDieField() {
		// Creating the frame of the die.
		dieFieldRectangle = new Rectangle(dieSize, dieSize);
		dieFieldRectangle.setStroke(Color.BLACK);
		dieFieldRectangle.setStrokeWidth(2);

		// Setting up the background color and the eyes count.
		drawDieColor();
		drawDieEyesCount();
	}

	// Sets the background color of the diefield.
	private void drawDieColor() {
		// Check if the field contains a die.
		if (patternCardField.hasDie()) {
			Die dieOnField = patternCardField.getDie();
			dieFieldRectangle.setFill(dieOnField.getColor().toFXColor().brighter());
		} else {
			// No die on field, so check if field has color requirement.
			if (patternCardField.hasColorRequirement()) {
				GameColor colorRequirement = patternCardField.getColorRequirement();
				
				// TODO: draw correct color depending on colorRequirement
				
				dieFieldRectangle.setFill(colorRequirement.toFXColor());
				dieFieldRectangle.setOpacity(0.7);
			} else {
				// No color requirement, so field color is white.
				dieFieldRectangle.setFill(Color.WHITE);
			}
		}
		getChildren().add(dieFieldRectangle);
	}

	// Draws the eyes count of the field. (from die or eyes count requirement).
	private void drawDieEyesCount() {
		Label eyesCountLabel = new Label();
		eyesCountLabel.setFont(new Font("Arial", 20));

		// Check if the field contains a die.
		if (patternCardField.hasDie()) {
			Die dieOnField = patternCardField.getDie();
			eyesCountLabel.setText(Integer.toString(dieOnField.getEyesCount()));
			eyesCountLabel.setTextFill(Color.BLACK);
		} else {
			// No die on field, so check if field has a eyes count requirement
			if (patternCardField.hasEyesCountRequirement()) {
				int eyesCountRequirement = patternCardField.getEyesCountRequirement();
				eyesCountLabel.setText(Integer.toString(eyesCountRequirement));
				eyesCountLabel.setTextFill(Color.GRAY);
			}
		}
		getChildren().add(eyesCountLabel);
	}
}

