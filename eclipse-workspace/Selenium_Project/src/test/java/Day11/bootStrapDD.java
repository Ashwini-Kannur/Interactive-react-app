package Day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class bootStrapDD {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.htmlelements.com/demos/dropdownlist/multiple-selection/");
		
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//div[contains(@class,'smart-buttons-container')]")).click();
		driver.close();
	}

}
