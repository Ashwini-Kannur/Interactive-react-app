package Day18;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testIncognitoMode {

	public static void main(String[] args) {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--Incognito");
		WebDriver driver=new ChromeDriver(options);
		driver.get("https://demo.nopcommerce.com/");
		String act_title=driver.getTitle();
		if(act_title.equals("nopCommerce demo store. Home page title"))
			System.out.println("Passed");
		else
			System.out.println("Not Passed");
	}

}
