package src;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorStage extends Stage {
	ErrorStage(String inputText) {
		super();
		BorderPane errorPane = new BorderPane();
		Scene errorScene = new Scene(errorPane);
		Pane errorTextPane = new Pane();
		Text errorText = new Text(50, 50, inputText);
		errorTextPane.getChildren().add(errorText);
		Button errorCloseButton = new Button("OK");

		errorPane.setTop(errorText);
		errorPane.setCenter(errorCloseButton);
		this.setScene(errorScene);

		errorCloseButton.setOnAction(e2 -> {
			this.close();
		});
	}
}
