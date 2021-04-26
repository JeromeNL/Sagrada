package view;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.DieFieldModel;

public class DieView extends StackPane {

///////////////////////////////////////////////////////////////// Instance Variables

	private final int dieSize = 50;
	private DieFieldModel dieField;

///////////////////////////////////////////////////////////////// Constructor	
	
	public DieView(DieFieldModel dieField) {
		this.dieField = dieField;
		update();

	
///// DIENT DIT NOG EEN NUT ?????		
		
////		Rectangle dieRectangle = new Rectangle(dieSize, dieSize);
////		dieRectangle.setStroke(Color.BLACK);
////		dieRectangle.setFill(dieColor);
////		dieRectangle.setStrokeWidth(5);
//
////		this.getChildren().addAll(dieRectangle, numberLabel);
	
	}

///////////////////////////////////////////////////////////////// update

/* Method that is responsible for the creation of a die */
	
	public void update() {
		int dieEyesCount = dieField.getEyesCount();

		Rectangle dieFieldRectangle = new Rectangle(dieSize, dieSize);
		dieFieldRectangle.setStroke(Color.BLACK);
		dieFieldRectangle.setStrokeWidth(2);
			
		if (dieField.getDieColor() != null) {
			dieFieldRectangle.setFill(dieField.getDieColor());
		} else {
			Color colorRequirement = dieField.getColorRequirement();
			if (colorRequirement != null) {
				dieFieldRectangle.setFill(colorRequirement);
			} else {
				dieFieldRectangle.setFill(Color.WHITE);
			}
		}

		getChildren().add(dieFieldRectangle);

/// Check if a field has a die placed on it.
		
		if (dieEyesCount >= 1 && dieEyesCount <= 6) {
			Label numberLabel = new Label(Integer.toString(dieEyesCount));
			numberLabel.setFont(new Font("Arial", 20));
			numberLabel.setTextFill(Color.BLACK);
			getChildren().add(numberLabel);
		} else {
			
			
/// Check if a field has color requirement
			
			int eyesCountRequirement = dieField.getEyesCountRequirement();
			if (eyesCountRequirement != 0) {
				Label numberLabel = new Label(Integer.toString(eyesCountRequirement));
				numberLabel.setFont(new Font("Arial", 20));
				numberLabel.setTextFill(Color.GRAY);
				getChildren().add(numberLabel);
			}
		}
	}
}
