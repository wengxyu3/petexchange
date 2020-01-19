package src;
import backEnd.OwnerList;
import backEnd.fileExistsException;
public class testownerlist{
	public static void main(String[] args) {
		OwnerList e = new OwnerList("OwnerList");
		try {
			e.Register("Test 1", "password3321234");
			e.Register("John DOE", "password");
			e.Register("John Doe", "password2");
		} catch (fileExistsException e1) {
			// TODO should do nothing right now
		}
	}
}