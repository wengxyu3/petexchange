import backEnd.Account;
import backEnd.PasswordMismatchException;

public class TestClass {
	public static void main(String[] args) {
		Account testAccount;
		try {
			testAccount = new Account("abc", "def", true);
			System.out.print(testAccount.getUsername());
			System.out.print(testAccount.getPassword());
		} catch (PasswordMismatchException e) {
			System.out.print("password does not match");
		}

	}
}
