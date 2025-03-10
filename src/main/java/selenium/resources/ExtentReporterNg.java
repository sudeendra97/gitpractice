package selenium.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {
	public static ExtentReports getReportObject() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);  //should send path where report should be created
		reporter.config().setReportName("Demo Report using Extent Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extent=new ExtentReports();//responsible to create and consolidate all the test execution
		//attach report with extent
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Soundarya");
		return extent;
	}

}
