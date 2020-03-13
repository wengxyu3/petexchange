package src;

import backEnd.Account;
import backEnd.UserDisplayType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditUserPane extends EditPane {
	Account account;

	EditUserPane(Account inputAccount) {
		account = inputAccount;
		setValues();
	}

	@Override
	void getValues() {
		textArray = new Label[UserDisplayType.length];
		textFieldArray = new TextField[UserDisplayType.length];

		for (UserDisplayType i : UserDisplayType.values()) {
			textArray[i.returnIntValue()] = new Label(i.returnStringValue());
			textFieldArray[i.returnIntValue()] = new TextField(account.get(i));
		}
	}

	@Override
	void reset() {
		for (UserDisplayType i : UserDisplayType.values())
			textFieldArray[i.returnIntValue()].setText(account.get(i));
	}

	@Override
	void save() {
		for (UserDisplayType i : UserDisplayType.values())
			account.edit(textFieldArray[i.returnIntValue()].getText(), i);
	}

}
