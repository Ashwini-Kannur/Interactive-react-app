package Day2;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TC3_annotation {
	@Test
	void m3() {
		System.out.println("something something");
	}
	@BeforeSuite
	void bs() {
		System.out.println("bs");
	}
	@AfterSuite
	void as() {
		System.out.println("as");
	}
}
