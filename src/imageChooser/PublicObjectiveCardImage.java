package imageChooser;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class PublicObjectiveCardImage extends Pane {

	private int widthCard = 200;
	private int heightCard = 300;
	private String imageURL;
	private String combinedURL;
	private int imageNumber;

	public PublicObjectiveCardImage(int newImageNumber) {
		super();
		
		imageNumber = newImageNumber;
		combinedURL = "/Images/Public Objectives/" + imageNumber + ".png";
		imageURL = combinedURL.toString();
		addCards();

	}

	public void addCards() {

		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		imageView.setFitHeight(heightCard);
		imageView.setFitWidth(widthCard);
		getChildren().addAll(imageView);

	}

}
