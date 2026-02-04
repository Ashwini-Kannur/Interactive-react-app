package Day16;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class tabWindow {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		WebElement register=driver.findElement(By.xpath("//a[normalize-space()='Register']"));
		Actions action= new Actions(driver);
		action.keyDown(Keys.CONTROL).click(register).keyUp(Keys.CONTROL).perform();
		List<String> IDS=new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(IDS.get(1));
		
		//fill the details of form
		
		driver.findElement(By.id("FirstName")).sendKeys("John");
		driver.findElement(By.id("LastName")).sendKeys("Dey");
		driver.findElement(By.id("Email")).sendKeys("Admin@admin.com");
		driver.findElement(By.id("Password")).sendKeys("admin123");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("admin123");
		driver.findElement(By.id("register-button")).click();
		
		//switch back to the home page
		driver.switchTo().window(IDS.get(0));
		driver.close();
	}

}
