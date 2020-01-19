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

	Pet() {

	}

	Pet(JSONObject input) {
		name = (String) input.get("name");
		species = (String) input.get("species");
		description = (String) input.get("description");
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
	void editHandler(String text1, String text2, String text3) {
		editName(text1);
		editSpecies(text2);
		editDescription(text3);
	}

	private void editName(String text) {
		name = text;
	}

	private void editSpecies(String text) {
		species = text;
	}

	JSONObject returnJsonObj() {
		jsonObj.put("name", name);
		jsonObj.put("description", description);
		jsonObj.put("species", species);
		return jsonObj;
	}
}
