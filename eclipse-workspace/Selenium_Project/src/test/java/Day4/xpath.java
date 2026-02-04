package Day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class xpath {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://grocerystoredemo.pcubeweb.com/");
		driver.manage().window().maximize();
		
		//xpath with single attribute
		//driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Pooja");
		 //Thread.sleep(3000);
		//xpath with multiple attributes using and /or operator
		//driver.findElement(By.xpath("//input[@name='search' or @fdprocessedid='thmeuk']")).sendKeys("Bread");
		
		//xpath with inner text
		driver.findElement(By.xpath("//h5[text()='Categories']")).click();
		 Thread.sleep(3000);
		//xpath with contains
		//driver.findElement(By.xpath("//input[contains(@name,'sear')]")).sendKeys("Bread");
		
		//xpath with starts-with
		driver.findElement(By.xpath("//input[starts-with(@name,'sear')]")).sendKeys("Bread");

		driver.close();
	}

}
