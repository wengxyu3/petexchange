package backEnd;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Account {

	private String file = "";

	private JSONObject jsonObj;
	private JSONParser jsonParse;
	private String username;
	private String password;
	private String description;
	private String email;
	private String adress;
	private String city;
	private String state;
	private int zipCode;
	private long phoneNumber;
	private long creditNumber;
	private ArrayList<Pet> pets = new ArrayList<>();
	private ArrayList<Request> requests = new ArrayList<>();
	private ArrayList<Review> ratings = new ArrayList<>();

	public Account(String username1, String password1, boolean isNewAccount) throws PasswordMismatchException {
		switch (OSUtility.getOS()) {
		case WINDOWS:
			file = "C:\\" + username1;
		case MAC: // TODO see what happens with MacOS file stuff
			String home1 = System.getProperty("user.home");
			file = home1;
		default:
			// TODO add default handler
			break;
		}
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

			try (Reader reader = new FileReader(file)) {
				jsonObj = (JSONObject) jsonParse.parse(reader);
				description = (String) jsonObj.get("description");
				email = (String) jsonObj.get("email");
				adress = (String) jsonObj.get("adress");
				city = (String) jsonObj.get("city");
				state = (String) jsonObj.get("state");
				zipCode = Math.toIntExact((Long) jsonObj.get("zipCode"));
				phoneNumber = (Long) jsonObj.get("phoneNumber");
				creditNumber = (Long) jsonObj.get("creditNumber");

				JSONArray petArray = (JSONArray) jsonObj.get("pets");
				Iterator<JSONObject> petIterator = petArray.iterator();
				while (petIterator.hasNext())
					pets.add(new Pet(petIterator.next()));
			} catch (IOException e) {

			} catch (ParseException e) {

			}
		}

	}

	Account(String username1, String password1, File file1) {

	}

	void addPet() {
		pets.add(new Pet());
		save();
	}

	void addPet(Pet pet1) {
		pets.add(pet1);
		save();
	}

	void addRating(Review rating) {
		ratings.add(rating);
		save();
	}

	void addRequest(Pet pet1) {
		requests.add(new Request(pet1));
		save();
	}

	void addRequest(Pet[] pets) {
		requests.add(new Request(pets));
		save();
	}

	void deletePet(Pet pet1) {
		pets.remove(pet1);
		save();
	}

	void editAdress(String text) {
		adress = text;
		save();
	}

	void editCity(String text) {
		city = text;
		save();
	}

	void editCreditNumber(Long input) {
		creditNumber = input;
		save();
	}

	void editDescription(String text) {
		description = text;
		save();
	}

	void editEmail(String text) {
		email = text;
		save();
	}

	void editPassword(String text) {
		password = text;
		save();
	}

	// void editPet(int index, PetString pString, String text) {
//
//	}
	void editPet(String name, String text2, String text3) {
		for (int i = 0; i < pets.size(); i++)
			if (name.equals(pets.get(i).getName())) {
				editPetIndex(i, name, text2, text3);
				break;
			}
	}

	void editPetIndex(int index, String text1, String text2, String text3) {
		pets.get(index).editHandler(text1, text2, text3);
		save();
	}

	void editPhoneNumber(Long input) {
		phoneNumber = input;
		save();
	}

	void editState(String text) {
		state = text;
		save();
	}

	void editUsername(String text) {
		username = text;
		save();
	}

	void editZipCode(int input) {
		zipCode = input;
		save();
	}

	public String getAdress() {
		return adress;
	}

	float getAverageRating() {
		int sum = 0;
		for (int i = 0; i < ratings.size(); i++)
			sum = sum + ratings.get(i).getRating();
		return sum / ratings.size();
	}

	public String getCity() {
		return city;
	}

	public long getCreditNumber() {
		return creditNumber;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public String getState() {
		return state;
	}

	public String getUsername() {
		return username;
	}

	public int getZipCode() {
		return zipCode;
	}

	void removePet(int index) {
		pets.remove(index);
	}

	void removePet(String name) {
		for (int i = 0; i < pets.size(); i++)
			if (pets.get(i).getName().equals(name))
				pets.remove(i);
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
