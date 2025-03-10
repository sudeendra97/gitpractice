package Seleniumframework.stepdefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Seleniumframework.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.testng.CucumberOptions;
import selenium.pageobjects.Productcatelogue;
import selenium.pageobjects.cartpage;
import selenium.pageobjects.checkoutpage;
import selenium.pageobjects.confirmationpage;
import selenium.pageobjects.loginpage;


public class Stepdefinition extends BaseTest{
	String CountryName="India";
	public loginpage login;
	public Productcatelogue prodtc;
	public confirmationpage message;
	@Given("I landed on Ecommerce page")   //text inside the () brases should match the text given in feature file 
	public void I_landed_on_ecommerce_page() throws IOException {
		login = launchapplication();  //directly calling landing page method to lauch the browser 
	}
	
	@Given("logged in with username {string} and password {string}")
	//data drived during run time is written with regular expression and enclosed with ^$
	public void loggin_in_with_username_and_password(String email,String password) throws IOException {
		prodtc=login.loginperform(email,password);
//		System.out.println(email);
//		System.out.println(password);
//		 WebElement emailField = driver.findElement(By.id("userEmail")); // Replace with actual locator
//	        WebElement passwordField = driver.findElement(By.id("userPassword")); // Replace with actual locator
//	        WebElement loginButton = driver.findElement(By.id("login")); // Replace with actual locator
//	        
//	        emailField.sendKeys(email);
//	        passwordField.sendKeys(password);
//	        loginButton.click();
	}
	
	@When("I add product {string} to the cart")
	public void i_add_product_to_the_cart(String productname) {
		List<WebElement> productlist=prodtc.getproductlist();
		prodtc.addproducttocart(productname);
	}
	
	@When("check out {string} and submit the order")
	public void check_out_and_submit_the_order(String productname) throws InterruptedException {
		cartpage cart=prodtc.gotocartpage();
		Boolean match=cart.verifycartlist(productname);
//		Assert.assertTrue(match);
//			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".heading h1"))));
//			
			Thread.sleep(2000);
			cart.checkout(); 
			checkoutpage checkout=new checkoutpage(driver);
			checkout.selectcountry(CountryName);		
			message=checkout.submit();
			Thread.sleep(2000);
	}
	
	@Then("{string} message should get displyed")
	//to read data from the feature file sentence it self just pass  the type of the data in {} 
	public void Message_should_be_displayed(String string) {
		String confirmMessage=message.confirmation();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
}
