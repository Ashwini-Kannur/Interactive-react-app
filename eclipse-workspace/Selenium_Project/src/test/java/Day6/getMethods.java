package Day6;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class getMethods {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().window().maximize();
		String string=driver.getTitle();
		System.out.println(string);
		System.out.println(driver.getCurrentUrl());
		//System.out.println(driver.getPageSource());
		String id=driver.getWindowHandle();
		System.out.println(id);
		
		Set<String> ids=driver.getWindowHandles();
		System.out.println(ids);
		driver.findElement(By.linkText("OrangeHRM, Inc")).click();
		Thread.sleep(3000);
		driver.close();
	}

}
