package Day2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class hardAssert {
	@Test
	void test() {
		System.out.println("test01");
		System.out.println("test01");
		Assert.assertEquals(1, 1);
		System.out.println("test1");
		System.out.println("test1");
	}
}
