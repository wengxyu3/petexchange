package src;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUpStage extends Stage {
	PopUpStage(String inputText) {
		super();
		BorderPane popUpPane = new BorderPane();
		Scene popUpScene = new Scene(popUpPane);
		Pane popUpTextPane = new Pane();
		Text popUpText = new Text(50, 50, inputText);
		popUpTextPane.getChildren().add(popUpText);
		Button popUpCloseButton = new Button("OK");

		popUpPane.setTop(popUpText);
		popUpPane.setCenter(popUpCloseButton);
		this.setScene(popUpScene);

		popUpCloseButton.setOnAction(e2 -> {
			this.close();
		});
	}
}
