package Day15;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class mouseEvents {

	public static void main(String[] args) {
		
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		driver.get("http://localhost/OpenCartStore/");
		wait.until(ExpectedConditions.titleContains("Your Store"));
		WebElement desktops = wait.until(
			    ExpectedConditions.visibilityOfElementLocated(
			        By.xpath("//a[contains(@class,'dropdown-toggle') and text()='Desktops']")
			    )
			);
		Actions actions = new Actions(driver);
		actions.moveToElement(desktops).perform();
		WebElement mac = wait.until(
			    ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Mac (1)']"))
			);

			mac.click();
		driver.close();
		
		}

}
