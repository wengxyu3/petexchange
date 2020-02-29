package src;

import backEnd.Pet;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PetBar extends Pane {
	private Pet pet;
	protected Button petButton;
	protected EditPetPane petPane;
	protected Stage petStage;

	PetBar(/* Account inputAccount, */ Pet inputPet) {
		pet = inputPet;

		petPane = new EditPetPane(pet);

		// Text petName = new Text(pet.getName());

		petButton = new Button(pet.getName());
		petButton.setOnAction(e -> {
			Scene scene = new Scene(petPane);
			petStage = new Stage();
			petStage.setScene(scene);
			petStage.show();
		});

		this.getChildren().add(petButton);
	}

	Pet returnPet() {
		return pet;
	}
}