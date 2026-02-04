package Day7;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class explicitWait {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://demo.nopcommerce.com/login");
		WebElement userName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='email']")));
		userName.sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='password']"))).sendKeys("admin123");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'login-button')]"))).click();
		driver.close();
		}

}
