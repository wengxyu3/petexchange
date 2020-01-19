package backEnd;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Account {
	String file = "C:\\";

	JSONObject jsonObj;
	JSONParser jsonParse;
	String username;
	String password;
	String description;
	String email;
	String adress;
	String city;
	String state;
	int zipCode;
	long phoneNumber;
	long creditNumber;
	ArrayList<Pet> pets = new ArrayList<>();
	ArrayList<Request> requests = new ArrayList<>();
	ArrayList<Review> ratings = new ArrayList<>();
	ArrayList<Transaction> transactions = new ArrayList<>();

	public Account(String username1, String password1, boolean isNewAccount) throws PasswordMismatchException {
		file = "C:\\" + username1;
		if (isNewAccount) {
			// Create new Account
			jsonObj = new JSONObject();
			username = username1;
			password = password1;
			this.save();
		} else {
			// Load Account
			jsonParse = new JSONParser();
			try (Reader reader = new FileReader(file)) {
				jsonObj = (JSONObject) jsonParse.parse(reader);
				username = (String) jsonObj.get("username");
				password = (String) jsonObj.get("password");
			} catch (IOException e) {

			} catch (ParseException e) {

			}
			if (!password.equals(password1))
				throw new PasswordMismatchException();
		}
	}

	Account(String username1, String password1, File file1) {

	}

	void addPet(Pet pet1) {
		pets.add(pet1);
	}

	void addRating(Review rating) {
		ratings.add(rating);
	}

	void addRequest(Pet pet1) {
		requests.add(new Request(pet1));
	}

	void addRequest(Pet[] pets) {
		requests.add(new Request(pets));
	}

	void deletePed(Pet pet1) {
		pets.remove(pet1);
	}

	void editDescription(String text) {
		description = text;
	}

	void editPassword(String text) {
		password = text;
	}

	// void editPet(int index, PetString pString, String text) {
//
//	}
	void editPet(int index, String text1, String text2, String text3) {
		pets.get(index).editHandler(text1, text2, text3);
	}

	void editUsername(String text) {
		username = text;
	}

	float getAverageRating() {
		int sum = 0;
		for (int i = 0; i < ratings.size(); i++)
			sum = sum + ratings.get(i).getRating();
		return sum / ratings.size();
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	void removePet(int index) {
		pets.remove(index);
	}

	void save() {
		jsonObj.put("username", username);
		jsonObj.put("password", password);
		jsonObj.put("description", description);
		jsonObj.put("email", email);
		jsonObj.put("adress", adress);
		jsonObj.put("city", city);
		jsonObj.put("state", state);
		jsonObj.put("zipCode", zipCode);
		jsonObj.put("phoneNumber", phoneNumber);
		jsonObj.put("creditNumber", creditNumber);
		JSONArray jsonPets = new JSONArray();
		for (int i = 0; i < pets.size(); i++)
			jsonPets.add(pets.get(i).returnJsonObj());
		jsonObj.put("pets", jsonPets);

		try (FileWriter fileOutput = new FileWriter(file)) {
			fileOutput.write(jsonObj.toJSONString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
