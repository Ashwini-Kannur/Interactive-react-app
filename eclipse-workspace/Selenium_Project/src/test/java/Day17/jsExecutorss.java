package Day17;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import Day11.selectDD;

public class jsExecutorss {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		WebElement inputBox=driver.findElement(By.xpath("//input[@id='name']"));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		//passing the value into the input box

		js.executeScript("arguments[0].setAttribute('value','john')",inputBox);
		WebElement radioButton= driver.findElement(By.xpath("//input[@id='male']"));
		js.executeScript("arguments[0].click()",radioButton);
		driver.close();
	}

}
