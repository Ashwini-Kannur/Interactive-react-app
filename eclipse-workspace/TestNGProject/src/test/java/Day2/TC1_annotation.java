package Day2;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC1_annotation {
	@Test
	void m1() {
		System.out.println("Something");
	}
	@BeforeTest
	void bt() {
		System.out.println("before");
	}

}
