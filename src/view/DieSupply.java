package view;

import java.util.Arrays;

import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DieSupply extends Pane {

	
///////////////////////////////////////////////////////////////// Instance Variables
	
	private Rectangle die, rect;
	private Circle moveHandle;
	private double deltaX, deltaY;
	private Color dieColor;
	private Label dieNumberText;
	private final double handleRadius = 10;
	

///////////////////////////////////////////////////////////////// Constructor

	public DieSupply(Color newColor) {


/// Initializing Variables
		
		dieNumberText = new Label("8");
		dieNumberText.setFont(new Font("Helvetica",20));
		dieColor=newColor;
		
		die = createDraggableRectangle(623, 550, 40, 40);
		die.setFill(dieColor);
		die.setStrokeWidth(10);
		die.setStroke(Color.BLACK);
		
		this.getChildren().addAll(die,dieNumberText);

	}

	

///////////////////////////////////////////////////////////////// createDraggableRectangle

 /* Method that is responsible for the creation of the handle that 
 * is used to drag the die on the board. Furthermore the shape
 * is bind to the rectangle */
	
	
	private Rectangle createDraggableRectangle(double x, double y, double width, double height) {
			
	
		rect = new Rectangle(x, y, width, height);
		moveHandle = new Circle(handleRadius, Color.YELLOW);
		
/// Bind to the  bottom center of the Rectangle:
		
		moveHandle.centerXProperty().bind(rect.xProperty().add(rect.widthProperty().divide(2)));
		moveHandle.centerYProperty().bind(rect.yProperty().add(rect.heightProperty()));

		
/// Force the handle to be in the same parent as the die:

		rect.parentProperty().addListener((obs, oldParent, newParent) -> {
			for (Circle c : Arrays.asList(moveHandle)) {
				Pane currentParent = (Pane) c.getParent();
				if (currentParent != null) {
					currentParent.getChildren().remove(c);
				}
				((Pane) newParent).getChildren().addAll(c);
			}
		});

		Wrapper<Point2D> mouseLocation = new Wrapper<>();
		setUpDragging(moveHandle, mouseLocation);

		
/// Sets the label of the die in correct position
		
		moveHandle.setOnMouseDragged(event -> {
			if (mouseLocation.value != null) {
				 deltaX = event.getSceneX() - mouseLocation.value.getX();
				 deltaY = event.getSceneY() - mouseLocation.value.getY();
				 
				double newX = rect.getX() + deltaX;
				double newMaxX = newX + rect.getWidth();
				if (newX >= handleRadius && newMaxX <= rect.getParent().getBoundsInLocal().getWidth() - handleRadius) {
					rect.setX(newX);
				}
				double newY = rect.getY() + deltaY;
				double newMaxY = newY + rect.getHeight();
				if (newY >= handleRadius && newMaxY <= rect.getParent().getBoundsInLocal().getHeight() - handleRadius) {
					rect.setY(newY);
				}
				mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());

				
/// Sets the label of the die in correct position
				 
				 dieNumberText.setLayoutX(event.getSceneX());				 
				 dieNumberText.setLayoutY(event.getSceneY());
			}
		});
		return rect;
	}

	
///////////////////////////////////////////////////////////////// setUpDragging

/* Method that defines what actions are executed 
 * if a drag is initiated and is released 
 * this method is also responsible for clipping of a die to the field*/	
	
	private void setUpDragging(Circle circle, Wrapper<Point2D> mouseLocation) {

		circle.setOnDragDetected(event -> {
			circle.getParent().setCursor(Cursor.CLOSED_HAND);
			mouseLocation.value = new Point2D(event.getSceneX(), event.getSceneY());
		});

		circle.setOnMouseReleased(event -> {
			circle.getParent().setCursor(Cursor.DEFAULT);
			mouseLocation.value = null;
			
			
/// Prints the current positions of the die		
						
			System.out.println("die1 "+event.getSceneX()+" "+event.getSceneY());
			
			
/// Logic that check allows clipping
			
			if(event.getSceneX()<530 && event.getSceneX()>515) {
				System.out.println("Just the X axis");   
				
				if(event.getSceneY()<485 && event.getSceneY()>460) {			
					System.out.println("super nice");   
				}
			}else {
				
			
/// Setting back the die to its supply position!	
				
			die.setX(623);
			die.setY(550);
			}
		});
	}

	static class Wrapper<T> {
		T value;
	}

}
