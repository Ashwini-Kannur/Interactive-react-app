package Day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class First_TC {

	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		String title = driver.getTitle();
		Thread.sleep(4000);
		if (title.equals("Google")) {
			System.out.println("Test Passed");
		} else {
			System.out.println("Test Failed");
		}

		driver.close();
	}
}
