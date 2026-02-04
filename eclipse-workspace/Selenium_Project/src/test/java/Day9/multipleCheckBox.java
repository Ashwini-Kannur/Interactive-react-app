package Day9;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class multipleCheckBox {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.get("https://testautomationpractice.blogspot.com/");
		driver.manage().window().maximize();
		//extract multiple xpath
	    List<WebElement> xpaths=driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
	    for(int i=0; i<xpaths.size();i++) {
	    	xpaths.get(i).click();
	    	
	    }
	    //to check only few boxes
//	    for(int i=4; i<xpaths.size();i++) {
//	    	xpaths.get(i).click();
//	    }
	    //to check first 3 boxes
//	    Thread.sleep(4000);
//	    for(int i=0; i<3;i++) {
//	    	xpaths.get(i).click();
//	    }
//	    //uncheck the boxes
	    
	    for(WebElement xpath:xpaths) {
	    	xpath.click();
		}
	    
	    
	    driver.close();
	
	}

}
