package Day18;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class captureSpecific {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		WebElement feature=driver.findElement(By.xpath("//section[@class='product-grid home-page-product-grid']"));
		File source=feature.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"//Screenshots//feature.png");
		source.renameTo(target);
		driver.close();
	}

}
