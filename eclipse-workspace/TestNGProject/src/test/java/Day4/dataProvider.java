package Day4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class dataProvider {
	WebDriver driver;
	@BeforeClass
	void setUp() {
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	@Test(dataProvider = "dp")
	void login( String email,String pwd) {
		driver.get("https://tutorialsninja.com/demo/index.php?route=account/login");
		driver.manage().window().fullscreen();
		driver.findElement(By.xpath("//input[@id='input-email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='input-password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}
	@AfterClass
	void tearDown() {
		driver.close();
	}
	@DataProvider(name = "dp" , indices= {0,1})
	 public Object[][] Data(){
		Object data[][]={ 
		 {"abc@gmail.com","test123"},
		 {"def@gmail.com","test1234"}};
	return data;
	}

}
