package backEnd;

public class Review {
	int rating;
	String header = null;
	String description = null;

	Review(int rating1) {
		rating = rating1;
	}

	Review(int rating1, String header1) {
		rating = rating1;
		header = header1;
	}

	Review(int rating1, String header1, String desc1) {
		rating = rating1;
		header = header1;
		description = desc1;
	}

	int getRating() {
		return rating;
	}
}
