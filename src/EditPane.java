package src;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

abstract public class EditPane extends BorderPane {

	GridPane informationPane = new GridPane();
	Label[] textArray;
	TextField[] textFieldArray;
	Button saveButton;
	GridPane buttonPane = new GridPane();

	EditPane() {
		// setValues();
//		textArray = new Label[textStringArray.length];
//		textFieldArray = new TextField[textStringArray.length];

	}

	abstract void getValues();

	abstract void reset();

	abstract void save();

	void setValues() {
		getValues();

		for (int i = 0; i < textArray.length; i++) {
			informationPane.add(textArray[i], 0, i * 2);
			informationPane.add(textFieldArray[i], 0, i * 2 + 1);
		}

		Button resetButton = new Button("Reset");
		resetButton.setOnAction(e -> reset());
		buttonPane.add(resetButton, 1, 0);

		saveButton = new Button("Save");
		// saveButton.setOnAction(e -> save());
		buttonPane.add(saveButton, 0, 0);

		this.setCenter(informationPane);
		this.setBottom(buttonPane);
	}
}
