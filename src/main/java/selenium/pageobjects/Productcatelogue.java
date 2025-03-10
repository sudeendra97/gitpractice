package selenium.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import abstractclasstoreuse.waitcode;

public class Productcatelogue extends waitcode {
	WebDriver driver;
	public Productcatelogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		//initializing pagefactory
		PageFactory.initElements(driver,this); //this refers to the instace of the variable
	}
	@FindBy(id="toast-container")
	WebElement eletodisappear1;
	
	@FindBy(css=".heading h1")
	WebElement tobevisible;
//	List<WebElement> prolist=driver.findElements(By.cssSelector(".mb-3"));
	By element=By.cssSelector(".mb-3");
	By addtocart=By.cssSelector(".mb-3 button:last-of-type");
	By toatsmessage=By.id("toast-container");
	//writing above webelements using pageFactory
	@FindBy(css=".mb-3")
	List<WebElement> prolist;	 //since we are getting list of products enclose element in List<>
	
	public  List<WebElement> getproductlist() {
		waitforelementtoappear(element);
		return prolist;
	}
	
	public WebElement getproductlistbyname(String ProductName) {
		WebElement finalproduct=prolist.stream().filter(product->
		product.findElement(By.tagName("b")).getText().equalsIgnoreCase(ProductName)).findFirst().orElse(null);
		return finalproduct;
	}
	
	public void addproducttocart(String ProductName) {
		WebElement finalproduct=getproductlistbyname(ProductName);
		finalproduct.findElement(addtocart).click();
		waitforelementtoappear(toatsmessage);
		waitforelementtodiaappear(eletodisappear1);
	}
}
