package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import controller.DatabaseController;
import controller.RegisterLoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.RegisterLoginModel;

public class MainMenu extends Pane {
	private ImageView imageView;

	public MainMenu(DatabaseController dbController, RegisterLoginController registerLoginController) {

		RegisterLoginModel rlm = new RegisterLoginModel(dbController, registerLoginController);

		InputStream stream;
		try {
			stream = new FileInputStream("LoginImg.png");
			Image image = new Image(stream);
			imageView = new ImageView();
			imageView.setImage(image);
			imageView.setTranslateX(600);
			imageView.setFitWidth(575);
			imageView.setPreserveRatio(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}



		
		Button login = new Button("  Login  ");
		Button register = new Button("Register");
		Text sagradaTxt = new Text("SAGRADA");
		sagradaTxt.setTranslateX(-300);
		sagradaTxt.setTranslateY(200);

		Rectangle burnOverlay = new Rectangle();
		burnOverlay.setWidth(500);
		burnOverlay.setHeight(700);
		burnOverlay.setFill(Color.TRANSPARENT);
		burnOverlay.setBlendMode(BlendMode.COLOR_BURN);

//		box.getChildren().addAll(sagradaText, layout,imageView);
//		sagradaText.getChildren().addAll(sagradaTxt);
//		
//		
//		
//		buttons.getChildren().addAll(login, register);
//		this.getChildren().addAll(box);
//
//		box.setBackground(
//				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
//		
//		sagradaText.setAlignment(Pos.CENTER);
//		layout.setAlignment(Pos.CENTER);
//		
//		warnTextBox.setAlignment(Pos.BOTTOM_CENTER);
//		sagradaText.setMinHeight(100);
		
		sagradaTxt.setStyle("-fx-font: 80 Arial;" +

				"-fx-fill: white;");

		

		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";

	



		login.setStyle(IDLE_BUTTON_STYLE);
		login.setOnMouseEntered(e -> login.setStyle(HOVERED_BUTTON_STYLE));
		login.setOnMouseExited(e -> login.setStyle(IDLE_BUTTON_STYLE));

		// Event after clicking the "Login" button
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Login knop test");
				;

				
			
				
			}
		});

	}

}