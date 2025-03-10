package selenium.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractclasstoreuse.waitcode;

public class checkoutpage extends waitcode{
	WebDriver driver;
	public checkoutpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	WebElement selectcountry;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	By visibilityof =By.cssSelector(".ta-results");
	public void selectcountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitforelementtoappear(visibilityof);
		selectcountry.click();
		
	}
	
	public confirmationpage submit() {
		submit.click();
		return new confirmationpage(driver);
	}
	
}