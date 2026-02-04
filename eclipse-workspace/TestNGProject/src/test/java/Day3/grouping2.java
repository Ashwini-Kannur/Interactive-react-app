package Day3;

import org.testng.annotations.Test;

public class grouping2 {
		@Test(priority = 1, groups = {"regression"})
		void signupInsta() {
			System.out.println("signup to insta");
		}
		@Test(priority = 2, groups = {"regression"})
		void signupFB() {
			System.out.println("signup to fb");
		}
}
