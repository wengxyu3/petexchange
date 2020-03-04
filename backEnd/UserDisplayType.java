package backEnd;

public enum UserDisplayType {

	USERNAME(0, "Username"), PASSWORD(1, "Password"), DESCRIPTION(2, "Description"), EMAIL(3, "Email"),
	ADRESS(4, "Adress"), CITY(5, "City"), STATE(6, "State"), ZIPCODE(7, "Zip Code"), PHONENUMBER(8, "Phone Number"),
	CREDITNUMBER(9, "Credit Card Number");

	public final static int length = UserDisplayType.values().length;
	private int numberValue;
	private String stringValue;

	UserDisplayType(int input, String stringInput) {
		numberValue = input;
		stringValue = stringInput;
	}

	public int returnIntValue() {
		return numberValue;
	}

	public String returnStringValue() {
		return stringValue;
	}

}