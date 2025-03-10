package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasstoreuse.waitcode;

public class loginpage extends waitcode{
	//first lets create constructor to get driver data 
	
	WebDriver driver;
	public loginpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//initializing pagefactory
		PageFactory.initElements(driver,this); //this refers to the instace of the variable
	}
//	WebElement username=driver.findElement(By.id("userEmail"));
//	WebElement password=driver.findElement(By.id("userPassword"));
//	WebElement submit=driver.findElement(By.id("login")); 
	
	//writing above webelements using pageFactory
	@FindBy(id="userEmail")
	WebElement username;	
	@FindBy(id="userPassword")
	WebElement passwordele;
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='toast-message']")
	WebElement loginerror;
	
	//action method on loginpage
	public Productcatelogue loginperform(String email,String password) {
		System.out.println("printing login username and password"+ email +" and "+password);
		username.sendKeys(email); //since we acnot send any input data in pom lets get it from test class
		passwordele.sendKeys(password);
		submit.click();
		//after login we'll go to prodct page soo reduce code in test class we can call next action method here itself
		Productcatelogue prodtc=new Productcatelogue(driver);
		return prodtc;
	}
	public String loginerrormessage() {
		waitforWebelementtoappear(loginerror);
		return loginerror.getText();
	
	}
	
	
	public void  goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
