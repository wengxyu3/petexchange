package src;
import backEnd.OwnerList;
public class testownerlist{
	public static void main(String[] args) {
		OwnerList e = new OwnerList("OwnerList");
		e.Register("John DOE", "password");
		e.Register("John Doe", "password2");
		e.Register("Test 1", "password3321234");
	}
}