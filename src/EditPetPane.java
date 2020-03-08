package src;

import backEnd.Pet;
import backEnd.PetDisplayType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class EditPetPane extends BorderPane {
	protected Pet pet;
	Label[] textArray;
	TextField[] textFieldArray;
	protected Button saveButton;
	protected Button deleteButton;

	EditPetPane(Pet inputPet) {
		pet = inputPet;
		GridPane informationPane = new GridPane();

		textArray = new Label[PetDisplayType.length];
		textFieldArray = new TextField[PetDisplayType.length];

		for (PetDisplayType i : PetDisplayType.values()) {
			textArray[i.returnIntValue()] = new Label(i.returnStringValue());
			textFieldArray[i.returnIntValue()] = new TextField(pet.get(i));

			informationPane.add(textArray[i.returnIntValue()], 0, i.returnIntValue() * 2);
			informationPane.add(textFieldArray[i.returnIntValue()], 0, i.returnIntValue() * 2 + 1);
		}

		GridPane buttonPane = new GridPane();

		Button resetButton = new Button("Reset");
		resetButton.setOnAction(e -> reset());
		buttonPane.add(resetButton, 1, 0);

		saveButton = new Button("Save");
		// saveButton.setOnAction(e -> save());
		buttonPane.add(saveButton, 0, 0);

		deleteButton = new Button("Delete");
		buttonPane.add(deleteButton, 2, 0);

		this.setCenter(informationPane);
		this.setBottom(buttonPane);
	}

	private void reset() {
		for (PetDisplayType i : PetDisplayType.values())
			textFieldArray[i.returnIntValue()].setText(pet.get(i));
	}

//	void save() {
//		for (PetDisplayType i : PetDisplayType.values())
//			pet.edit(textFieldArray[i.returnIntValue()].getText(), i);
//
//	}
}
