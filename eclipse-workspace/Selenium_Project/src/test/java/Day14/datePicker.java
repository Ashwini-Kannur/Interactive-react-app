package Day14;

import java.sql.Time;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class datePicker {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://jqueryui.com/datepicker/");
		driver.manage().window().maximize();
		String expectYear="2025";
		String expectMonth="May";
		String expectDate="20";
		
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@id='datepicker']")).click();
		
		while (true) {
			//capturing actual month and year
			String actMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
			String actYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
			if(actMonth.equals(expectMonth) && actYear.equals(expectYear)) {
				break;
				
			}
			//to click the nex arrow
			driver.findElement(By.xpath("//span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
			//select date
			List<WebElement> actDates=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tbody//tr//td//a"));
			for(WebElement actDate:actDates) {
				if(actDate.getText().equals(expectDate))
				{
					actDate.click();
					break;
				}
			}
		}
	}

}
