package Day2;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class TC2_annotation {
	@Test
	void m2() {
		System.out.println("Somethinggg");
	}
	@AfterTest
	void at() {
		System.out.println("after");
	}
}
