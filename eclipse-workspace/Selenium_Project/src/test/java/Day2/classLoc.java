package Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class classLoc {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.get("https://grocerystoredemo.pcubeweb.com/");
		driver.manage().window().maximize();
		List<WebElement> links=driver.findElements(By.className("categoryImg"));
		System.out.println(links.size());
		driver.quit();
	}

}
