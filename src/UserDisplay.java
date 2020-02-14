package src;

import backEnd.Account;
import backEnd.DisplayType;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UserDisplay extends Pane {
	Account account;
	GridPane informationPane;
	Text[] textArray;
	TextField[] textFieldArray;

	UserDisplay(Account inputAccount) {
		account = inputAccount;
		informationPane = new GridPane();

		textArray = new Text[DisplayType.length];
		textFieldArray = new TextField[DisplayType.length];

		for (DisplayType i : DisplayType.values()) {
			textArray[i.returnIntValue()] = new Text(i.returnStringValue());
			textFieldArray[i.returnIntValue()] = new TextField();

			informationPane.add(textArray[i.returnIntValue()], 0, i.returnIntValue() * 2);
			informationPane.add(textFieldArray[i.returnIntValue()], 0, i.returnIntValue() * 2 + 1);
		}
		Button saveButton = new Button("Save");
		informationPane.add(saveButton, 0, DisplayType.length * 2 + 1);

		this.getChildren().add(informationPane);
	}

	void save(Event e) {
		for (DisplayType i : DisplayType.values())
			account.editHandler(textFieldArray[i.returnIntValue()].getText(), i);
	}

}
