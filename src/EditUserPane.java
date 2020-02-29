package src;

import backEnd.Account;
import backEnd.UserDisplayType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class EditUserPane extends BorderPane {
	Account account;
	Text[] textArray;
	TextField[] textFieldArray;

	EditUserPane(Account inputAccount) {
		account = inputAccount;
		GridPane informationPane = new GridPane();

		textArray = new Text[UserDisplayType.length];
		textFieldArray = new TextField[UserDisplayType.length];

		for (UserDisplayType i : UserDisplayType.values()) {
			textArray[i.returnIntValue()] = new Text(i.returnStringValue());
			textFieldArray[i.returnIntValue()] = new TextField(account.get(i));

			informationPane.add(textArray[i.returnIntValue()], 0, i.returnIntValue() * 2);
			informationPane.add(textFieldArray[i.returnIntValue()], 0, i.returnIntValue() * 2 + 1);
		}

		GridPane buttonPane = new GridPane();

		Button resetButton = new Button("Reset");
		resetButton.setOnAction(e -> reset());
		buttonPane.add(resetButton, 1, 0);

		Button saveButton = new Button("Save");
		saveButton.setOnAction(e -> save());
		buttonPane.add(saveButton, 0, 0);

		this.setCenter(informationPane);
		this.setBottom(buttonPane);
	}

	void reset() {
		for (UserDisplayType i : UserDisplayType.values())
			textFieldArray[i.returnIntValue()].setText(account.get(i));
	}

	void save() {
		for (UserDisplayType i : UserDisplayType.values())
			account.edit(textFieldArray[i.returnIntValue()].getText(), i);
	}

}
