package view;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.DieFieldModel;

// View of one die in the patterncard (non-draggable die).

public class DieView extends StackPane {

	private final int dieSize = 50;
	private DieFieldModel dieField;
	private Rectangle dieFieldRectangle;

	public DieView(DieFieldModel dieField) {
		this.dieField = dieField;
		drawDieField();
		setUpAcceptDrag();
	}	
	
	// Allowing the placement of a die by dragging & dropping a supply die onto the DieView.
	public void setUpAcceptDrag() {
		setOnDragOver(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				// Accept drag if it is not dragged from the same node and if it contains date of the die in a string.
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					// Setting up transfer mode
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
				event.consume();
			}
		});

		// Showing visual feedback when a die is dragged over a field. Changes opacity of the field.
		setOnDragEntered(new EventHandler<DragEvent>() {
			public void handle(DragEvent event) {
				if (event.getGestureSource() != this && event.getDragboard().hasString()) {
					setOpacity(0.5);
				}
				event.consume();
			}
		});

		// Resets opacity back to normal value when draggable die is no longer on the field.
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
					
					// Extracting die data from the string.
					int eyesCount = Integer.valueOf(dieData.substring(0, 1));
					Color dieColor = Color.valueOf(dieData.substring(1));
					
					// Check if die can actually be placed.
					if (isValidMove(eyesCount, dieColor)) {
						dieField.placeDie(eyesCount, dieColor);
						drawDieField();
						diePlaced = true;
					}

				}

				// Let the DieSupplyView know if the die was actually placed.
				event.setDropCompleted(diePlaced); 
				event.consume();
			}
		});
	}

	// Checks if die can be placed. Returns boolean representing the validity.
	public boolean isValidMove(int eyesCount, Color dieColor) {
		// to-do: check if field already has a die on it
		// containsDie();
		
		// to-do: more checks...
		
		if (isCorrectNumber(eyesCount) && isCorrectColor(dieColor)) {
			return true;
		}
		return false;
	}
	
	// Checks if color requirement of diefield allows the die to be placed.
	public boolean isCorrectColor(Color dieColor) {
		Color colorRequirement = dieField.getColorRequirement();
		if (colorRequirement != Color.WHITE) {
			if (dieColor.equals(colorRequirement)) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}
	
	// Checks if number of eyes requirement of diefield allows the die to be placed.
	public boolean isCorrectNumber(int eyesCount) {
		int numberRequirement = dieField.getEyesCountRequirement();
		if (numberRequirement != 0) {
			if (numberRequirement == eyesCount) {
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
	
	// Sets the background color of the die.
	private void drawDieColor() {
		// Check if field has a die on it.
		if (dieField.getDieColor() != null) {
			// Fill field with die color.
			dieFieldRectangle.setFill(dieField.getDieColor());
		} else {
			// No die on field, so check if field has a color requirement.
			Color colorRequirement = dieField.getColorRequirement();
			if (colorRequirement != null) {
				// Set the color requirement as the background color.
				dieFieldRectangle.setFill(colorRequirement);
			} else {
				// No color requirement, so field color is white.
				dieFieldRectangle.setFill(Color.WHITE);
			}
		}
		getChildren().add(dieFieldRectangle);
	}
	
	// Draws the eyes count of the field. (from die or eyes count requirement).
	private void drawDieEyesCount() {
		// Retrieving dieEyesCount. If dieField has no die, the dieEyesCount is 0.
		int dieEyesCount = dieField.getEyesCount();
		
		// Check if field has a die on it.
		if (dieEyesCount >= 1 && dieEyesCount <= 6) {
			// Drawing die number on the field.
			Label numberLabel = new Label(Integer.toString(dieEyesCount));
			numberLabel.setFont(new Font("Arial", 20));
			numberLabel.setTextFill(Color.BLACK);
			getChildren().add(numberLabel);
		} else {
			// No die on the field, so check if the field has color requirement.
			int eyesCountRequirement = dieField.getEyesCountRequirement();
			// 0 means no eyesCountRequirement.
			if (eyesCountRequirement != 0) {
				// Draw eyes requirement label on the field.
				Label numberLabel = new Label(Integer.toString(eyesCountRequirement));
				numberLabel.setFont(new Font("Arial", 20));
				numberLabel.setTextFill(Color.GRAY);
				getChildren().add(numberLabel);
			}
		}
	}
}
