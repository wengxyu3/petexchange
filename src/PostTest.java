package src;

import backEnd.Account;
import backEnd.PasswordMismatchException;
import backEnd.UserDisplayType;
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
//		Account account = new Account();
		Scene scene = null;
		try {
			MainPane toshow = new MainPane(new Account("abc", "xxy", true));
			scene = new Scene(toshow);
			primaryStage.setScene(scene);
			primaryStage.show();
			toshow.menuBarSetup("bulletinstorage/");
		} catch (PasswordMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//new BulletinPane("abc", "testbulletinboard1"));
		
	}

}
