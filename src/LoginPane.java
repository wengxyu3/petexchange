package src;

import java.util.ArrayList;

import backEnd.Account;
import backEnd.FileExistsException;
import backEnd.OwnerList;
import backEnd.PasswordMismatchException;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPane extends Pane {

	OwnerList ownerList = new OwnerList("");
	ArrayList<Account> accounts = new ArrayList<>();
	TextField usernameField;
	PasswordField passwordField;

	LoginPane() {
		// Create a pane and set its properties
		GridPane textFieldPane = new GridPane();
		textFieldPane.setAlignment(Pos.CENTER);
		textFieldPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		textFieldPane.setHgap(5.5);
		textFieldPane.setVgap(5.5);

		// Place nodes in the pane
		usernameField = new TextField();
		passwordField = new PasswordField();

		usernameField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginHandle(e, usernameField.getText(), passwordField.getText());
		});
		passwordField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER)
				loginHandle(e, usernameField.getText(), passwordField.getText());
		});

		textFieldPane.add(new Label("Username:"), 0, 0);
		textFieldPane.add(usernameField, 1, 0);
		textFieldPane.add(new Label("Password:"), 0, 1);
		textFieldPane.add(passwordField, 1, 1);

		// Create BorderPane to make button alignment less of a hassle
		BorderPane loginPane = new BorderPane();

		GridPane loginButtonPane = new GridPane();
		loginButtonPane.setAlignment(Pos.TOP_CENTER);
		loginButtonPane.setHgap(3);

		Button loginButton = new Button("Log in");
		Button newAccountButton = new Button("Create Account");
		Button passwordReset = new Button("Reset Password");

		loginButtonPane.add(newAccountButton, 0, 0);
		loginButtonPane.add(loginButton, 1, 0);
		loginButtonPane.add(passwordReset, 2, 0);
		loginButton.setOnAction(e -> loginHandle(e, usernameField.getText(), passwordField.getText()));

		newAccountButton.setOnAction(e -> createAccountHandle(e, usernameField.getText(), passwordField.getText()));

		loginPane.setTop(textFieldPane);
		loginPane.setCenter(loginButtonPane);
		this.getChildren().add(loginPane);
	}

	private void createAccountHandle(Event e, String username, String password) {

		try {
			BorderPane newAccountConfirmationPane = new BorderPane();
			Scene newAccountConfirmationScene = new Scene(newAccountConfirmationPane);
			Stage newAccountConfirmationStage = new Stage();

			Text accountCreatedText = new Text(50, 50, "Account Created!");
			Button newAccountConfirmationCloseButton = new Button("OK");

			newAccountConfirmationPane.setTop(accountCreatedText);
			newAccountConfirmationPane.setCenter(newAccountConfirmationCloseButton);

			newAccountConfirmationStage.setScene(newAccountConfirmationScene);
			ownerList.Register(username, password);

			newAccountConfirmationStage.show();
			newAccountConfirmationCloseButton.setOnAction(e1 -> {

				newAccountConfirmationStage.close();
			});
		} catch (FileExistsException e1) {
			ErrorStage errorStage = new ErrorStage("This username is already taken.");
			errorStage.show();
		}
		textFieldClear();

	}

	private void loginHandle(Event e, String username, String password) {
		try {
			Account loginAccount = new Account(username, password, false);
			// TODO change to a better file name
			MainPane chatRoom = new MainPane(loginAccount);
			Scene chatRoomScene = new Scene(chatRoom);
			Stage chatRoomStage = new Stage();
			chatRoomStage.setScene(chatRoomScene);
			chatRoomStage.show();
		} catch (PasswordMismatchException e1) {
			ErrorStage errorStage = new ErrorStage("Username/Password does not match.");

			errorStage.show();
		}
		textFieldClear();
	}

	private void textFieldClear() {
		usernameField.clear();
		passwordField.clear();
	}
}
