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

	public Account() {
		// TODO Auto-generated constructor stub
	}

	Account(String password1, File file1) throws PasswordMismatchException {
		file = file1.getPath();
		loginHandler(password1);
	}

	public Account(String username1, String password1, boolean isNewAccount) throws PasswordMismatchException {
		switch (OSUtility.getOS()) {
		case WINDOWS:
			file = "C:\\" + username1;
			break;
		case MAC: // TODO see what happens with MacOS file stuff
			String home1 = System.getProperty("user.home");
			file = home1 + "/Documents/Java" + username1;
			break;
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
		} else
			// Load Account
			loginHandler(password1);

	}

	public void addPet(Pet pet1) {
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

	public void deletePet(Pet pet1) {
		pets.remove(pet1);
		save();
	}

	public void edit(String inputString, UserDisplayType displayType) {
		String string;
		try {
			string = inputString.trim();
		} catch (NullPointerException e) {
			string = "";
		}
		switch (displayType) {
		case USERNAME:
			editUsername(string);
			break;

		case PASSWORD:
			editPassword(string);
			break;

		case DESCRIPTION:
			editDescription(string);
			break;

		case EMAIL:
			editEmail(string);
			break;

		case ADRESS:
			editAdress(string);
			break;

		case CITY:
			editCity(string);
			break;

		case STATE:
			editState(string);
			break;

		case ZIPCODE:
			editZipCode(Integer.valueOf(string));
			break;

		case PHONENUMBER:
			editPhoneNumber(Long.valueOf(string));
			break;

		case CREDITNUMBER:
			editCreditNumber(Long.valueOf(string));
			break;

		default:
			// TODO throw an error message
			System.out.print("Invalid Input");
			break;
		}

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

	public void editPet(Pet inputPet, String inputString, PetDisplayType displayType) throws NullPetNameException {
		if (pets.indexOf(inputPet) != -1) {
			pets.get(pets.indexOf(inputPet)).edit(inputString, displayType);
			save();
		}
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

	public String get(UserDisplayType displayType) {

		switch (displayType) {
		case USERNAME:
			return username;

		case PASSWORD:
			return password;

		case DESCRIPTION:
			return description;

		case EMAIL:
			return email;

		case ADRESS:
			return adress;

		case CITY:
			return city;

		case STATE:
			return state;

		case ZIPCODE:
			return String.valueOf(zipCode);

		case PHONENUMBER:
			return String.valueOf(phoneNumber);

		case CREDITNUMBER:
			return String.valueOf(creditNumber);

		default:
			// TODO throw an error message
			System.out.print("Invalid Input");
			return null;
		}

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

	public ArrayList<Pet> getPets() {
		return pets;
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

	@SuppressWarnings("unchecked")
	private void loginHandler() {
		jsonParse = new JSONParser();
		try (Reader reader = new FileReader(file)) {
			username = (String) jsonObj.get("username");
			password = (String) jsonObj.get("password");
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

	private void loginHandler(String inputPassword) throws PasswordMismatchException {
		jsonParse = new JSONParser();
		try (Reader reader = new FileReader(file)) {
			jsonObj = (JSONObject) jsonParse.parse(reader);
			username = (String) jsonObj.get("username");
			password = (String) jsonObj.get("password");
			if (!inputPassword.equals(password))
				throw new PasswordMismatchException();
			loginHandler();
		} catch (IOException e) {

		} catch (ParseException e) {

		}
	}

	void removePet(int index) {
		pets.remove(index);
	}

	void removePet(String name) {
		for (int i = 0; i < pets.size(); i++)
			if (pets.get(i).getName().equals(name))
				pets.remove(i);
	}

	@SuppressWarnings("unchecked")
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
