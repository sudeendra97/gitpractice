package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasstoreuse.waitcode;

public class orderpage extends waitcode{
	WebDriver driver;
	public orderpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderlist;
	
	@FindBy(xpath="(//h1[normalize-space()='Your Orders'])[1]")
	WebElement eletovisible;
//	
//	@FindBy(xpath="//button[normalize-space()='Checkout']")
//	WebElement checkout;
	
	public Boolean verifyorderpage(String ProductName) {
		waitforvisibilityoff(eletovisible);
		Boolean order=orderlist.stream().anyMatch(cartitems->cartitems.getText().equalsIgnoreCase(ProductName));
		return order;
	}

	

}
