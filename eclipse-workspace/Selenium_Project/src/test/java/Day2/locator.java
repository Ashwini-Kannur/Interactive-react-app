package Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class locator {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://grocerystoredemo.pcubeweb.com/");
		driver.manage().window().maximize();

		Thread.sleep(4000);
		// Name locator
		driver.findElement(By.name("search")).sendKeys("Biscuits");

		// partial linktext
		driver.findElement(By.partialLinkText("Acco")).click();

		// linktext locator
		// driver.findElement(By.linkText("Account")).click();

		System.out.println("Found");
		driver.close();
	}

}
