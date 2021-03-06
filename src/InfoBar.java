package src;

import java.util.ArrayList;

import backEnd.Account;
import backEnd.NullPetNameException;
import backEnd.Pet;
import backEnd.PetDisplayType;
import backEnd.UserDisplayType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InfoBar extends BorderPane {
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
						} catch (NullPetNameException e2) {
						}
				});
				petPane.add(inputPetNameField, 0, petBars.size());
			}
			inputPetNameField.clear();

		});

		pane.setBottom(addPetButton);

		ScrollPane scrollPetPane = new ScrollPane();
		scrollPetPane.setContent(pane);
		this.setCenter(scrollPetPane);

		Button userBar = userSetup();
		this.setBottom(userBar);
	}

	private void addPetBar(Pet inputPet) {
		PetBar petBar = new PetBar(inputPet);
		petBar.petPane.deleteButton.setOnAction(e -> {
			account.deletePet(petBar.returnPet());
			petBar.petStage.close();
			petUpdate();
		});
		petBar.petPane.saveButton.setOnAction(e -> {
			for (PetDisplayType i : PetDisplayType.values())
				try {
					account.editPet(petBar.returnPet(), petBar.petPane.textFieldArray[i.returnIntValue()].getText(), i);
					petBar.petStage.close();
					petBar.petButton.setText(petBar.returnPet().getName());
					petUpdate();
				} catch (NullPetNameException e1) {
					PopUpStage errorStage = new PopUpStage("This pet must have a name.");
					errorStage.show();
				}

		});

		petBars.add(petBar);
		petPane.add(petBar, 0, petBars.indexOf(petBar));

	}

	private void petSetup() {
		for (int i = 0; i < account.getPets().size(); i++)
			addPetBar(account.getPets().get(i));

	}

	private void petUpdate() {
		petBars.clear();
		petPane.getChildren().clear();
		petSetup();
	}

	private Button userSetup() {
		Button userBar = new Button(account.get(UserDisplayType.USERNAME));
		userBar.setOnAction(e -> {
			EditUserPane userPane = new EditUserPane(account);

			Scene scene = new Scene(userPane);
			Stage userStage = new Stage();
			userStage.setScene(scene);
			userPane.saveButton.setOnAction(e1 -> {
				userPane.save();
				userStage.close();
				this.setBottom(userSetup());
			});

			userStage.show();
		});

		return userBar;
	}
}
