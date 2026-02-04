package TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.homePage;
import PageObjects.regPage;

public class TC001_Registration  extends baseClass{

	
	@Test
	public void verify_acct_registation() throws InterruptedException {
		homePage hp=new homePage(driver);
		hp.myAccount();
		hp.register();
		
		regPage rp=new regPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastname(randomString().toUpperCase());
		rp.setEmail(randomString()+"@gmail.com");
		rp.setTelephone(randomNumber());
		String pwwdString=randomAlphanumeric();
		rp.setPassword(pwwdString);
		rp.setConfirmPassword(pwwdString);
		rp.checkPolicy();
		rp.continueBtn();
		String cnfMsg=rp.getConfirmMessage();
		Assert.assertEquals(cnfMsg, "Your Account Has Been Created!");
		
	}
	
	
	
}
