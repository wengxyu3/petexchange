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

	LoginPane() {
		// Create a pane and set its properties
		GridPane textFieldPane = new GridPane();
		textFieldPane.setAlignment(Pos.CENTER);
		textFieldPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		textFieldPane.setHgap(5.5);
		textFieldPane.setVgap(5.5);

		// Place nodes in the pane
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();

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
		textFieldPane.add(new Label("Password"), 0, 1);
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
		BorderPane newAccountConfirmationPane = new BorderPane();
		Scene newAccountConfirmationScene = new Scene(newAccountConfirmationPane);
		Stage newAccountConfirmationStage = new Stage();

		Pane isAccountCreatedPane = new Pane();
		Text isAccountCreatedText = new Text(50, 50, "");
		isAccountCreatedPane.getChildren().add(isAccountCreatedText);
		Button newAccountConfirmationCloseButton = new Button("OK");
		newAccountConfirmationPane.setTop(isAccountCreatedText);
		newAccountConfirmationPane.setCenter(newAccountConfirmationCloseButton);
		newAccountConfirmationStage.setScene(newAccountConfirmationScene);

		isAccountCreatedText.setText("Account Created!");
		try {
			ownerList.Register(username, password);
		} catch (FileExistsException e1) {
			isAccountCreatedText.setText("This username is already taken.");
		}

		newAccountConfirmationStage.show();
		newAccountConfirmationCloseButton.setOnAction(e1 -> {

			newAccountConfirmationStage.close();
		});
	}

	private void loginHandle(Event e, String username, String password) {
		try {
			Account loginAccount = new Account(username, password, false);
			// TODO progress to next scene
			BulletinPane chatRoom = new BulletinPane(loginAccount);
			Scene chatRoomScene = new Scene(chatRoom);
			Stage chatRoomStage = new Stage();
			chatRoomStage.setScene(chatRoomScene);
			chatRoomStage.show();
		} catch (PasswordMismatchException e1) {
			BorderPane errorPane = new BorderPane();
			Scene errorScene = new Scene(errorPane);
			Stage errorStage = new Stage();
			Pane errorTextPane = new Pane();
			Text errorText = new Text(50, 50, "Username/Password does not match.");
			errorTextPane.getChildren().add(errorText);
			Button errorCloseButton = new Button("OK");

			errorPane.setTop(errorText);
			errorPane.setCenter(errorCloseButton);
			errorStage.setScene(errorScene);

			errorCloseButton.setOnAction(e2 -> {
				errorStage.close();
			});

			errorStage.show();
		}
	}
}
