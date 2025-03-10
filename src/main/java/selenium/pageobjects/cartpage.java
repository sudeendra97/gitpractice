package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasstoreuse.waitcode;

public class cartpage extends waitcode{
	WebDriver driver;
	public cartpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	@FindBy(css=".cartSection h3")
	List<WebElement> cartlist;
	
	@FindBy(css=".heading h1")
	WebElement eletovisible;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkout;
	
	public Boolean verifycartlist(String ProductName) {
		waitforvisibilityoff(eletovisible);
		Boolean match=cartlist.stream().anyMatch(cartitems->cartitems.getText().equalsIgnoreCase(ProductName));
//		System.out.println(cartlist.stream().anyMatch(cartitems->cartitems.getText().equals(ProductName)));
		return match;
	}
	
	public checkoutpage checkout() {
		checkout.click();
		return new checkoutpage(driver);
		
	}
	

}
