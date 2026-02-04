package Day1;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class tcTestNG {
  WebDriver driver ;
  
  @Test(priority = 1)
  public void openURL() {
	  	WebDriverManager.chromedriver().setup();
	  	ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
       
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
  }
  @Test(priority = 2)
   void status() {
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	    WebElement logo = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//img[contains(@alt,'company')]")
	        )
	    );

	    System.out.println("Logo displayed: " + logo.isDisplayed());
	}
  
  @Test(priority = 3)
  void Login() 
	  {

	      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	      WebElement usrName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
	      usrName.clear();
	      usrName.sendKeys("Admin");

	      WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
	      pwd.clear();
	      pwd.sendKeys("admin123");

	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
	      
	  }

  @Test(priority = 4)
  void Logout() {
	  driver.close();
  }
	
}
