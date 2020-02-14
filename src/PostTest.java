package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PostTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO replace this with BulletinBoard
		Scene scene = new Scene(new BulletinPane("testusername2", "testbulletinboard"));

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
