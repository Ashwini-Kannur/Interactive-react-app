package Day3;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class selector {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
//		tag-id
//		driver.findElement(By.cssSelector("input#small-searchterms")).sendKeys("TV");
//		driver.findElement(By.cssSelector("#small-searchterms")).sendKeys("TV");
		
		
		//tag-class
		//driver.findElement(By.cssSelector("input.search-box-text")).sendKeys("Tv");
		//driver.findElement(By.cssSelector(".search-box-text")).sendKeys("Tv");

		//tag-attribute
//		driver.findElement(By.cssSelector("input[name='q']")).sendKeys("laptop");
//		driver.findElement(By.cssSelector("[name='q']")).sendKeys("laptop");

		
		//tag-class attribute
		driver.findElement(By.cssSelector("input.search-box-text[name='q']")).sendKeys("laptop");
		
		driver.close();
		}

}
