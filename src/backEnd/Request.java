package backEnd;
public class Request {
	Pet[] pets;

	Request(Pet pet) {
		pets = new Pet[] { pet };
	}

	Request(Pet[] pets1) {
		pets = pets1;
	}
}
