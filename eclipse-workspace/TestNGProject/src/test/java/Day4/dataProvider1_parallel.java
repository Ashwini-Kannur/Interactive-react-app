package Day4;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class dataProvider1_parallel {
		WebDriver driver;
		String browserName;
		@BeforeClass
		@Parameters({"browser"})
		void setup(String browser) {
			this.browserName=browser;
			switch(browser.toLowerCase()){	
				
			case "chrome": driver=new ChromeDriver();
			break;
			case "firefox": driver=new FirefoxDriver();
			break;
			default:System.out.println("Invalid");
			return;
			}			
				driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			}
			
	

		@Test(priority = 1)
		void logo() {
			boolean status=driver.findElement(By.xpath("//img[@alt='company-branding']")).isDisplayed();
			Assert.assertEquals(status, true);
		}
		@Test(priority = 2)
		void title() {
			//WebElement element=driver.getCurrentUrl();
			Assert.assertEquals(driver.getTitle(),"OrangeHRM");
		}
		@Test(priority = 3)
		void testURL() {
			//WebElement element=driver.getCurrentUrl();
			String expectString="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
			
			Assert.assertEquals(driver.getCurrentUrl(),expectString);
		}
		@AfterClass
		void tearDown() {
			driver.close();
		}
}
