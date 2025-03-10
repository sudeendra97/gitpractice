package Seleniumframework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import selenium.resources.ExtentReporterNg;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent=ExtentReporterNg.getReportObject();
	ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		//ToDo
		//before it runs any test thuis method will start
		test=extent.createTest(result.getMethod().getMethodName()); //result holds all teh information about test
		extenttest.set(test); //sets unqiue thread id's
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
		//ToDo
		extenttest.get().log(Status.PASS, "Test Passed");
	}
	@Override
	public void onTestFailure(ITestResult result) {
//		test.log(Status.FAIL, "Test failed");
		extenttest.get().fail(result.getThrowable());
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		//screenshot and attach to the report
		String filepath = null;
		try {
			filepath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extenttest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		//ToDo
	}
	@Override
	public void onStart(ITestContext context) {
		//ToDo
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();	
	}
}
