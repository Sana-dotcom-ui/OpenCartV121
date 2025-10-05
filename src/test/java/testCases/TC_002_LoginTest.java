package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
 
	@Test(groups = {"Master","Sanity"})
	public void testLogin()
	{
		logger.info("*** Started Execution of LoginTestCase ****");
		try 
		{
			HomePage hp = new HomePage (driver);
			hp.clickMyAccount();
			hp.clickLoginLink();
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmailAdd(pr.getProperty("username"));
			lp.setPassword(pr.getProperty("password"));
			lp.clickLoginButton();
			
			MyAccountPage macc = new MyAccountPage(driver);
			boolean loginstatus = macc.isMyAccountpagedisplayed();
			if(loginstatus)
			{
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
			Assert.fail();
		}
		
		logger.info("*** Finished Execution of LoginTestCase ***");
	}
}
