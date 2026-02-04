package Day18;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class enablingExtension {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options=new ChromeOptions();
		options.addExtensions(new File("C://Users/Ashwini/Downloads/SelectorsHub-Chrome-Web-Store.crx"));

		WebDriver driver=new ChromeDriver(options);
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().window().maximize();
		
		Thread.sleep(5000);
		
//		File file1=new File("https://chromewebstore.google.com/detail/ublock-origin-lite/ddkjiahejlhfcafbddmgiahcphecmpfh?hl=en");
//		options.addExtensions(file1);
//		driver.get("https://text-compare.com/");
		
		driver.close();
		
	}

}
