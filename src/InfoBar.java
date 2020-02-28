package src;

import java.util.ArrayList;

import backEnd.Account;
import backEnd.Pet;
import backEnd.PetDisplayType;
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
		petUpdate();

		pane.setCenter(petPane);

		Button addPetButton = new Button("Add Pet");
		TextField inputPetNameField = new TextField();

		addPetButton.setOnAction(e -> {
			if (!petPane.getChildren().contains(inputPetNameField)) {
				inputPetNameField.setOnKeyPressed(e1 -> {
					if (e1.getCode() == KeyCode.ENTER /* && !inputPetNameField.getText().trim().isEmpty() */)
						try {
							account.addPet(new Pet(inputPetNameField.getText()));
							addPetBar(account.getPets().get(account.getPets().size() - 1));
							petPane.getChildren().remove(inputPetNameField);
						} catch (Exception e2) {
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
		petBar.petPane.deleteButton.setOnAction(e -> {
			account.deletePet(petBar.returnPet());
			petBar.petStage.close();
			petUpdate();
		});
		petBar.petPane.saveButton.setOnAction(e -> {
			for (PetDisplayType i : PetDisplayType.values())
				account.editPet(petBar.returnPet(), petBar.petPane.textFieldArray[i.returnIntValue()].getText(), i);
			petBar.petStage.close();
			petBar.petButton.setText(petBar.returnPet().getName());
			petUpdate();
		});

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
