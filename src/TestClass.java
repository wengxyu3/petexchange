package src;

import backEnd.Account;
import backEnd.PasswordMismatchException;

public class TestClass {

	public static void main(String[] args) {
		Account testAccount = null;
		try {
			testAccount = new Account("abc", "def", false);
			System.out.print(testAccount.getUsername());
			System.out.print(testAccount.getPassword());

			// testAccount.editDescription("hi");
			System.out.print(testAccount.getDescription());
		} catch (PasswordMismatchException e) {
			System.out.print("password does not match");
		}
//		Pet testPet = new Pet();
//		Pet deletePet = new Pet();
//		testAccount.addPet(testPet);
//		testAccount.addPet(deletePet);
//		testAccount.deletePet(deletePet);

	}
}
