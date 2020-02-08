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
//
//		ArrayList<Post> posts = new ArrayList<>();
//
//		posts.add(new Post(0, "abc", "afdfsfds", LocalDateTime.now()));
//		posts.add(new Post(1, "cdf", "dfdsfdasfas", LocalDateTime.now()));
//
//		GridPane postPane = new GridPane();
//		for (int i = 0; i < posts.size(); i++)
//			postPane.add(new PostHUD(posts.get(i)), 0, i);
//		postPane.add(new PostHUD(new Post(2, "adfdsfs", "dsfdsfdsafaasdfads", LocalDateTime.now())), 0, 6);
		Scene scene = new Scene(new BulletinPane());

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
