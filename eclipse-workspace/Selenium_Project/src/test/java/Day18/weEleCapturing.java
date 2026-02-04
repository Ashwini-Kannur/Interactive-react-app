package Day18;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class weEleCapturing {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		WebElement logo=driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
		File src=logo.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"//Screenshots//webele.png");
		src.renameTo(target);
		driver.close();
	}

}
