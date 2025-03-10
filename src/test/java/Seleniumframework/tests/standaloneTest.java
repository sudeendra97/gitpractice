package Seleniumframework.tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import selenium.pageobjects.loginpage;

public class standaloneTest {
	public static void main(String[]args) throws InterruptedException {
	System.setProperty("Webdriver.chrome.driver","C:\\Users\\sudee\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	
	String ProductName="IPHONE 13 PRO";
	driver.get("https://rahulshettyacademy.com/client/");
	driver.manage().window().maximize();
	driver.findElement(By.id("userEmail")).sendKeys("soundaryapacharya@gmail.com");
	driver.findElement(By.id("userPassword")).sendKeys("Test@123");
	driver.findElement(By.id("login")).click();
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(30));
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".left.mt-1 h3")));
	//list of products present in the page
	List<WebElement> prolist=driver.findElements(By.cssSelector(".mb-3"));
	//iterate through all the products in the list and find iphone 13 pro max and then add it to cart
	//Using iterating using stream
	WebElement finalproduct=prolist.stream().filter(product->product.findElement(By.tagName("b")).getText().equals(ProductName)).findFirst().orElse(null);
	finalproduct.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
	wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id(".ng-animating"))));	
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".heading h1"))));
		List<WebElement> cartlist=driver.findElements(By.cssSelector(".cartSection h3"));
		System.out.println(cartlist);
		Boolean productpresent=cartlist.stream().anyMatch(cartitems->cartitems.getText().equalsIgnoreCase(ProductName));
		Assert.assertTrue(productpresent);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder=\"Select Country\"]")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		Thread.sleep(2000);
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
//		driver.close();
		System.out.println("AllDone");
	}
}
