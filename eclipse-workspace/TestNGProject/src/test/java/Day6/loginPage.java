package Day6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage {
	WebDriver driver;
	//constructor
	loginPage(WebDriver driver){
		this.driver=driver;
	}
	//locators
	By userName=By.cssSelector("[placeholder='Username']");
	By password=By.cssSelector("[placeholder='Password']");
	By button= By.xpath("//button[normalize-space()='Login']");
	
	//action Methods
	public void setusername(String usr) {
		driver.findElement(userName).sendKeys(usr);
		
	}
	public void setpassword(String pwd) {
		driver.findElement(password).sendKeys(pwd);
	}
	public void clickLogin() {
		driver.findElement(button).click();
	}
}
