package Day3;

import org.testng.annotations.Test;

public class grouping3 {
	@Test(priority = 1, groups = {"sanity,regression","functional"})
	void pay1() {
		System.out.println("pay1");
	}
	@Test(priority = 2, groups = {"sanity,regression","functional"})
	void pay2() {
		System.out.println("pay2");
	}
}
