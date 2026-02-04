package Day2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class assetion {
	@Test
	void test() {
		String exp_title="ABC";
		String act_title="DEF";
//		Assert.assertEquals(exp_title, act_title);
		if(exp_title.equals(act_title)) {
			System.out.println("passed");
			Assert.assertTrue(true);
		}else {
			System.out.println("Failed");
			Assert.assertTrue(false);
		}
	}
}
