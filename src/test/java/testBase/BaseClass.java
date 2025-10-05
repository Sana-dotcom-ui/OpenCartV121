package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger;
	public Properties pr;
	
	@BeforeClass (groups = {"Master","Sanity","Regression"})
	@Parameters({"os","browser"})
	public void setup (String os, String br) throws IOException
	{
		FileReader fr = new FileReader(".//src//test//resources//config.properties");
		pr = new Properties();
		pr.load(fr);
		
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver (); break;
		case "edge" : driver = new EdgeDriver();break;
		default: System.out.println("Invalid browser name entered"); return;
		
		}
		
		driver.manage().deleteAllCookies();
		
		logger = LogManager.getLogger(this.getClass());
		
		driver.get(pr.getProperty("appURL1"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	@AfterClass (groups = {"Master","Sanity","Regression"})
	public void teardown ()
	{
		driver.quit();
		
	}
	
	public String getRandomString()
	{
	   return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String getRandomNumbers()
	{
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String getRandomAlphaNumeric()
	{
		String alpha = RandomStringUtils.randomAlphabetic(3);
		String numeric = RandomStringUtils.randomNumeric(3);
		return alpha+"#"+numeric;
	}
	
	public String captureScreenshot(String reportname)
	{
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		
		String timeStamp = new SimpleDateFormat("yyy-mm-dd hh-mm").format(new Date());
		String targetFile = ".\\screenshots\\"+reportname+""+timeStamp+".png";
		File file = new File(targetFile);
		sourceFile.renameTo(file);
		
		return targetFile;
		
	}

}
