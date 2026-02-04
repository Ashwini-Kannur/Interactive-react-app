package Day2;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class allAnnotation {
	@BeforeSuite
	void bs()
	{
		System.out.println("buite");
	}
	@AfterSuite
	void as() {
		System.out.println("asuite");
	}
	@BeforeTest
	void bt() {
		System.out.println("btest");
	}
	@AfterTest
	void at() {
		System.out.println("atest");
	}
	@BeforeClass
	void bc() {
		System.out.println("bclass");
	}
	@AfterClass
	void ac() {
		System.out.println("aclass");
	}
	@BeforeMethod
	void bm() {
		System.out.println("bmethod");
		
	}
	@AfterMethod
	void am() {
		System.out.println("amethod");
	}
	@Test
	void m1() {
		System.out.println("method1");
	}
	@Test
	void m2() {
		System.out.println("method2");
	}
}
