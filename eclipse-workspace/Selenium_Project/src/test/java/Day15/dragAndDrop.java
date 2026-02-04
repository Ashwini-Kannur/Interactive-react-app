package Day15;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class dragAndDrop {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://artoftesting.com/samplesiteforselenium");
		driver.manage().window().maximize();
		Actions actions=new Actions(driver);
		WebElement source=driver.findElement(By.xpath("//img[@id='myImage']"));
		WebElement destination=driver.findElement(By.xpath("//div[@id='targetDiv']"));
		actions.dragAndDrop(source, destination).perform();
		Thread.sleep(4000);
		driver.close();
	}

}
