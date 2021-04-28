package view;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class GameButtonView extends Pane {
	
///////////////////////////////////////////////////////////////// Instance Variables
	
	private Circle backButton, toolCardButton1, toolCardButton2, toolCardButton3, confirmButton;
	private Group group;
	private ToolCardInUseView toolCardInUseView;
	private boolean test = false;
	
///////////////////////////////////////////////////////////////// Constructor
	
	
	GameButtonView() {
		
	
		
		
/// Initializing Variables
		
		toolCardInUseView = new ToolCardInUseView();
		
		backButton = new Circle(450, 650, 25);
		backButton.setStroke(Color.BLACK);
		backButton.setStrokeWidth(5);
		backButton.setFill(Color.WHITE);

		toolCardButton1 = new Circle(550, 650, 25);
		toolCardButton1.setStroke(Color.BLACK);
		toolCardButton1.setFill(Color.WHITE);
		toolCardButton1.setStrokeWidth(5);

		toolCardButton2 = new Circle(650, 650, 25);
		toolCardButton2.setStroke(Color.BLACK);
		toolCardButton2.setFill(Color.WHITE);
		toolCardButton2.setStrokeWidth(5);
		
		toolCardButton3 = new Circle(750, 650, 25);
		toolCardButton3.setStroke(Color.BLACK);
		toolCardButton3.setFill(Color.WHITE);
		toolCardButton3.setStrokeWidth(5);

		confirmButton = new Circle(850, 650, 25);
		confirmButton.setStroke(Color.BLACK);
		confirmButton.setFill(Color.WHITE);
		confirmButton.setStrokeWidth(5);

		
/// Adding the buttons to a group so its easier to redefine their position if needed

		group = new Group();
		group.getChildren().addAll(backButton, toolCardButton1, toolCardButton2, toolCardButton3, confirmButton);
		
		
/// Defines the Color that is shown when hovered on a certain button and the color
///	that is shown when the cursor leaves the button.
		
		registerHandler(backButton, Color.WHITE, Color.LIGHTBLUE);
		registerHandler(toolCardButton1, Color.WHITE, Color.LIGHTBLUE);
		registerHandler(toolCardButton2, Color.WHITE, Color.LIGHTBLUE);
		registerHandler(toolCardButton3, Color.WHITE, Color.LIGHTBLUE);
		registerHandler(confirmButton, Color.WHITE, Color.LIGHTBLUE);

		
/// Buttons and their respective Functions		

		backButton.setOnMouseClicked(e -> System.out.println("button is pressed"));
		toolCardButton1.setOnMouseClicked(e -> {
			
			System.out.println("button is pressedllllll");
			
			if(test == false) {
			
			toolCardInUseView.getChildren().clear();
			toolCardInUseView.getChildren().addAll(toolCardInUseView.getR(),toolCardInUseView.getCard(),toolCardInUseView.getBackButton(),toolCardInUseView.getJoinButtonText());
			group.getChildren().clear();
			group.getChildren().addAll(backButton, toolCardButton1, toolCardButton2, toolCardButton3, confirmButton,toolCardInUseView);	
			}
		});
		
		toolCardButton2.setOnMouseClicked(e -> System.out.println("button is pressed"));
		toolCardButton3.setOnMouseClicked(e -> System.out.println("button is pressed"));
		confirmButton.setOnMouseClicked(e -> System.out.println("button is pressed"));
		
		
/// Adding the the Buttons to the Parent of the Class
		
		this.getChildren().addAll(group);
		
	}
	
/// Method that is responsible for the coloring of the buttons and its response
/// on entering the buttons area and leaving it.

	private void registerHandler(Shape s, Color defaultColor, Color hoverColor) {
		s.setOnMouseEntered(e -> s.setFill(hoverColor));
		s.setOnMouseExited(e -> s.setFill(defaultColor));

		
	}
}
