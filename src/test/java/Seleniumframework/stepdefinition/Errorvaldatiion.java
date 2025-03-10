package Seleniumframework.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import Seleniumframework.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import selenium.pageobjects.Productcatelogue;
import selenium.pageobjects.confirmationpage;
import selenium.pageobjects.loginpage;

public class Errorvaldatiion extends BaseTest {
	public loginpage login;
	public Productcatelogue prodtc;
	public confirmationpage message;
	@Given("I landed on the Ecommerce page")   //text inside the () brases should match the text given in feature file 
	public void I_landed_on_ecommerce_page() throws IOException {
		login = launchapplication();  //directly calling landing page method to lauch the browser 
	}
	
	@Given("logged in with the username {string} and password {string}")
	//data drived during run time is written with regular expression and enclosed with ^$
	public void loggin_in_with_username_and_password(String email,String password) throws IOException {
		prodtc=login.loginperform(email,password);
	}
	@Then("{string} message is displayed")
	public void Error_message_is_displayed(String message) {
		Assert.assertEquals( "Incorrect email or password.", login.loginerrormessage());
		driver.close();
	}
}
