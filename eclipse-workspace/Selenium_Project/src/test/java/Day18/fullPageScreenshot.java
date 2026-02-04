package Day18;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class fullPageScreenshot {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver=new ChromeDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		TakesScreenshot ts=(TakesScreenshot) driver;
	
		
		String projectPath = System.getProperty("user.dir");

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(projectPath + "\\Screenshots\\fullpage.png");

		FileUtils.copyFile(src, dest);
		
		driver.close();
	}

}
