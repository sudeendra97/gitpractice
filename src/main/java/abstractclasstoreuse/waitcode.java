package abstractclasstoreuse;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import selenium.pageobjects.cartpage;
import selenium.pageobjects.orderpage;
public class waitcode {
	WebDriver driver;
	public waitcode(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartpage;
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderpage;
	
	
	public void waitforelementtoappear(By eletowait) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(eletowait)); 
		//to which element visibility should wait is need to be sent by test class 
	}
	public void waitforWebelementtoappear(WebElement eletowait) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(eletowait)); 
		//to which element visibility should wait is need to be sent by test class 
	}
	public void waitforelementtodiaappear(WebElement eletodisappear) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.invisibilityOf(eletodisappear)); //to which element visibility should wait is need to be sent by test class 
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id(".ng-animating"))));
	}
	
	public void waitforvisibilityoff(WebElement eletovisible ) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(eletovisible));
		
	}
	
	public cartpage gotocartpage() {
		cartpage.click();
		cartpage cart=new cartpage(driver);
		return cart;
	}
	public orderpage gotoorderpage() {
		
		orderpage.click();
		orderpage order=new orderpage(driver);
		return order;
	}
}
