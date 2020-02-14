package src;

import backEnd.Pet;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class PetBar extends Pane {
	Pet pet;

	PetBar(Pet inputPet) {
		pet = inputPet;

		Text petName = new Text(pet.getName());

		this.getChildren().add(petName);
	}
}