package Day17;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class uploadFile {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://davidwalsh.name/demo/multiple-file-upload.php");
		driver.manage().window().maximize();
		String file1="C://Users/Ashwini/OneDrive/Documents/PPS syllabus[1].docx";
		String file2="C://Users/Ashwini/OneDrive/Documents/Unit-3.docx";
		
		Thread.sleep(4000);
		driver.findElement(By.id("filesToUpload")).sendKeys(file1 + "\n" + file2);
		if(driver.findElement(By.xpath("//ul[@id='fileList']//li[1]")).getText().equals("PPS syllabus[1].docx")  && driver.findElement(By.xpath("//ul[@id='fileList']//li[2]")).getText().equals("Unit-3.docx"))
			System.out.println("File uploaded");
		else
			System.out.println("File not Uploaded");
		
		//int filesize=((Object)driver.findElement(By.xpath("//ul[@id='fileList']//li"))).size();
			
		
		
		driver.close();
		
		

		
	}

}
