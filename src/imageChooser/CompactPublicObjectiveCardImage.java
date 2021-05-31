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

public class CompactPublicObjectiveCardImage extends Pane {


	private String imageURL;
	private String combinedURL;
	private int imageNumber;

	public CompactPublicObjectiveCardImage(int newImageNumber) {
		super();

		imageNumber = newImageNumber;
		combinedURL = "/Images/Compact Public Objectives/" + imageNumber + ".png";
		imageURL = combinedURL.toString();
		addCards();
		
	}

	public void addCards() {

		Image toolCardImage = new Image(getClass().getResource(imageURL).toString());
		ImageView imageView = new ImageView(toolCardImage);
		getChildren().addAll(imageView);

	}

}
