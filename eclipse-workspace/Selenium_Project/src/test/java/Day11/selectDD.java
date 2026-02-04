package Day11;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class selectDD {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		WebElement dropDown=driver.findElement(By.xpath("//select[@id='country']"));
		Select drpdwn=new Select(dropDown);
		
		//select the option
		//Selectbyvisibletext
		//drpdwn.selectByVisibleText("Canada");
		
		//select by value
		//drpdwn.selectByValue("canada");
		
		//select by index
		
		//drpdwn.selectByIndex(5);
		
		//Capture all options
		List<WebElement> options=drpdwn.getOptions();
		System.out.println(options.size());
		
		//printing all options
		for(int i=0; i<options.size();i++) {
			System.out.println(options.get(i).getText());
		}
		
		driver.close();
		
		}

}
