package Day3;

import org.testng.annotations.Test;

public class grouping1 {
	@Test(priority = 1, groups = {"sanity"})
	void loginInsta() {
		System.out.println("login to insta");
	}
	@Test(priority = 2,groups = {"sanity"})
	void loginFB() {
		System.out.println("login to fb");
	}
	
}

