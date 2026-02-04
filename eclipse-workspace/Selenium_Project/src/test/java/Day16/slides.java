package Day16;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class slides {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.jqueryscript.net/demo/Price-Range-Slider-jQuery-UI/");
		driver.manage().window().maximize();
		
		//min slider
		WebElement min=driver.findElement(By.xpath("//div[@class='price-range-block']//span[1]"));
		System.out.println(min.getLocation());
		Actions action=new Actions(driver);
		Thread.sleep(3000);
		action.dragAndDropBy(min, 100, 240).perform();
		System.out.println(min.getLocation());
		
		//max slider
		WebElement max=driver.findElement(By.xpath("//div[@class='price-range-block']//span[2]"));
		System.out.println(max.getLocation());
		action.dragAndDropBy(max, -176, 249).perform();
		System.out.println(max.getLocation());
		
		driver.close();
	}

}
