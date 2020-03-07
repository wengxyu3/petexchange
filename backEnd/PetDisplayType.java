package backEnd;

public enum PetDisplayType {

	NAME(0, "Name"), SPECIES(1, "Species"), DESCRIPTION(2, "Description");

	public final static int length = PetDisplayType.values().length;
	private int numberValue;
	private String stringValue;

	PetDisplayType(int input, String stringInput) {
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