package src;

import java.time.format.DateTimeFormatter;

import backEnd.Post;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PostHUD extends BorderPane {
	Post post;
	// Pane parentPane;
	Button deleteButton;
	FlowPane topmenu= new FlowPane();
	PostHUD(Post inputPost, String username) {
		//The username string is not the username of the post, its the accessor's username
		super();
		post = inputPost;
		// parentPane = parentInput;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd HH:mm");
		String toPost = post.getMessage();
		Label postText = new Label(toPost);
		Label userDisplay = new Label(post.getUsername());
		Label dateDisplay = new Label(post.getDateTime().format(formatter));
		Label idDisplay = new Label("#"+ Long.toString(post.getId()));
		Label display = new Label(post.getUsername() + "  " + post.getDateTime().format(formatter) + "   #" +Long.toString(post.getId()) );
		display.setFont(Font.font("calibri", 16));
//		topmenu.getChildren().add(display);
		topmenu.getChildren().add(userDisplay);
		topmenu.getChildren().add(dateDisplay);
		topmenu.getChildren().add(idDisplay);
//		dateDisplay.setFont(Font.font("calibri", 24));
//		idDisplay.setFont(Font.font("calibri", 24));
//		Text t = new Text(toPost);
//		postText.setEditable(false);
//		postText.setWrapText(true);
//		postText.setPrefHeight(50);
//		t.setWrappingWidth(500);
		postText.setWrapText(true);
		deleteButton = new Button("Delete");
		deleteButton.setPrefWidth(60);
		this.setTop(topmenu);
//		this.add(dateDisplay, 1, 0);
		this.setCenter(postText);
		postText.setCenterShape(false);
//		this.add(idDisplay, 2, 0);
		if(username.equals(post.getUsername())){
			topmenu.getChildren().add(deleteButton);
		}
		deleteButton.setOnAction(e -> {
			inputPost.delete(true, username);
		});
//		GridPane.setHgrow(postText, javafx.scene.layout.Priority.ALWAYS);
	}

}
