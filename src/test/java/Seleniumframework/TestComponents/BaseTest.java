package Seleniumframework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import selenium.pageobjects.loginpage;

public class BaseTest {
	public WebDriver driver;
	public loginpage login;
	public WebDriver initializeDriver() throws IOException {
		//PROPERTIES CLASS CAN READ THE PROPERTIES
		Properties prop=new Properties();
		
		FileInputStream files=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\selenium\\resources\\GlobalData.properties");
		prop.load(files);
		String browsername=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
//		String browsername=prop.getProperty("browser");*//
//		if(browsername.equalsIgnoreCase("chrome"))  //changes it to conatisn because when we are ruunign chrome in headless mode from command prompt we cannot comapre exact string inpout 
		if(browsername.contains("chrome"))
		{	ChromeOptions options = new ChromeOptions();
			if(browsername.contains("headless")) {
					options.addArguments("headless");
		}
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900)); //full screen
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			//edge driver
		}
		else if(browsername.equalsIgnoreCase("FireFox")) {
			//edge driver
			driver=new FirefoxDriver();
		}
//		driver.manage().window().maximize();
		return driver;
	}
	public List<HashMap<String, String>> getJsonDatatMap(String filepath) throws IOException {
		//reading the json to String
		String jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//Convert String to Hashmap using jackson Databind 
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	
	}
	public String getScreenshot(String testCaseName, WebDriver driver ) throws IOException{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source,file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		
	}
	@BeforeMethod(alwaysRun = true)
	public loginpage launchapplication() throws IOException {
		driver =initializeDriver();
		login=new loginpage(driver);
		System.out.println("printing login objecty"+ login.toString());
		login.goTo();
		return login;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
