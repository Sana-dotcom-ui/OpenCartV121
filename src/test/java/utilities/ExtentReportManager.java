package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter extentSpark;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	public String filename;
	
	public void onStart(ITestContext context) 
	{
		
		String timeStamp = new SimpleDateFormat("yyyy-mm-dd hh-mm").format(new Date());
		filename = "Test Report "+timeStamp+".html";
		extentSpark = new ExtentSparkReporter(".\\reports\\"+filename);
		extentSpark.config().setDocumentTitle("OpenCart Automation Test");
		extentSpark.config().setReportName("Functional Testing");
		extentSpark.config().setTheme(Theme.DARK);
		
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSpark);
		//Set the System information - Application Name, Tester, Browser, OS, Environment
		extentReports.setSystemInfo("Application Name", "OpenCart");
		extentReports.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
		extentReports.setSystemInfo("Operating System", context.getCurrentXmlTest().getParameter("os"));
		
		List<String> includedgroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedgroups.isEmpty())
		{
			extentReports.setSystemInfo("Groups", includedgroups.toString());
		}
	
	}

	public void onTestSuccess(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.PASS, result.getName()+"is passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.FAIL, result.getName()+"is failed");
		extentTest.log(Status.INFO,result.getThrowable());
		
		try 
		{
			String screenshotpath = new BaseClass().captureScreenshot(result.getName());
			extentTest.addScreenCaptureFromPath(screenshotpath);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReports.createTest(result.getTestClass().getName());
		extentTest.assignCategory(result.getMethod().getGroups());
		extentTest.log(Status.SKIP,result.getName()+"is Skipped");
		extentTest.log(Status.INFO,result.getThrowable());
	}

	public void onFinish(ITestContext context) {
	
		extentReports.flush();
		
		String extentReportFilepath = "reports/"+filename;
		File extentReportFile = new File(extentReportFilepath);
		
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
