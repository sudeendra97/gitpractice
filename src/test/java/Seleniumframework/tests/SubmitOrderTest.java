package Seleniumframework.tests;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Seleniumframework.TestComponents.BaseTest;
import selenium.pageobjects.Productcatelogue;
import selenium.pageobjects.cartpage;
import selenium.pageobjects.checkoutpage;
import selenium.pageobjects.confirmationpage;
import selenium.pageobjects.loginpage;
import selenium.pageobjects.orderpage;

public class SubmitOrderTest extends BaseTest {
	String ProductName="IPHONE 13 PRO";
	@Test(dataProvider= "getData",groups= {"purchase"})
//	public void submitOrder(String email,String password,String ProductName ) throws IOException, InterruptedException { when data provider is object
	public void submitOrder(HashMap<String,String> input ) throws IOException, InterruptedException {
	
	
	String CountryName="India";
	Productcatelogue prodtc=login.loginperform(input.get("email"),input.get("password")); //sending login details to loginpage pom file
	//list of products present in the page
	List<WebElement> productlist=prodtc.getproductlist();
	prodtc.addproducttocart(input.get("product"));
	cartpage cart=prodtc.gotocartpage();
	Boolean match=cart.verifycartlist(input.get("product"));
//	Assert.assertTrue(match);
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".heading h1"))));
//		
		Thread.sleep(2000);
		cart.checkout(); 
		checkoutpage checkout=new checkoutpage(driver);
		checkout.selectcountry(CountryName);		
		confirmationpage message=checkout.submit();
		Thread.sleep(2000);
		
		String confirmMessage=message.confirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		System.out.println("AllDone");
	}
	
	//check for order present in orders page
	@Test(dependsOnMethods= {"submitOrder"}, groups= {"purchase"})
	public void OrderHistoryTest() throws InterruptedException {
		Productcatelogue prodtc=login.loginperform("soundaryapacharya@gmail.com" , "Test@123");
		Thread.sleep(2000);
		orderpage orderpage=prodtc.gotoorderpage();
		Assert.assertTrue(orderpage.verifyorderpage(ProductName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email","soundaryapacharya@gmail.com");
//		map.put("password","Test@123");
//		map.put("product","IPHONE 13 PRO");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email","soundaryapacharya@gmail.com");
//		map1.put("password","Test@123");
//		map1.put("product","qwerty");
		
		List<HashMap<String,String>> data=getJsonDatatMap(System.getProperty("user.dir")+"\\src\\test\\java\\Seleniumframework\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData() {
//		return new  Object[][]  {{"soundaryapacharya@gmail.com","Test@123","IPHONE 13 PRO"},{"soundaryapacharya@gmail.com","Test@123","qwerty"}};
//	}
}
