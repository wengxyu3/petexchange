package backEnd;

import org.json.simple.JSONObject;

//enum PetString{
//	NAME, SPECIES, DESCRIPTION;
//}

public class Pet {
	String name;
	String species;
	String description;
	JSONObject jsonObj = new JSONObject();

//	public Pet() {
//
//	}

	Pet(JSONObject input) {
		name = (String) input.get("name");
		species = (String) input.get("species");
		description = (String) input.get("description");
	}

	public Pet(String inputName) throws NullPetNameException {
		if (inputName.trim().isEmpty())
			throw new NullPetNameException();
		name = inputName.trim();
	}

	public void edit(String stringInput, PetDisplayType displayType) throws NullPetNameException {
		String string = stringInput.trim();
		switch (displayType) {
		case NAME:
			editName(string);
			break;

		case SPECIES:
			editSpecies(string);
			break;

		case DESCRIPTION:
			editDescription(string);
			break;

		default:
			// TODO throw an error message
			System.out.print("Invalid Input");
			break;
		}
	}

	private void editDescription(String text) {
		description = text;
	}

	// void editHandler(PetString pString, String text) {
//		if(pString.equals(PetString.NAME)) {
//			editName(text);
//		}
//		else if(pString.equals(PetString.SPECIES)) {
//			editSpecies(text);
//		}
//		else if(pString.equals(PetString.DESCRIPTION)) {
//			editDescription(text);
//		}
//		else {
//			throw new NullPointerException();
//		}
//	}
	protected void editHandler(String text1, String text2, String text3) {
		try {
			editName(text1);
		} catch (NullPetNameException e) {

		}
		editSpecies(text2);
		editDescription(text3);
	}

	private void editName(String text) throws NullPetNameException {
		if (text.trim().isEmpty())
			throw new NullPetNameException();
		name = text;
	}

	private void editSpecies(String text) {
		species = text;
	}

	public String get(PetDisplayType displayType) {
		switch (displayType) {
		case NAME:
			return name;

		case SPECIES:
			return species;

		case DESCRIPTION:
			return description;

		default:
			// TODO throw an error message
			System.out.print("Invalid Input");
			return null;
		}

	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getSpecies() {
		return species;
	}

	@SuppressWarnings("unchecked")
	JSONObject returnJsonObj() {
		jsonObj.put("name", name);
		jsonObj.put("description", description);
		jsonObj.put("species", species);
		return jsonObj;
	}
}
