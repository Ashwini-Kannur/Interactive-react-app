package Day1;
import org.testng.annotations.Test;

public class firstTCTestNG {
	@Test(priority = 1)
	void openAPP() {
		System.out.println("opening application");
	}
	@Test(priority = 2)
	void login() {
		System.out.println("Login Page");
	}
	@Test(priority = 3)
	void logout() {
		System.out.println("logout");
	}
	
}
