package Day18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class headless {

	public static void main(String[] args) {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--headless=new");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://demo.nopcommerce.com/");
		
		//validate title
		String actualStr=driver.getTitle();
		if(actualStr.equals("nopCommerce demo store. Home page title"))
				System.out.println("passed");
		else 
				System.out.println("Not passed");
		driver.close();
		
	}

}
