package Day14;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class datePicker1 {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(40));
		driver.get("https://demoqa.com/date-picker");
		driver.manage().window().maximize();
		
		String reqYear="2021";
		String reqMonth="June";
		String reqDate="16";
		
		driver.switchTo().frame("google_ads_iframe_/21849154601,22343295815/Ad.Plus-970x250-1_0");
		Thread.sleep(3000);
		WebElement datePicker = driver.findElement(By.xpath("//input[contains(@id,'datePickerMonthYearInput')]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", datePicker);
		//select year
		
		WebElement yearDrpdown= driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
		Select selectYear=new Select(yearDrpdown);
		selectYear.selectByVisibleText(reqYear);
		
		WebElement monthDrpdown=driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
		Select selectMonth=new Select(monthDrpdown);
		selectMonth.selectByContainsVisibleText(reqMonth);
		
		//select date
		
		List<WebElement> allDates= driver.findElements(By.xpath("//div[@class='react-datepicker__week']//div"));
		for(WebElement dates:allDates) {
			if(dates.getText().equals(reqDate)) {
				dates.click();
				
				break;
			}
		}
	}

}
