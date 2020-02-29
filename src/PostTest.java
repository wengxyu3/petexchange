package src;

import backEnd.Account;
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
//		Scene scene = new Scene(new UserPane(new Account()));
		Scene scene = new Scene(new BulletinPane(new Account(), "testbulletinboard1"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
