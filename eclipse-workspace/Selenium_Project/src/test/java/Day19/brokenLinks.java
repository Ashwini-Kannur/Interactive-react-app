package Day19;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class brokenLinks {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		int noOfBrokenLink=0;
		List<WebElement> links=driver.findElements(By.tagName("a"));
		System.out.println(links.size());
		
		//condition-1 link should have href
		
		for(WebElement link:links) {
			String hrefAttribute= link.getAttribute("href");
			if(hrefAttribute==null||hrefAttribute.isEmpty()) {
			System.out.println("not possible to check the status code");
			continue;
			}
			try {
				URL urlLink= new URL(hrefAttribute);
				HttpURLConnection connectionLink= (HttpURLConnection) urlLink.openConnection();
				connectionLink.connect();
				if(connectionLink.getResponseCode()>400) {
					System.out.println("its Broken Link");
					System.out.println(hrefAttribute);
					noOfBrokenLink++;
					
				}
				else {
					System.out.println("not broken");
				}
				
			}
			catch (Exception e) {
				
			}
		}
		System.out.println(noOfBrokenLink);
		driver.close();
	}

}
