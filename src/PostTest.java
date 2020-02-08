package src;

import java.time.LocalDateTime;
import java.util.ArrayList;

import backEnd.Post;
import backEnd.PostHUD;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PostTest extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// TODO replace this with BulletinBoard
		ArrayList<Post> posts = new ArrayList<>();

		posts.add(new Post(1, "abc", "afdfsfds", LocalDateTime.now()));
		posts.add(new Post(2, "cdf", "dfdsfdasfas", LocalDateTime.now()));

		GridPane postPane = new GridPane();
		for (int i = 0; i < posts.size(); i++)
			postPane.add(new PostHUD(posts.get(i)), 0, i);

		Scene scene = new Scene(postPane);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
