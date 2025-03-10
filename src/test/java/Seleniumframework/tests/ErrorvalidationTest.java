package Seleniumframework.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import Seleniumframework.TestComponents.BaseTest;
import Seleniumframework.TestComponents.Retry;
import selenium.pageobjects.Productcatelogue;
import selenium.pageobjects.cartpage;
import selenium.pageobjects.checkoutpage;
import selenium.pageobjects.confirmationpage;
import selenium.pageobjects.loginpage;

public class ErrorvalidationTest extends BaseTest {
	@Test(groups= {"errorhandling"},retryAnalyzer=Retry.class)
	public void LoginErrorvalidation() throws IOException, InterruptedException {
	
	
	login.loginperform("soundaryapacharya@gmail.com" , "Test@1234"); //sending login details to loginpage pom file with wrong password
	Assert.assertEquals( "Incorrect email or password.", login.loginerrormessage());
	}
	
	@Test
	public void ProductValidation() throws InterruptedException {
		String ProductName="IPHONE 13 PRO";
		String CountryName="India";
		Productcatelogue prodtc=login.loginperform("soundaryapacharya@gmail.com" , "Test@123");
		
		List<WebElement> products=prodtc.getproductlist();
		prodtc.addproducttocart(ProductName);
		cartpage cartpage=prodtc.gotocartpage();
		Boolean match=cartpage.verifycartlist("IPHONE 13 PRO");
		Thread.sleep(2000);
		Assert.assertFalse(match);
		
	}
}
