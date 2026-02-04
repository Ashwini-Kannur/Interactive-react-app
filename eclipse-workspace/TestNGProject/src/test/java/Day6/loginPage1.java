package Day6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class loginPage1 {
	WebDriver driver;
	public loginPage1(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(xpath = "//input[@name='username']") WebElement txtUsername;
	@FindBy(xpath = "//input[@name='password']") WebElement txtPassword;
	@FindBy(xpath = "//button[normalize-space()='Login']") WebElement loginButton;
	
	public void setusername(String usr) {
		txtUsername.sendKeys(usr);
		
	}
	public void setpassword(String pwd) {
		txtPassword.sendKeys(pwd);	}
	public void clickLogin() {
		loginButton.click();
	}
}
