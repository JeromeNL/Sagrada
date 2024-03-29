package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class RegisterView extends VBox {
	private ImageView imageView;

	public RegisterView() {

		InputStream stream;
		try {
			stream = new FileInputStream("LoginImg.png");
			Image image = new Image(stream);
			imageView = new ImageView();
			imageView.setImage(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitWidth(575);
			imageView.setPreserveRatio(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		VBox box = new VBox();
		HBox sagradaText = new HBox();
		HBox layout = new HBox();
		HBox buttons = new HBox(5);
		VBox text = new VBox();
		VBox textFields = new VBox(10);
		StackPane colorBurn = new StackPane();

		TextField tfName = new TextField();
		PasswordField tfPassword = new PasswordField();
		PasswordField tfConfirm = new PasswordField();
		Button Back = new Button("  Back  ");
		Button Register = new Button("Register");
		Text Sagradatxt = new Text("SAGRADA");

		Rectangle burnOverlay = new Rectangle();

		burnOverlay.setWidth(500);
		burnOverlay.setHeight(700);
		burnOverlay.setFill(Color.BLUE);
		burnOverlay.setBlendMode(BlendMode.COLOR_BURN);

		box.getChildren().addAll(sagradaText, layout);
		colorBurn.getChildren().addAll(imageView, burnOverlay);
		layout.getChildren().addAll(textFields, colorBurn);
		sagradaText.getChildren().addAll(Sagradatxt);
		textFields.getChildren().addAll(text, buttons);
		text.getChildren().addAll(tfName, tfPassword, tfConfirm);
		buttons.getChildren().addAll(Back, Register);
		this.getChildren().addAll(box);

		box.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfPassword.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setPromptText("Username");
		tfConfirm.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfConfirm.setPromptText("Confirm Password");
		tfPassword.setPromptText("Password");
		sagradaText.setAlignment(Pos.CENTER);
		layout.setAlignment(Pos.CENTER);
		textFields.setAlignment(Pos.CENTER);
		sagradaText.setMinHeight(100);
		textFields.setMinWidth(400);

		Sagradatxt.setStyle("-fx-font: 80 Arial;" +

				"-fx-fill: white;");

		tfName.setStyle("-fx-border-color: white;\r\n" + "    -fx-border-width: 0 0 3 0;\r\n"
				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: white;");
		tfPassword.setStyle("-fx-border-color: white;\r\n" + "    -fx-border-width: 0 0 3 0;\r\n"
				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: white;");
		tfConfirm.setStyle("-fx-border-color: white;\r\n" + "    -fx-border-width: 0 0 3 0;\r\n"
				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: white;");

		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";

		Register.setStyle(IDLE_BUTTON_STYLE);
		Register.setOnMouseEntered(e -> Register.setStyle(HOVERED_BUTTON_STYLE));
		Register.setOnMouseExited(e -> Register.setStyle(IDLE_BUTTON_STYLE));
		Back.setStyle(IDLE_BUTTON_STYLE);
		Back.setOnMouseEntered(e -> Back.setStyle(HOVERED_BUTTON_STYLE));
		Back.setOnMouseExited(e -> Back.setStyle(IDLE_BUTTON_STYLE));

	}

}