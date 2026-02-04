package Day12;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class staticWebTable {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://testautomationpractice.blogspot.com/");
		
		driver.manage().window().maximize();
			
		//find total number of rows
		WebElement rows=driver.findElement(By.xpath("//table[@name='BookTable']//tr"));
		WebElement cols=driver.findElement(By.xpath("//table[@name='BookTable']//th"));

		
		//read the specific data
//		String bookName=driver.findElement(By.xpath("//table[@name='BookTable']//tr[5]//td[2]")).getText();
//		System.out.println(bookName);
		
		//read the data from all the rows and columns
		System.out.println("BookName"+"\t"+"Author"+"\t");
		
		driver.close();
		
	}

}
