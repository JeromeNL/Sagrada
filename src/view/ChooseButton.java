package view;

import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ChooseButton extends VBox{
	
	final static Color SAGRADAPINK = Color.rgb(247, 150, 150);
	
	public ChooseButton() {
		super();
		
		setAlignment(Pos.CENTER_RIGHT);
		setPadding(new Insets(0, 35, 35, 0));
		setBackground(new Background(new BackgroundFill( SAGRADAPINK,null,null)));
		
		createButton();

	}
	
	
	public void createButton() {
	
		Rectangle button = new Rectangle(150, 50);

		
		getChildren().addAll(button);
		StackPane stack = new StackPane();
		button.setFill(Color.TRANSPARENT);
		button.setArcWidth(10.0);
		button.setArcHeight(10.0);
		
		button.setStroke(Color.WHITE);
		button.setStrokeWidth(4.0);

		
		Text text = new Text(" Kiezen ");

		stack.getChildren().addAll(button, text);
		text.setFont(Font.font("Futura Std", 18));
		text.setFill(Color.WHITE);
		stack.setAlignment(Pos.CENTER_RIGHT);
		
		StackPane.setMargin(text, new Insets(10, 45, 10, 60));
		getChildren().add(stack);
		button.hoverProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean show) -> {
			if(show) {
				button.setFill(Color.WHITE);
				text.setFill(SAGRADAPINK);
			} else {
				button.setFill(Color.TRANSPARENT);
				button.setArcWidth(10.0);
				button.setArcHeight(10.0);
				
				button.setStroke(Color.WHITE);
				button.setStrokeWidth(4.0);
				text.setFill(Color.WHITE);
		
			}
		});
		

		
	}

	
}
