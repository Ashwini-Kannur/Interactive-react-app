package Day17;

import java.awt.Window;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.asm.Advice.AllArguments;

public class pageScrolling {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.countries-ofthe-world.com/flags-of-the-world.html");
		driver.manage().window().maximize();
		JavascriptExecutor js=(JavascriptExecutor) driver;
		//page scrolling
//		js.executeScript("window.scrollTo(0,3000)");
//		
//		//capture the pixel
//		System.out.println(js.executeScript("return window.pageYOffset"));
		
		//scroll by page till the page is visible
//	    WebElement link= driver.findElement(By.xpath("//a[normalize-space()='Top 100 largest countries by area']"));
//	   Thread.sleep(5000);
//	    js.executeScript("arguments[0].scrollIntoView()",link);
//	    System.out.println(js.executeScript("return window.pageYOffset"));
		
		//scroll by page till the page is visible
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		System.out.println(js.executeScript("return window.pageYOffset"));
		
		//to reverse the page
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		driver.close();
		
		
		
		}

}
