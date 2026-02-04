package Day2;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class softAssert {
	@Test
	void test() {
		System.out.println("test01");
		System.out.println("test01");
		SoftAssert sa=new SoftAssert();
		sa.assertEquals(1, 2);
		System.out.println("test1");
		System.out.println("test1");
		sa.assertAll();
	}
}
