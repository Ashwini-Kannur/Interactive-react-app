package Day8;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class windowHandle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//		driver.manage().window().maximize();
//		driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
//		Set<String> ids=driver.getWindowHandles();
		//Approach1
//		List<String> windowList=new ArrayList<String>(ids);
//		String pID=windowList.get(0);
//		String cID=windowList.get(1);
//		System.out.println(pID);
//		System.out.println(cID);
//		//switch to child window
//		driver.switchTo().window(cID);
//		System.out.println(driver.getTitle());
//		//switch to parent window
//		driver.switchTo().window(pID);
//		System.out.println(driver.getTitle());
		
		//Approach2 When we have many number od browser window looping approch can be used'
		
//		for(String ID:ids) {
//			String title=driver.switchTo().window(ID).getTitle();
//			if(title.equals("OrangeHRM")) {
//				System.out.println(driver.getCurrentUrl());
//			}
//		}
//		
//		driver.close();
//	
		//closing specific window
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.findElement(By.xpath("//a[normalize-space()='OrangeHRM, Inc']")).click();
		Set<String> ids=driver.getWindowHandles();
		for(String winID:ids) {
			String title= driver.switchTo().window(winID).getTitle();
			System.out.println(title);
			if(title.equals("OrangeHRM"))
			{
				driver.close();
			}
			
		}

	}

}
