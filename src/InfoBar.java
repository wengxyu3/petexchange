package src;

import java.util.ArrayList;

import backEnd.Account;
import backEnd.Pet;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class InfoBar extends ScrollPane {
	GridPane petPane;
	Account account;
	ArrayList<PetBar> petBars = new ArrayList<>();

	InfoBar(Account inputAccount) {
		account = inputAccount;

		BorderPane pane = new BorderPane();

		TextField usernameField = new TextField();
		usernameField.setText(account.getUsername());
		usernameField.setEditable(false);

		pane.setTop(usernameField);

		petPane = new GridPane();
		petSetup();

		pane.setCenter(petPane);

		Button addPetButton = new Button("Add Pet");
		TextField inputPetNameField = new TextField();

		addPetButton.setOnAction(e -> {
			if (!petPane.getChildren().contains(inputPetNameField)) {
				inputPetNameField.setOnKeyPressed(e1 -> {
					if (e1.getCode() == KeyCode.ENTER) {
						account.addPet(new Pet(inputPetNameField.getText()));
						addPetBar(account.getPets().get(account.getPets().size() - 1));
						petPane.getChildren().remove(inputPetNameField);
					}
				});
				petPane.add(inputPetNameField, 0, petBars.size());
			}
			inputPetNameField.clear();

		});

		pane.setBottom(addPetButton);

		this.setContent(pane);
	}

	void addPetBar(Pet inputPet) {
		PetBar petBar = new PetBar(inputPet);

		petBars.add(petBar);
		petPane.add(petBar, 0, petBars.indexOf(petBar));
	}

	void petSetup() {
		for (int i = 0; i < account.getPets().size(); i++)
			addPetBar(account.getPets().get(i));
	}

	void petUpdate() {
		petBars.clear();
		petPane.getChildren().clear();
		petSetup();
	}
}
