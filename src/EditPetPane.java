package src;

import backEnd.NullPetNameException;
import backEnd.Pet;
import backEnd.PetDisplayType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditPetPane extends EditPane {
	protected Pet pet;
	Button deleteButton;

	EditPetPane(Pet inputPet) {
		pet = inputPet;
		setValues();
		deleteButton = new Button("Delete");
		buttonPane.add(deleteButton, 2, 0);
	}

	@Override
	void getValues() {
		textArray = new Label[PetDisplayType.length];
		textFieldArray = new TextField[PetDisplayType.length];

		for (PetDisplayType i : PetDisplayType.values()) {
			textArray[i.returnIntValue()] = new Label(i.returnStringValue());
			textFieldArray[i.returnIntValue()] = new TextField(pet.get(i));
		}
	}

	@Override
	void reset() {
		for (PetDisplayType i : PetDisplayType.values())
			textFieldArray[i.returnIntValue()].setText(pet.get(i));
	}

	@Override
	void save() {
		for (PetDisplayType i : PetDisplayType.values())
			try {
				pet.edit(textFieldArray[i.returnIntValue()].getText(), i);
			} catch (NullPetNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
