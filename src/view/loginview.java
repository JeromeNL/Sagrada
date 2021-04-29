package view;
import java.io.FileInputStream;
import java.io.InputStream;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class loginview extends Application {
	private int Width = 1280;
	private int Height = 720;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		
		InputStream stream = new FileInputStream(".//LoginImg.png");
	      Image image = new Image(stream);
	      ImageView imageView = new ImageView();
	      imageView.setImage(image);
	      imageView.setX(10);
	      imageView.setY(10);
	      imageView.setFitWidth(575);
	      imageView.setPreserveRatio(true);
		
		VBox Box = new VBox();
		HBox SagradaText = new HBox();
		HBox Indeling = new HBox();
		HBox knoppen = new HBox(5);
		VBox tekst = new VBox();
		VBox Textvakken = new VBox(10);
	
		Stage loginstage = new Stage();
		Scene loginscene = new Scene(Box ,Width, Height, Color.rgb(247, 150, 150));
	
		TextField tfName = new TextField();
		PasswordField tfPassword = new PasswordField();
		Button Login = new Button("  Login  ");
		Button Register = new Button("Register");
		Text Sagradatxt = new Text("SAGRADA");
		
		Box.getChildren().addAll(SagradaText, Indeling);
		Indeling.getChildren().addAll(Textvakken, imageView);
		SagradaText.getChildren().addAll(Sagradatxt);
		Textvakken.getChildren().addAll(tekst, knoppen);
		tekst.getChildren().addAll(tfName, tfPassword);
		knoppen.getChildren().addAll(Login, Register);
		
		Box.setBackground(new Background(new BackgroundFill(Color.rgb(247, 150, 150), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setBackground(new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfPassword.setBackground(new Background(new BackgroundFill(Color.rgb(247, 150, 150, 0.6), CornerRadii.EMPTY, Insets.EMPTY)));
		tfName.setPromptText("Username");
		tfPassword.setPromptText("Password");
		SagradaText.setAlignment(Pos.CENTER);
		Indeling.setAlignment(Pos.CENTER);
		Textvakken.setAlignment(Pos.CENTER);
		SagradaText.setMinHeight(100);
		Textvakken.setMinWidth(400);
		
		Sagradatxt.setStyle(
				"-fx-font: 80 futura;" +
		
						"-fx-fill: white;"
				);
		
		tfName.setStyle(
			"-fx-border-color: white;\r\n" + 
			"    -fx-border-width: 0 0 3 0;\r\n" + 
			"    -fx-underline: true;" +
			"-fx-prompt-text-fill: white;"
				);
		tfPassword.setStyle(
			"-fx-border-color: white;\r\n" + 
			"    -fx-border-width: 0 0 3 0;\r\n" + 
			"    -fx-underline: true;" +
			"-fx-prompt-text-fill: white;"
							);
		
		String IDLE_BUTTON_STYLE = "-fx-border-width: 2;" +
				"-fx-border-color: white;" +
				"-fx-background-color: transparent;" +
				"-fx-font-size: 20;" +
				"-fx-text-fill: white;";
		String HOVERED_BUTTON_STYLE = "-fx-border-width: 2;" +
				"-fx-border-color: white;" +
				"-fx-background-color:white;" +
				"-fx-font-size: 20;" +
				"-fx-text-fill:blue;";

		Register.setStyle(IDLE_BUTTON_STYLE);
		Register.setOnMouseEntered(e -> Register.setStyle(HOVERED_BUTTON_STYLE));
		Register.setOnMouseExited(e -> Register.setStyle(IDLE_BUTTON_STYLE));
		Login.setStyle(IDLE_BUTTON_STYLE);
		Login.setOnMouseEntered(e -> Login.setStyle(HOVERED_BUTTON_STYLE));
		Login.setOnMouseExited(e -> Login.setStyle(IDLE_BUTTON_STYLE));
		
				
		loginstage.setScene(loginscene);
		loginstage.show();
		
		
	}

}