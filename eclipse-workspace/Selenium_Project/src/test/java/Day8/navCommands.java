package Day8;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class navCommands {

	public static void main(String[] args) throws MalformedURLException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://demo.nopcommerce.com");
		driver.navigate().to("https://opensource-demo.orangehrmlive.com/");
		//URL can also stored in variable of the object URL 
		
		//URL url=new URL("https://demo.nopcommerce.com");
		//driver.navigate().to(url);
		//driver.findElement(By.xpath("//a[normalize-space()='Jewelry']")).click();
		
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		driver.navigate().forward();
		System.out.println(driver.getCurrentUrl());
		driver.close();
		
		
		
		
	}

}
