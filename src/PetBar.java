package src;

import backEnd.Pet;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PetBar extends Pane {
	Pet pet;
	Button petButton;
	PetPane petPane;
	Stage petStage;

	PetBar(Pet inputPet) {
		pet = inputPet;

		petPane = new PetPane(pet);

		// Text petName = new Text(pet.getName());
		petPane.saveButton.setOnAction(e -> {
			petPane.save();
			petStage.close();
			petButton.setText(pet.getName());
		});

		petButton = new Button(pet.getName());
		petButton.setOnAction(e -> {
			Scene scene = new Scene(petPane);
			petStage = new Stage();
			petStage.setScene(scene);
			petStage.show();
		});

		this.getChildren().add(petButton);
	}
}