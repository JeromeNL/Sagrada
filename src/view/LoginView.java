package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView extends VBox {
	private ImageView imageView;

	public LoginView() {

		model.RegisterLoginModel rlm = new model.RegisterLoginModel();

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
		HBox warnTextBox = new HBox(5);
		VBox tekst = new VBox();
		VBox textFields = new VBox(10);
		StackPane colorBurn = new StackPane();

		Text warnText = new Text(rlm.getWarningText());
		TextField tfName = new TextField();
		PasswordField tfPassword = new PasswordField();
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

		box.getChildren().addAll(sagradaText, layout);
		colorBurn.getChildren().addAll(imageView, burnOverlay);
		layout.getChildren().addAll(textFields, colorBurn);
		sagradaText.getChildren().addAll(sagradaTxt);
		warnTextBox.getChildren().add(warnText);
		textFields.getChildren().addAll(tekst, buttons, warnTextBox);
		tekst.getChildren().addAll(tfName, tfPassword);
		buttons.getChildren().addAll(login, register);
		this.getChildren().addAll(box);

		box.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfPassword.setBackground(
				new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setPromptText("Username");
		tfPassword.setPromptText("Password");
		sagradaText.setAlignment(Pos.CENTER);
		layout.setAlignment(Pos.CENTER);
		textFields.setAlignment(Pos.CENTER);
		warnTextBox.setAlignment(Pos.BOTTOM_CENTER);
		sagradaText.setMinHeight(100);
		textFields.setMinWidth(400);

		warnText.setFont(Font.font("Arial", FontWeight.BOLD, 13));
		sagradaTxt.setStyle("-fx-font: 80 Arial;" +

				"-fx-fill: white;");

		tfName.setStyle("-fx-border-color: white;\r\n" + "    -fx-border-width: 0 0 3 0;\r\n"
				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: white;");
		tfPassword.setStyle("-fx-border-color: white;\r\n" + "    -fx-border-width: 0 0 3 0;\r\n"
				+ "    -fx-underline: true;" + "-fx-prompt-text-fill: white;");

		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color: transparent;" + "-fx-font-size: 20;" + "-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" + "-fx-border-color: white;"
				+ "-fx-background-color:white;" + "-fx-font-size: 20;" + "-fx-text-fill:blue;";

		register.setStyle(IDLE_BUTTON_STYLE);
		register.setOnMouseEntered(e -> register.setStyle(HOVERED_BUTTON_STYLE));
		register.setOnMouseExited(e -> register.setStyle(IDLE_BUTTON_STYLE));

		// Event after clicking the "Register" button
		register.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Register knop test");
				;

				rlm.setRegisterUsernameGiven(tfName.getText().toString().toLowerCase());
				rlm.setRegisterPasswordGiven(tfPassword.getText().toString().toLowerCase());
				rlm.registerAccount();
				warnText.setText(rlm.getWarningText());
				if (rlm.getWarningColor().equals("red")) {
					warnText.setFill(Color.RED);
				} else if (rlm.getWarningColor().equals("green")) {
					warnText.setFill(Color.GREEN);
				} else {
					warnText.setFill(Color.BLACK);
				}

			}
		});

		login.setStyle(IDLE_BUTTON_STYLE);
		login.setOnMouseEntered(e -> login.setStyle(HOVERED_BUTTON_STYLE));
		login.setOnMouseExited(e -> login.setStyle(IDLE_BUTTON_STYLE));

		// Event after clicking the "Login" button
		login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("Login knop test");
				;

				rlm.setLoginUsernameGiven(tfName.getText().toString().toLowerCase());
				rlm.setLoginPasswordGiven(tfPassword.getText().toString().toLowerCase());
				rlm.tryLogin();
				warnText.setText(rlm.getWarningText());
				if (rlm.getWarningColor().equals("red")) {
					warnText.setFill(Color.RED);
				} else if (rlm.getWarningColor().equals("green")) {
					warnText.setFill(Color.GREEN);
				} else {
					warnText.setFill(Color.BLACK);
				}
			}
		});

	}

}